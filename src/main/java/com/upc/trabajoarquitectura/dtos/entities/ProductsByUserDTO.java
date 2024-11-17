package com.upc.trabajoarquitectura.dtos.entities;

import com.upc.trabajoarquitectura.entities.Product;
import com.upc.trabajoarquitectura.entities.UserApp;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter

public class ProductsByUserDTO {
    private UserApp userApp;
    private Product product;
    private LocalDate date;
}