package com.upc.trabajoarquitectura.dtos.querys;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class SupermarketProductsPricesDTO {
    private Long productId;
    private String productName;
    private String productDescription;
    private double productPrice;
    private String brandName;
    private String categoryName;
    private String supermarketName;
    private String imagePath;
}
