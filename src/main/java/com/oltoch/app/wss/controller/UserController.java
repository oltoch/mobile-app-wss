package com.oltoch.app.wss.controller;

import com.oltoch.app.wss.io.entity.UserEntity;
import com.oltoch.app.wss.model.response.UserRest;
import com.oltoch.app.wss.service.UserService;
import com.oltoch.app.wss.shared.dto.UserDto;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping
    public String getUsers() {
        return "Get users was called";
    }

    //Getting user by id using path variable
    @GetMapping("/{id}")
    public ResponseEntity<UserRest> getUserById(@PathVariable(value = "id") String id) throws Exception {
        UserRest userRest = new UserRest();

        UserDto createdUser = userService.getUserById(id);
        BeanUtils.copyProperties(createdUser, userRest);
        return ResponseEntity.ok(userRest);
    }

    //Getting user by id using query parameter
    @GetMapping("/byId")
    public ResponseEntity<Object> getUserById2(@RequestParam(value = "id") String id) throws Exception {
        UserRest userRest = new UserRest();

        UserDto createdUser = userService.getUserById(id);
        BeanUtils.copyProperties(createdUser, userRest);
        HashMap<String, Object> out= new HashMap<>();
        out.put("status",true);
        out.put("message", "User details fetched successfully");
        out.put("data", userRest);
        return ResponseEntity.ok(out);

    }
}
