package com.upc.trabajoarquitectura.dtos.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class ProductClickDTO {
    private Long id;
    private String name;
    private int clicks;
}
