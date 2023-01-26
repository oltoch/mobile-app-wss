package com.oltoch.app.wss.service;

import com.oltoch.app.wss.shared.dto.UserDto;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {
	UserDto registerUser(UserDto user);
	UserDto login(UserDto user);
	UserDto getUserById(String id) throws Exception;
}
