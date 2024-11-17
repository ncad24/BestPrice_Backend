package com.upc.trabajoarquitectura.controllers;

import com.upc.trabajoarquitectura.dtos.querys.PricesByProductSupermarketsDTO;
import com.upc.trabajoarquitectura.dtos.querys.ProductsPerUserDTO;
import com.upc.trabajoarquitectura.entities.ProductsByList;
import com.upc.trabajoarquitectura.interfaces.IProductsByListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = {"http://localhost:4200","http://18.223.169.236/"})
@RestController
@RequestMapping("/api")
public class ProductsByListController {
    @Autowired
    private IProductsByListService productsByListService;

    @PostMapping("/product/list/{userListId}/{productId}")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public void registerProductsXList(@PathVariable Long userListId, @PathVariable Long productId){
        productsByListService.assignProductsByList(userListId, productId);
    }

    @DeleteMapping("/product/list/delete/{userListId}/{productId}")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public void deleteProductsXList(@PathVariable Long userListId, @PathVariable Long productId){
        productsByListService.unassignProductByList(userListId, productId);
    }

    @GetMapping("/product/list/findproducts/{listId}")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public List<ProductsPerUserDTO> findProductsOfUsers(@PathVariable Long listId){
        return productsByListService.findProductsByListPerUserId(listId);
    }

    @GetMapping("/product/list/sumlist/{listId}")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public List<PricesByProductSupermarketsDTO> sumListProducts(@PathVariable Long listId){
        return productsByListService.findTotalPricesBySupermarketForUserList(listId);
    }
}
