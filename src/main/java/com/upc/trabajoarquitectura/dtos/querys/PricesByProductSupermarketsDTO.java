package com.upc.trabajoarquitectura.dtos.querys;

import com.upc.trabajoarquitectura.entities.Brand;
import com.upc.trabajoarquitectura.entities.Category;
import com.upc.trabajoarquitectura.entities.Supermarket;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class PricesByProductSupermarketsDTO {
    private String supermarketName;
    private double productPrice;
    private String imagePath;
}
