package com.upc.trabajoarquitectura.controllers;

import com.upc.trabajoarquitectura.dtos.entities.ProductClickDTO;
import com.upc.trabajoarquitectura.dtos.entities.ProductDTO;
import com.upc.trabajoarquitectura.entities.Product;
import com.upc.trabajoarquitectura.entities.ProductClick;
import com.upc.trabajoarquitectura.interfaces.IProductClickService;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Slf4j
@CrossOrigin(origins = {"http://localhost:4200","http://18.223.169.236/"})
@RestController
@RequestMapping("/api")
public class ProductClickController {
    @Autowired
    private IProductClickService productClickService;

    @GetMapping("/products/click")
    @PreAuthorize("hasRole('ADMIN')")
    public List<ProductClickDTO> getProductClicks(){
        ModelMapper mapper = new ModelMapper();
        List<ProductClick> productsClick = productClickService.getProductClicks();
        List<ProductClickDTO> productClickDTO = Arrays.asList(mapper.map(productsClick, ProductClickDTO[].class));
        return productClickDTO;
    }

    @PostMapping("/productClick/{nombre}")
    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    public void createProductClick(@PathVariable String nombre){
        productClickService.createProductClick(nombre);
    }

    @PutMapping("/productClick")
    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    public void incrementClick(@RequestParam Long id) {
        productClickService.incrementclick(id);
    }

    @GetMapping("/product/click/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Optional<ProductClick>> getProductClickById(@PathVariable Long id){
        return ResponseEntity.ok(productClickService.findById(id));
    }

    @GetMapping("/product/click/findTop")
    @PreAuthorize("hasRole('ADMIN')")
    public List<ProductClick> findTopBy(){
        return productClickService.findTopBy();
    }
}
