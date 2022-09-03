package com.sewah.usersservice.controllers;

import com.sewah.usersservice.dtos.UserSignupDto;
import com.sewah.usersservice.services.userServices.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "/users")
@AllArgsConstructor
@PreAuthorize("permitAll()")
public class UsersController {
    private final UserService service;
    private final AuthenticationManager authenticationManager;

    @GetMapping()
    ResponseEntity<Object> getUsers(){
        return ResponseEntity.ok(
                service.getUsers()
        );
    }

    @PostMapping("/signup")
    ResponseEntity<?> signup(@Valid @RequestBody UserSignupDto request){
        return ResponseEntity.ok(service.signup(request));
    }

}
