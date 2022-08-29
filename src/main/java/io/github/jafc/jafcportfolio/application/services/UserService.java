package io.github.jafc.jafcportfolio.application.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import io.github.jafc.jafcportfolio.domain.model.User;
import io.github.jafc.jafcportfolio.infrastructure.exceptions.NotFoundException;
import io.github.jafc.jafcportfolio.infrastructure.exceptions.UsernameFoundException;
import io.github.jafc.jafcportfolio.infrastructure.persistence.repository.RoleRepository;
import io.github.jafc.jafcportfolio.infrastructure.persistence.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class UserService implements UserDetailsService {
	
	@Autowired
    private UserRepository userRepository;
	
	@Autowired
	private RoleRepository roleRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		User user = userRepository.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException("User with email ".concat(email).concat(" not found!")));
		return user;
	}
    
    public User saveUser(User user) {
    	log.info("Saving new User {} to the database",user.getEmail());
    	if(user.getRoles()!=null) {
    		roleRepository.save(user.getRoles().get(0));    		
    	}
    	// validate if the user exist
    	userRepository.findByEmail(user.getEmail()).ifPresent(element -> new UsernameFoundException("User ".concat(user.getUsername()).concat(" exist!")));
    	// encoder password
    	user.setPassword(passwordEncoder.encode(user.getPassword()));
    	return userRepository.save(user);
    }

    public User update(User user) {
        return userRepository.save(user);
    }

    public void deleteById(Long id) {
        userRepository.deleteById(id);
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
