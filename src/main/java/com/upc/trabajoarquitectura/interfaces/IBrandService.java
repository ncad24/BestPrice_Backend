package com.upc.trabajoarquitectura.interfaces;

import com.upc.trabajoarquitectura.entities.Brand;

import java.util.List;

public interface IBrandService {
    public List<Brand> getBrands();
    public Brand registerBrand(Brand brand);
    public Brand updateBrand(Brand brand);
    public void deleteBrand(Long brandId) throws Exception;
    public Long countTotalsBrand();
    public Brand findBrandById(Long brandId);
    public List<Brand> findByNameStartsWith(String name);
}
