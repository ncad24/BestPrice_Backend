package com.upc.trabajoarquitectura.controllers;


import com.upc.trabajoarquitectura.dtos.querys.ListProductsBySupermarketDTO;
import com.upc.trabajoarquitectura.dtos.querys.PricesByProductSupermarketsDTO;
import com.upc.trabajoarquitectura.dtos.entities.ProductsBySupermarketDTO;
import com.upc.trabajoarquitectura.dtos.querys.ProductPriceDTO;
import com.upc.trabajoarquitectura.dtos.querys.SupermarketProductsPricesDTO;
import com.upc.trabajoarquitectura.entities.ProductsBySupermarket;
import com.upc.trabajoarquitectura.servicies.ProductsBySupermarketService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@CrossOrigin(origins = {"http://localhost:4200","http://18.223.169.236/"})
@RestController
@RequestMapping("/api")
public class ProductsBySupermarketController {
    @Autowired
    private ProductsBySupermarketService productsBySupermarketService;

    @GetMapping("/products/supermarket")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public List<ProductsBySupermarketDTO> getProductsBySupermarket() {
        ModelMapper mapper = new ModelMapper();
        List<ProductsBySupermarket> productsBySupermarkets = productsBySupermarketService.getProductsBySupermarket();
        List<ProductsBySupermarketDTO> productsBySupermarketDTOS = Arrays.asList(mapper.map(productsBySupermarkets, ProductsBySupermarketDTO[].class));
        return productsBySupermarketDTOS;
    }

    @PostMapping("/product/supermarket/{productID}/{supermarketID}/{price}")
    @PreAuthorize("hasRole('ADMIN')")
    public void registerProductsBySupermarket(@PathVariable Long productID, @PathVariable Long supermarketID, @PathVariable double price){
        productsBySupermarketService.assignProductsBySupermarket(productID, supermarketID, price);
    }
    @DeleteMapping("/product/supermarket/{productID}/{supermarketID}")
    @PreAuthorize("hasRole('ADMIN')")
    public void deleteProductsBySupermarket(@PathVariable Long productID, @PathVariable Long supermarketID){
        productsBySupermarketService.unassignProductBySupermarket(productID, supermarketID);
    }

    @PutMapping("/product/supermarket/{productId}/{supermarketId}/{newPrice}")
    @PreAuthorize("hasRole('ADMIN')")
    public void updateProductPrice(@PathVariable Long productId, @PathVariable Long supermarketId, @PathVariable double newPrice) {
        productsBySupermarketService.updateProductPrice(productId, supermarketId, newPrice);
    }

    @GetMapping("/products/supermarkets/{supermarketId}")
    @PreAuthorize("hasRole('ADMIN')")
    public List<ListProductsBySupermarketDTO> findProductsBySupermarket(@PathVariable Long supermarketId){
        return productsBySupermarketService.findProductsBySupermarketId(supermarketId);
    }

    @GetMapping("/top5-lowest-price")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<ProductPriceDTO>> getTop5LowestPriceProducts() {
        return ResponseEntity.ok(productsBySupermarketService.findTop5ProductsByLowestPrice());
    }

    @GetMapping("/products/supermarkets/{supermarketId}/{productName}")
    @PreAuthorize("hasRole('ADMIN')")
    public List<ListProductsBySupermarketDTO> findProductsBySupermarket(@PathVariable Long supermarketId, @PathVariable String productName){
        return productsBySupermarketService.findProductsBySupermarketAndProductName(supermarketId, productName);
    }

    @GetMapping("/products/supermarkets/prices")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public List<PricesByProductSupermarketsDTO> getPricesProductsBySupermarket(@RequestParam Long productID){
        return productsBySupermarketService.findProductPRicesForEachSupermarket(productID);
    }

    @GetMapping("/products/supermarkets/pricesProducts")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public List<SupermarketProductsPricesDTO> getMinPriceProducts(){
        return productsBySupermarketService.findMinPricesForProductsBySupermarket();
    }
    @GetMapping("/products/supermarkets/pricesProducts/asc")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public List<SupermarketProductsPricesDTO> getMinPriceProductsASC(){
        return productsBySupermarketService.findMinPricesForProductsBySupermarketOrderAsc();
    }
    @GetMapping("/products/supermarkets/pricesProducts/{priceMIN}/{priceMAX}")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public List<SupermarketProductsPricesDTO> getProductsBetweenMINMAX(@RequestParam double priceMIN, @RequestParam double priceMAX){
        return productsBySupermarketService.findMinPricesForProductsBySupermarketBetweenPrices(priceMIN, priceMAX);
    }
    @GetMapping("/products/supermarkets/filter")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public List<SupermarketProductsPricesDTO> getProductsByFilters(
            @RequestParam(required = false) String productName,
            @RequestParam(required = false) String brandName,
            @RequestParam(required = false) String categoryName,
            @RequestParam(required = false) Double minPrice,
            @RequestParam(required = false) Double maxPrice,
            @RequestParam(required = false) String supermarketName,
            @RequestParam(required = false) String sortOption) {
        return productsBySupermarketService.findProductsWithFilters(productName, brandName, categoryName, minPrice, maxPrice, supermarketName, sortOption);
    }
}