package com.example.NeobisShopProject.service.Impl;

import com.example.NeobisShopProject.dto.UserDto;
import com.example.NeobisShopProject.entity.User;
import com.example.NeobisShopProject.enums.Role;
import com.example.NeobisShopProject.repository.UserRepository;
import com.example.NeobisShopProject.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.password.PasswordEncoder;


import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    private final ModelMapper modelMapper;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, ModelMapper modelMapper, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
        this.passwordEncoder = passwordEncoder;
    }
    public User getByUsername(String username) {
        Optional<User> userOptional = userRepository.findByUsername(username);
        return userOptional.orElseThrow(() -> new UsernameNotFoundException("Пользователь не найден"));
    }
    public List<User> findAll() {
        return userRepository.findAll();
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
   public ResponseEntity<Object> createUser(UserDto userDto) {
       if (userRepository.findUserByEmail(userDto.getEmail()).isPresent()) {
           return ResponseEntity.badRequest().body("User with email:" + userDto.getEmail() + " already exists!");
       }

       User user = modelMapper.map(userDto, User.class);
       user.setPassword(passwordEncoder.encode(userDto.getPassword()));
       user.setRole(Role.ROLE_USER);

       userRepository.save(user);

       return ResponseEntity.ok("User successfully created");
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
    public ResponseEntity<Object> deleteUser(Long id) {
        // Проверяем, существует ли пользователь с данным ID
        if (userRepository.existsById(id)) {
            // Если пользователь существует, удаляем его
            userRepository.deleteById(id);
        } else {
            // Если пользователь не существует, выбрасываем исключение
            throw new RuntimeException("User not found");
        }
        return null;
    }
    @Override
    public ResponseEntity<Object> updateUserRequest(Long id, UserDto userDto) {
        Optional<User> optionalUser = userRepository.findById(id);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();

            // Use ModelMapper to map fields from UserDTO to the existing User entity
            modelMapper.map(userDto, user);

            userRepository.save(user);
            return ResponseEntity.ok("User password was updated!");
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    @Override
        public ResponseEntity<Object> createUserAdmin(UserDto userDto) {
            // Check if user with email already exists
            if (userRepository.findUserByEmail(userDto.getEmail()).isPresent()) {
                return ResponseEntity.ok("User with email:"  + userDto.getEmail() +  " already exists!");
            }

            // Mapping UserDTO to User entity using ModelMapper
            User user = modelMapper.map(userDto, User.class);

            // Save the user entity
            userRepository.save(user);

            return ResponseEntity.ok("User successfully established");
        }

@Override
    public ResponseEntity<Object> updateUserInfoById(Long id, UserDto userDto) {
        Optional<User> optionalUser = userRepository.findById(id);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();

            // Use ModelMapper to map fields from UserDTO to the existing User entity
            modelMapper.map(userDto, user);

            userRepository.save(user);

            return ResponseEntity.ok("User info was updated");
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}

