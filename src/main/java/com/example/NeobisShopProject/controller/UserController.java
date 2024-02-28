package com.example.NeobisShopProject.controller;

import com.example.NeobisShopProject.dto.UserDto;
import com.example.NeobisShopProject.service.Impl.UserServiceImpl;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
@Tag(name = "User controller", description = "Uses for logic upon users")
public class UserController {
    private final UserServiceImpl userServiceImpl;

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
