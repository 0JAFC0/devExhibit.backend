package io.github.jafc.jafcportfolio.application.services;

import io.github.jafc.jafcportfolio.domain.enumeration.Role;
import io.github.jafc.jafcportfolio.domain.model.User;
import io.github.jafc.jafcportfolio.infrastructure.http.client.feign.google.GoogleApisController;
import io.github.jafc.jafcportfolio.infrastructure.http.client.feign.google.Oauth2GoogleController;
import io.github.jafc.jafcportfolio.infrastructure.exceptions.EmailFoundException;
import io.github.jafc.jafcportfolio.infrastructure.exceptions.NotFoundException;
import io.github.jafc.jafcportfolio.infrastructure.persistence.repository.UserRepository;
import io.github.jafc.jafcportfolio.infrastructure.security.services.JwtService;
import io.github.jafc.jafcportfolio.presentation.dto.request.AccountCredentials;
import io.github.jafc.jafcportfolio.presentation.dto.request.GoogleTokenRequestBody;
import io.github.jafc.jafcportfolio.presentation.dto.response.Token;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.core.env.Environment;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.util.Objects;
import java.util.Optional;
import java.util.Random;

@Service
@Slf4j
public class UserService {

    private final UserRepository userRepository;

    private final JwtService jwtService;

    private final AuthenticationManager authenticationManager;

	private final PasswordEncoder passwordEncoder;

	private final Oauth2GoogleController oauth2GoogleController;

	private final GoogleApisController googleApisController;

	private final String googleClientId;

	private final String googleClientSecret;

	private final String googleRedirectUri;

	private final String googleAuthUri;

	private final String googleScope;

	private static final String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789!@#$%^&*()_+";

	public UserService(UserRepository userRepository,
					   JwtService jwtUtils,
					   AuthenticationManager authenticationManager,
					   PasswordEncoder passwordEncoder,
					   Oauth2GoogleController oauth2GoogleController,
					   GoogleApisController googleApisController,
					   Environment env) {
		this.userRepository = userRepository;
		this.jwtService = jwtUtils;
		this.authenticationManager = authenticationManager;
		this.passwordEncoder = passwordEncoder;
		this.oauth2GoogleController = oauth2GoogleController;
		this.googleApisController = googleApisController;

		this.googleClientId = env.getProperty("spring.security.oauth2.client.registration.google.client-id");
		this.googleClientSecret = env.getProperty("spring.security.oauth2.client.registration.google.client-secret");
		this.googleAuthUri = env.getProperty("spring.security.oauth2.client.provider.google.authorization-uri");
		this.googleRedirectUri = env.getProperty("spring.security.oauth2.client.registration.google.redirect-uri");
		this.googleScope = env.getProperty("spring.security.oauth2.client.registration.google.scope");

	}

	public User signup(User user) {
    	if(userRepository.existsByEmail(user.getEmail())) {
    		throw new EmailFoundException("Email is already in use!");
    	}

		user.setRole(Role.USER);
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		return userRepository.save(user);
    }
    
    public Token signin(AccountCredentials accountCredentials) {

    	authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
				accountCredentials.getEmail(),
				accountCredentials.getPassword()));

		var user = getUserByEmail(accountCredentials.getEmail());

		var jwtToken = jwtService.generateToken(user);

        Token token = new Token();
        token.setAccessToken(jwtToken);
		token.setTokenType("Bearer");
		token.setId(user.getId());
    	return token;
    }

	public String redirecionarToGoogleAuth() {
		return String.format("%s?client_id=%s&redirect_uri=%s&response_type=code&scope=%s", googleAuthUri, googleClientId, googleRedirectUri, googleScope);
	}

	private String generatePassword() {
		int passwordLength = 12;
		Random random = new SecureRandom();
		StringBuilder password = new StringBuilder(passwordLength);

		for (int i = 0; i < passwordLength; i++) {
			int randomIndex = random.nextInt(CHARACTERS.length());
			password.append(CHARACTERS.charAt(randomIndex));
		}

		return password.toString();
	}

	public Token getAccessTokenWithGoogle(String code) {

		GoogleTokenRequestBody requestBody = new GoogleTokenRequestBody(
				googleClientId,
				googleClientSecret,
				googleRedirectUri,
				code,
				"authorization_code");

		Token token = oauth2GoogleController.getToken(requestBody);

		if(Objects.isNull(token)) {
			throw new RuntimeException("Não foi possivel obter o token de acesso google.");
		}

		User userResponse = googleApisController.getInformacoes("Bearer " + token.getAccessToken());

		if(Objects.isNull(userResponse)) {
			throw new RuntimeException("Não foi possivel obter as informações do usuario.");
		}

		Optional<User> userOp = userRepository.findByEmail(userResponse.getEmail());

		if(userOp.isPresent()) {
			AccountCredentials accountCredentials = new AccountCredentials(
					userOp.get().getUsername(),
					userOp.get().getUsername()
			);

			return signin(accountCredentials);
		} else {
			User user = new User();
			user.setName(userResponse.getName());
			user.setEmail(userResponse.getEmail());
			String password = generatePassword();
			user.setPassword(password);
			AccountCredentials account = new AccountCredentials(user.getEmail(), password);

			signup(user);
			return signin(account);
		}
	}

	public User update(User user, String email) {
		User userTemp = getUserByEmail(email);
		BeanUtils.copyProperties(userTemp, user);
        return userRepository.save(userTemp);
    }

    public boolean deleteByEmail(String email) {
		if(userRepository.findByEmail(email).isPresent() || userRepository.findByEmail(email).isEmpty()){
			userRepository.deleteByEmail(email);
			return true;
		}
		
		return false;
    }
    
    public User getUserByEmail(String email) {
    	log.info("get User {} to the database",email);
    	return userRepository.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException("User with email ".concat(email).concat(" not found in the database!")));
    }

    public User getById(Long id) {
        return userRepository.findById(id)
				.orElseThrow(() -> new NotFoundException("The user with id ".concat(id.toString())
						.concat(" not exist.")));
    }

	public Long countUsers() {
		return this.userRepository.count();
	}

	public boolean userByIdExist(Long id) {
        return userRepository.existsById(id);
    }


}
