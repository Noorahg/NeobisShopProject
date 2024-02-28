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
@RequestMapping("/admin")
@RequiredArgsConstructor
@Tag(name = "Admin controller", description = "Controller for ADMIN")
public class AdminController {
    private final UserServiceImpl userServiceImpl;

    @GetMapping("/allUsers")
    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "for get all users", description = "For get list users for ADMIN")
    public List<User> getAll() {
        System.out.println();
        return userServiceImpl.findAll();
    }

    @PostMapping("/createUserAdmin")
    @PreAuthorize("hasAuthority('ADMIN')")
    @Operation(summary = "add admin", description = "For add user admin")
    public ResponseEntity<Object> addUser(@RequestBody UserDto userDto) {
        return userServiceImpl.createUserAdmin(userDto);
    }

    @GetMapping("{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    @Operation(summary = "get user", description = "For get user by id")
    public UserDto getUserById(@PathVariable Long id) {
        return userServiceImpl.getUserById(id);
    }

    @DeleteMapping("/delete/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    @Operation(summary = "delete user", description = "Delete user by id")
    public ResponseEntity<Object> deleteUser(@PathVariable Long id) {
        return userServiceImpl.deleteUser(id);
    }
}
