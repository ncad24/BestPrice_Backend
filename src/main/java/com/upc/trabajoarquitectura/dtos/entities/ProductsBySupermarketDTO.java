package com.upc.trabajoarquitectura.dtos.entities;

import com.upc.trabajoarquitectura.entities.Product;
import com.upc.trabajoarquitectura.entities.Supermarket;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter

public class ProductsBySupermarketDTO {
    private Supermarket supermarket;
    private Product product;
    private double price;
    private LocalDate date;
}
