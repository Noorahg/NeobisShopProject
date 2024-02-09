package com.example.NeobisShopProject.dto;

import com.example.NeobisShopProject.enums.Role;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor

public class UserDto {
    private Long id;
    private String firstName;
    private String lastName;
    private String address;
    private String email;
    private String password;
    private String phoneNumber;
    private Role role;
    private boolean active;

}
