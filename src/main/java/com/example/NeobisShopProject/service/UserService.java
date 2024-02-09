package com.example.NeobisShopProject.service;

import com.example.NeobisShopProject.dto.UserDto;
import com.example.NeobisShopProject.entity.User;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
public interface UserService {
   User createUser(User user);
    public List<UserDto> getAllUsers() ;

    UserDto getUserById(Long id);
    UserDto updateUser(Long id, UserDto userDto);
    void deleteUser(Long id);
    public ResponseEntity<Object> createUserAdmin(UserDto userDto);
}

