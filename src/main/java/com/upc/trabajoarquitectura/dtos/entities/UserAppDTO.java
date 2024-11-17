package com.upc.trabajoarquitectura.dtos.entities;

import com.upc.trabajoarquitectura.entities.Role;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class UserAppDTO {
    private Long userId;
    private String names;
    private String surnames;
    private String phoneNumber;
    private String email;
    private String gender;
    private String username;
    private String password;
    private String imagePath;
    private Role role;
}
