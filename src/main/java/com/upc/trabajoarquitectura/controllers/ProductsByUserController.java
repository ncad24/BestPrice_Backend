package com.upc.trabajoarquitectura.controllers;

import com.upc.trabajoarquitectura.dtos.entities.ProductsByUserDTO;
import com.upc.trabajoarquitectura.entities.ProductsByUser;
import com.upc.trabajoarquitectura.servicies.ProductsByUserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@CrossOrigin(origins = {"http://localhost:4200","http://18.223.169.236/"})
@RestController
@RequestMapping("/api")
public class ProductsByUserController {
    @Autowired
    private ProductsByUserService productxUserService;

    @GetMapping("/products/users")
    public List<ProductsByUserDTO> getProductsxUser() {
        ModelMapper mapper = new ModelMapper();
        List<ProductsByUser> productsByUsers = productxUserService.getProductsByUser();
        List<ProductsByUserDTO> productsByUserDTOS = Arrays.asList(mapper.map(productsByUsers, ProductsByUserDTO[].class));
        return productsByUserDTOS;
    }

    @PostMapping("/product/user/{productID}/{userID}")
    public void registerProductsxUser(@PathVariable Long productID, @PathVariable Long userID){
        productxUserService.registerProductsByUser(productID, userID);
    }

    @GetMapping("/products/users/{userId}")
    public List<ProductsByUserDTO> getProductsByUserId(@PathVariable Long userId) {
        ModelMapper mapper = new ModelMapper();
        List<ProductsByUser> productsByUser = productxUserService.getProductsByUserId(userId);
        return Arrays.asList(mapper.map(productsByUser, ProductsByUserDTO[].class));
    }
}
