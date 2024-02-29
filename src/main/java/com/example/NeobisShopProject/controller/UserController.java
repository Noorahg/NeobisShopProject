package com.example.NeobisShopProject.controller;

import com.example.NeobisShopProject.dto.UserDto;
import com.example.NeobisShopProject.entity.User;
import com.example.NeobisShopProject.service.Impl.UserServiceImpl;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
@Tag(name = "User controller", description = "Uses for logic upon users")
public class UserController {
    private final UserServiceImpl userServiceImpl;
    @GetMapping("/allUsers")
    @PreAuthorize("hasRole('USER')")
    @Operation(summary = "for get all users", description = "For get list users for ADMIN")
    public List<User> getAll() {
        System.out.println();
        return userServiceImpl.findAll();
    }
    @PostMapping("/createUser")
    @PreAuthorize("hasAuthority('USER')")
    @Operation(summary = "create user", description = "Create default user")
    public ResponseEntity<Object> addUser(@RequestBody UserDto userDto) {
        return userServiceImpl.createUser(userDto);
    }

    @PutMapping("/updateUserRequest/{id}")
    @PreAuthorize("hasAuthority('USER')")
    @Operation(summary = "update user request", description = "Update email and password for user")
    public ResponseEntity<Object> updateUserRequestById(@PathVariable Long id,
                                                        UserDto userDto) {
        return userServiceImpl.updateUserRequest(id, userDto);
    }

    @PutMapping("/updateUserInfo/{id}")
    @PreAuthorize("hasAnyAuthority('USER')")
    @Operation(summary = "update user info", operationId = "Update user information, for example: firstname")
    public ResponseEntity<Object> updateUserInfo(@PathVariable Long id, UserDto userDto) {
        return userServiceImpl.updateUserInfoById(id, userDto);
    }
}
