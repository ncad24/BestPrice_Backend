package com.upc.trabajoarquitectura.dtos.querys;

import com.upc.trabajoarquitectura.entities.Brand;
import com.upc.trabajoarquitectura.entities.Category;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class ProductsPerUserDTO {
    private Long productId;
    private String name;
    private String description;
    private String advertisement;
    private String imagePath;
    private String brand;
    private String category;
    private Double productPrice;
}
