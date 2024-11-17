package com.upc.trabajoarquitectura.interfaces;

import com.upc.trabajoarquitectura.dtos.querys.ListProductsBySupermarketDTO;
import com.upc.trabajoarquitectura.dtos.querys.PricesByProductSupermarketsDTO;
import com.upc.trabajoarquitectura.dtos.querys.ProductPriceDTO;
import com.upc.trabajoarquitectura.dtos.querys.SupermarketProductsPricesDTO;
import com.upc.trabajoarquitectura.entities.ProductsBySupermarket;

import java.util.List;

public interface IProductsBySupermarketService {
    public List<ProductsBySupermarket> getProductsBySupermarket();
    public void assignProductsBySupermarket(Long productId, Long supermarketId, double price);
    public void unassignProductBySupermarket(Long productId, Long supermarketId);
    public void updateProductPrice(Long productId, Long supermarketId, double newPrice);
    public List<ListProductsBySupermarketDTO> findProductsBySupermarketId(Long supermarketId);
    public List<ProductPriceDTO> findTop5ProductsByLowestPrice();
    public List<ListProductsBySupermarketDTO> findProductsBySupermarketAndProductName(Long supermarketId, String productName);
    public List<PricesByProductSupermarketsDTO> findProductPRicesForEachSupermarket(Long idProduct);
    public List<SupermarketProductsPricesDTO> findMinPricesForProductsBySupermarket();
    public List<SupermarketProductsPricesDTO> findMinPricesForProductsBySupermarketOrderAsc();
    public List<SupermarketProductsPricesDTO> findMinPricesForProductsBySupermarketBetweenPrices(double minPrice, double maxPrice);
    public List<SupermarketProductsPricesDTO> findProductsWithFilters(String productName, String brandName, String categoryName, Double minPrice, Double maxPrice, String supermarketName, String sortOption);
}