package com.upc.trabajoarquitectura.dtos.entities;

import com.upc.trabajoarquitectura.entities.UserApp;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class UserListDTO {
    private Long listId;
    private String name;
    private UserApp userApp;
}