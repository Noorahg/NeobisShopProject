package com.example.NeobisShopProject.service;

import com.example.NeobisShopProject.dto.UserDto;
import com.example.NeobisShopProject.entity.User;
import org.springframework.http.ResponseEntity;

import java.util.List;
public interface UserService {
   User createUser(User user);
    public List<UserDto> getAllUsers() ;
     public List<User> findAll() ;

    UserDto getUserById(Long id);
    UserDto updateUser(Long id, UserDto userDto);
    void deleteUser(Long id);
    public ResponseEntity<Object> createUserAdmin(UserDto userDto);
}

