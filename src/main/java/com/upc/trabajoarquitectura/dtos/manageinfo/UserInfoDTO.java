package com.upc.trabajoarquitectura.dtos.manageinfo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class UserInfoDTO {
    private Long userId;
    private String names;
    private String surnames;
    private String phoneNumber;
    private String email;
    private String gender;
    private String username;
    private String imagePath;
}