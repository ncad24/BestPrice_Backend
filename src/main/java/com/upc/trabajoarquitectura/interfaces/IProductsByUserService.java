package com.upc.trabajoarquitectura.interfaces;

import com.upc.trabajoarquitectura.entities.ProductsByUser;

import java.util.List;

public interface IProductsByUserService {
    public List<ProductsByUser> getProductsByUser();
    public void registerProductsByUser(Long productId, Long userId);
    public List<ProductsByUser> getProductsByUserId(Long userId);
}