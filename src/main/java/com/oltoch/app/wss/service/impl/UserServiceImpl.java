package com.oltoch.app.wss.service.impl;

import com.oltoch.app.wss.io.entity.UserEntity;
import com.oltoch.app.wss.repository.UserRepository;
import com.oltoch.app.wss.security.JwtService;
import com.oltoch.app.wss.service.UserService;
import com.oltoch.app.wss.shared.dto.UserDto;
import com.oltoch.app.wss.utils.Utils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    //Getting the methods provided by jpa
    @Autowired
    UserRepository userRepository;

    @Autowired
    Utils utils;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    JwtService jwtService;

    @Autowired
    AuthenticationManager authenticationManager;

    @Override
    public UserDto registerUser(UserDto user) {

        if (userRepository.findByEmail(user.getEmail()).isPresent()) {
            throw new RuntimeException("User already exists!");
        }

        UserEntity userEntity = new UserEntity();
        BeanUtils.copyProperties(user, userEntity);//copy data from dto to entity

        userEntity.setPassword(passwordEncoder.encode(user.getPassword()));

        userEntity.setUserId(utils.generateUserId(20));

        //Saves to DB.
        UserEntity storedDetails = userRepository.save(userEntity);

        UserDto output = new UserDto();
        BeanUtils.copyProperties(storedDetails, output);
        output.setToken(jwtService.generateToken(storedDetails));
        return output;
    }

    @Override
    public UserDto login(UserDto user) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        user.getEmail(), user.getPassword()
                )
        );
        //Throw correct exception and wrap in a try catch block later
        var entity = userRepository.findByEmail(user.getEmail()).orElseThrow();
        var jwtToken = jwtService.generateToken(entity);
        return UserDto.builder()
                .token(jwtToken)
                .firstName(entity.getFirstName())
                .lastName(entity.getLastName())
                .email(user.getEmail())
                .userId(entity.getUserId())
                .build();
    }

    @Override
    public UserDto getUserById(String id) throws Exception {
        Optional<UserEntity> entity = userRepository.findByUserId(id);
        if (entity.isEmpty()) throw new Exception("User not found");
        UserDto output = new UserDto();
        BeanUtils.copyProperties(entity.get(), output);
        return output;
    }

    @Override
    public UserEntity loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<UserEntity> entity = userRepository.findByEmail(username);
        if (entity.isEmpty()) throw new UsernameNotFoundException(username);

        return entity.get();
    }

}
