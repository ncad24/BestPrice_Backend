package com.upc.trabajoarquitectura.dtos.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter

public class CategoryDTO {
    private Long categoryId;
    private String name;
    private String description;
}
