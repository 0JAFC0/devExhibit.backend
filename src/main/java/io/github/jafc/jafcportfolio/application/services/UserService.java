package io.github.jafc.jafcportfolio.application.services;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import io.github.jafc.jafcportfolio.domain.model.ERole;
import io.github.jafc.jafcportfolio.domain.model.Role;
import io.github.jafc.jafcportfolio.domain.model.User;
import io.github.jafc.jafcportfolio.infrastructure.exceptions.EmailFoundException;
import io.github.jafc.jafcportfolio.infrastructure.exceptions.NotFoundException;
import io.github.jafc.jafcportfolio.infrastructure.persistence.repository.RoleRepository;
import io.github.jafc.jafcportfolio.infrastructure.persistence.repository.UserRepository;
import io.github.jafc.jafcportfolio.infrastructure.security.jwt.JwtUtils;
import io.github.jafc.jafcportfolio.infrastructure.security.services.UserDetailsImpl;
import io.github.jafc.jafcportfolio.presentation.dto.request.AccountCredentials;
import io.github.jafc.jafcportfolio.presentation.dto.response.Token;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class UserService {
	
	@Autowired
    private UserRepository userRepository;
	
	@Autowired
	private RoleRepository roleRepository;
	
	@Autowired
    private JwtUtils jwtUtils;
	
	@Autowired
    private AuthenticationManager authenticationManager;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
    
    public User saveUser(User user) {
    	if(userRepository.existsByEmail(user.getEmail())) {
    		throw new EmailFoundException("Email is already in use!");
    	}
    	// set roles
    	Set<Role> roles = new HashSet<>();
    	if(userRepository.count() == 0) {
    		Role role = new Role();
    		role.setName(ERole.ROLE_ADMIN);
    		roleRepository.save(role);
    		roles.add(role);
    	} else {
    		Role role = new Role();
    		role.setName(ERole.ROLE_USER);
    		roleRepository.save(role);
    		roles.add(role);
    	}
    	
		user.setRoles(roles);
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		return userRepository.save(user);
    }
    
    public Token signin(AccountCredentials accountCredentials) {
    	UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = 
    			new UsernamePasswordAuthenticationToken(accountCredentials.getEmail(), accountCredentials.getPassword());
    	
    	Authentication authentication = authenticationManager.authenticate(usernamePasswordAuthenticationToken);
    	SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateToken(authentication);
        
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();    
        List<String> roles = userDetails.getAuthorities().stream()
            .map(GrantedAuthority::getAuthority)
            .collect(Collectors.toList());
    	
        Token token = new Token();
        token.setAccessToken(jwt);
        token.setEmail(userDetails.getEmail());
        token.setRoles(roles);
    	return token;
    }

    public User update(User user) {
        return userRepository.save(user);
    }

    public boolean deleteByEmail(String email) {
		if(!userRepository.findByEmail(email).isEmpty()){
			userRepository.deleteByEmail(email);
			return true;
		}
		
		return false;
    }
    
    public User getUser(String email) {
    	log.info("get User {} to the database",email);
    	return userRepository.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException("User with email ".concat(email).concat(" not found in the database!")));
    }

    public List<User> getUsers() {
        return userRepository.findAll();
    }

    public User findById(Long id) {
        return userRepository.findById(id).orElseThrow(() -> new NotFoundException("The user with id ".concat(id.toString()).concat(" not exist.")));
    }

}
