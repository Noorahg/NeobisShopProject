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
    private String username;
    private String email;
    private String password;
    private Role role;

}
