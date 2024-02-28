package com.example.NeobisShopProject.service;

import com.example.NeobisShopProject.dto.UserDto;
import com.example.NeobisShopProject.entity.User;
import org.springframework.http.ResponseEntity;

import java.util.List;
public interface UserService {
   ResponseEntity<Object> createUser(UserDto userdto);
 ResponseEntity<Object> updateUserRequest(Long id, UserDto userDto);

    List<User> findAll() ;
    List<UserDto> getAllUsers();
    UserDto updateUser(Long id, UserDto userDto) ;


        UserDto getUserById(Long id);
    ResponseEntity<Object> deleteUser(Long id);
    ResponseEntity<Object> createUserAdmin(UserDto userDto);
    ResponseEntity<Object> updateUserInfoById(Long id, UserDto userDto);

    }

