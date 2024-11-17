package com.upc.trabajoarquitectura.dtos.querys;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class ProductPriceDTO {
    private String productName;
    private double price;
}
