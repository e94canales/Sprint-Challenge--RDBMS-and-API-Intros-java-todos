package com.erick.rdbmsprint.controllers;

import com.erick.rdbmsprint.models.User;
import com.erick.rdbmsprint.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    UserService userService;

    // http://localhost:2019/users/users
    @GetMapping(value = "/users", produces = {"application/json"})
    public ResponseEntity<?> getAllUsers(){
        List<User> rtnList = userService.getAllUsers();
        return new ResponseEntity<>(rtnList, HttpStatus.OK);
    }

    // http://localhost:2019/users/user/4
    @GetMapping(value = "/user/{id}", produces = {"application/json"})
    public ResponseEntity<?> findUserById(@PathVariable long id){
        User rtnUser = userService.getUserById(id);
        return new ResponseEntity<>(rtnUser, HttpStatus.OK);
    }

    // POST http://localhost:2019/users/user
    @PostMapping(value = "user", consumes = {"application/json"})
    public ResponseEntity<?> addNewUser(@Validated @RequestBody User newUser){
        newUser.setUserid(0);
        newUser = userService.saveUser(newUser);

        HttpHeaders responseHeaders = new HttpHeaders();
        URI newUserURI = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{userid}")
                .buildAndExpand(newUser.getUserid())
                .toUri();
        responseHeaders.setLocation(newUserURI);

        return new ResponseEntity<>(null, responseHeaders, HttpStatus.CREATED);
    }
}
