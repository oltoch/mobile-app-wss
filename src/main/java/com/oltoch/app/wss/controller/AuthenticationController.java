package com.oltoch.app.wss.controller;

import com.oltoch.app.wss.model.request.UserLoginRequestModel;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.oltoch.app.wss.model.request.UserDetailsRequestModel;
import com.oltoch.app.wss.model.response.UserRest;
import com.oltoch.app.wss.service.UserService;
import com.oltoch.app.wss.shared.dto.UserDto;


@RestController
@RequestMapping("/api/v1/auth") // baseUrl/api/v1/auth
public class AuthenticationController {

    @Autowired
    UserService userService;

    @PostMapping("/register")
    public ResponseEntity<UserRest> register(@RequestBody UserDetailsRequestModel userDetails)
            throws IllegalArgumentException {
        assert userDetails.getEmail() != null;
        if(userDetails.getFirstName() == null){
            throw new IllegalArgumentException("firstName (type:String) field is required");
        }
        UserRest userRest = new UserRest();

        UserDto userDto = new UserDto();
        BeanUtils.copyProperties(userDetails, userDto);

        UserDto createdUser = userService.registerUser(userDto);
        BeanUtils.copyProperties(createdUser, userRest);


        return ResponseEntity.ok(userRest);

    }

    @PostMapping("/login")
    public ResponseEntity<UserRest> login(@RequestBody UserLoginRequestModel loginModel) {
        UserRest userRest = new UserRest();

        UserDto userDto = new UserDto();
        BeanUtils.copyProperties(loginModel, userDto);

        UserDto loggedUser = userService.login(userDto);
        BeanUtils.copyProperties(loggedUser, userRest);

        return ResponseEntity.ok(userRest);

    }

    @PutMapping
    public String updateUser() {
        return "Put got called";
    }

    @DeleteMapping
    public String deleteUser() {
        return "Delete got called";
    }
}

