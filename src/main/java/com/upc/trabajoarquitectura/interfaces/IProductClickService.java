package com.upc.trabajoarquitectura.interfaces;

import com.upc.trabajoarquitectura.entities.ProductClick;

import java.util.List;
import java.util.Optional;

public interface IProductClickService {
    public void createProductClick(String nombre);
    public List<ProductClick> getProductClicks();
    public Optional<ProductClick> findById(Long Id);
    public List<ProductClick> findTopBy();
    public void incrementclick(Long id);
}
