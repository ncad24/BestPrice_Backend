package com.upc.trabajoarquitectura.dtos.querys;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class ListProductsBySupermarketDTO {
    private Long productId;
    private String productName;
    private Double productPrice;
    private String imagePath;
}
