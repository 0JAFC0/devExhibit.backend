package io.github.jafc.jafcportfolio.application.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import io.github.jafc.jafcportfolio.domain.model.User;
import io.github.jafc.jafcportfolio.infrastructure.exceptions.NotFoundException;
import io.github.jafc.jafcportfolio.infrastructure.exceptions.UsernameFoundException;
import io.github.jafc.jafcportfolio.infrastructure.persistence.repository.UserRepository;
import io.github.jafc.jafcportfolio.infrastructure.security.jwt.JwtTokenUtil;
import io.github.jafc.jafcportfolio.presentation.dto.security.AccountCredentials;
import io.github.jafc.jafcportfolio.presentation.dto.security.Token;

@Service
public class UserService {
	
	@Autowired
    private UserRepository userRepository;
	
	@Autowired
	private PasswordEncoder bcryptEncoder;
	
	@Autowired
	private JwtTokenUtil jwtTokenUtil;
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private MyUserDetailsService myUserDetailsService;
    
    public Token signin(AccountCredentials data) {
		try {
			authenticate(data.getEmail(), data.getPassword());
			
			UserDetails user = myUserDetailsService.loadUserByUsername(data.getEmail());
			
			return new Token(user.getUsername(),jwtTokenUtil.generateToken(user));
		} catch (Exception e) {
			throw new BadCredentialsException("Invalid email/password supplied!");
		}
	}
    
    private void authenticate(String username, String password) throws Exception {
		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
		} catch (DisabledException e) {
			throw new DisabledException("USER_DISABLED");
		} catch (BadCredentialsException e) {
			throw new BadCredentialsException("INVALID_CREDENTIALS");
		}
	}
    
    public User saveUser(User user) {
    	// validate if the user exist
    	userRepository.findByEmail(user.getEmail()).ifPresent(element -> new UsernameFoundException("User ".concat(user.getUsername()).concat(" exist!")));
    	// encoder password
    	user.setPassword(bcryptEncoder.encode(user.getPassword()));
    	return userRepository.save(user);
    }
    
//    public User addRoleToUser(String username, String roleName) {
//    	User user = userRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("User with username ".concat(username).concat(" not found!")));
//    	Role role = roleRepository.findByName(roleName).orElseThrow(() -> new NotFoundException("Role with name ".concat(roleName).concat(" not found!")));
//    	user.getRoles().add(role);
//    	return user;
//    }

    public User update(User user) {
        return userRepository.save(user);
    }

    public void deleteById(Long id) {
        userRepository.deleteById(id);
    }
    
    public User getUser(String email) {
    	return userRepository.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException("User with email ".concat(email).concat(" not found in the database!")));
    }

    public List<User> getUsers() {
        return userRepository.findAll();
    }

    public User findById(Long id) {
        return userRepository.findById(id).orElseThrow(() -> new NotFoundException("The user with id ".concat(id.toString()).concat(" not exist.")));
    }
}
