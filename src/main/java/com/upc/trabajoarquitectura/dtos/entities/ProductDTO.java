package com.upc.trabajoarquitectura.dtos.entities;

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
public class ProductDTO {
    private Long productId;
    private String name;
    private String description;
    private String advertisement;
    private String imagePath;
    private Brand brand;
    private Category category;
}
