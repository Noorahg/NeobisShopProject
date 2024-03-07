package com.example.NeobisShopProject.service.Impl;



import com.example.NeobisShopProject.entity.User;
import com.example.NeobisShopProject.exception.UserNotFoundException;
import com.example.NeobisShopProject.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;



@Service
public class UserDetailsServicee implements UserDetailsService {
    private final UserRepository userRepository;
    @Autowired
    public UserDetailsServicee(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /*@Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<User> user = userRepository.findUserByEmail(email);

        if(user.isEmpty()) {
            throw new UsernameNotFoundException("User not found!");
        }

        return new User(user.get()) {
        };
    }*/

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User user = userRepository.findUserByUsername(username).orElseThrow(
                () -> new UserNotFoundException("User not found"));

        return new org.springframework.security.core.userdetails.User(user.getUsername(),
                user.getPassword(), user.getAuthorities());
    }
}
