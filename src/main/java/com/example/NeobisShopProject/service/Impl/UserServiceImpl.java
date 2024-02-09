package com.example.NeobisShopProject.service.Impl;

import com.example.NeobisShopProject.dto.UserDto;
import com.example.NeobisShopProject.entity.User;
import com.example.NeobisShopProject.enums.Role;
import com.example.NeobisShopProject.repository.UserRepository;
import com.example.NeobisShopProject.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    private final ModelMapper modelMapper;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, ModelMapper modelMapper) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
    }
    public User getByUsername(String username) {
        Optional<User> userOptional = userRepository.findByUsername(username);
        return userOptional.orElseThrow(() -> new UsernameNotFoundException("Пользователь не найден"));
    }


    public UserDetailsService userDetailsService() {
        return this::getByUsername;
    }
    public User getCurrentUser() {
        // Получение имени пользователя из контекста Spring Security
        var username = SecurityContextHolder.getContext().getAuthentication().getName();
        return getByUsername(username);
    }
    @Deprecated
    public void getAdmin() {
        var user = getCurrentUser();
        user.setRole(Role.ROLE_ADMIN);
        userRepository.save(user);
    }
   @Override
   public User createUser(User user) {
       if (userRepository.existsByUsername(user.getUsername())) {
           // Заменить на свои исключения
           throw new RuntimeException("Пользователь с таким именем уже существует");
       }

       if (userRepository.existsByEmail(user.getEmail())) {
           throw new RuntimeException("Пользователь с таким email уже существует");
       }

       return userRepository.save(user);
   }

    @Override
    public List<UserDto> getAllUsers() {
        List<User> userList = userRepository.findAll();
        return userList.stream()
                .map(user -> modelMapper.map(user, UserDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public UserDto getUserById(Long id) {
        Optional<User> userOptional = userRepository.findById(id);
        if (userOptional.isPresent()) {
            return modelMapper.map(userOptional.get(), UserDto.class);
        } else {
            throw new RuntimeException("User not found");
        }
    }




    @Override
    public UserDto updateUser(Long id, UserDto userDto) {
        User existingUser = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));

        modelMapper.map(userDto, existingUser); // Update existingUser fields
        User updatedUser = userRepository.save(existingUser);
        return modelMapper.map(updatedUser, UserDto.class);
    }

    @Override
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }
    @Override
    public ResponseEntity<Object> createUserAdmin(UserDto userDto) {
        try {
            // Map UserDto to User entity
            User user = modelMapper.map(userDto, User.class);

            // Save the user
            User savedUser = userRepository.save(user);

            // Map the saved user back to UserDto
            UserDto savedUserDto = modelMapper.map(savedUser, UserDto.class);

            // Return ResponseEntity with the saved user and HTTP status CREATED

            return ResponseEntity.status(HttpStatus.CREATED).body(savedUserDto);
        } catch (Exception e) {
            // If any exception occurs during user creation, return ResponseEntity with error message and HTTP status BAD_REQUEST
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Failed to create user: " + e.getMessage());
        }

}
}

