package com.dj.service.impl;

import com.dj.dto.SignUpRequest;
import com.dj.exception.UserAlreadyExistAuthenticationException;
import com.dj.model.Role;
import com.dj.model.User;
import com.dj.repo.RoleRepository;
import com.dj.repo.UserRepository;
import com.dj.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

	private final UserRepository userRepository;
	private final RoleRepository roleRepository;
	private final PasswordEncoder passwordEncoder;

	@Override
	public User findUserByEmail(final String email) {
		return userRepository.findByEmailIgnoreCase(email);
	}

	@Override
	@Transactional(value = "transactionManager")
	public User registerNewUser(final SignUpRequest signUpRequest) throws UserAlreadyExistAuthenticationException {
		if (userRepository.existsByEmailIgnoreCase(signUpRequest.getEmail())) {
			throw new UserAlreadyExistAuthenticationException("User with email id " + signUpRequest.getEmail() + " already exist");
		}
		User user = buildUser(signUpRequest);
		user = userRepository.save(user);
		userRepository.flush();
		return user;
	}

	private User buildUser(final SignUpRequest signUpRequest) {
		User user = new User();
		user.setCustomerName(signUpRequest.getCustomerName());
		user.setEmail(signUpRequest.getEmail());
		user.setPassword(passwordEncoder.encode(signUpRequest.getPassword()));
		user.addRole(roleRepository.findByName(Role.ROLE_USER));
		user.setEnabled(true);
		return user;
	}
}
