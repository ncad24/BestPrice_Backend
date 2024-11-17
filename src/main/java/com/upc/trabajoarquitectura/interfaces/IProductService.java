package com.upc.trabajoarquitectura.interfaces;

import com.upc.trabajoarquitectura.entities.Product;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface IProductService {
    public List<Product> getProducts();
    public Product  registerProduct(Product product, Long brandId, Long categoryId, MultipartFile image) throws IOException;
    public Product updateProduct(Product product, MultipartFile image) throws IOException;
    public void deleteProduct(Long productId);
    public Product findProductByID(Long productId);
    public Long countTotalProducts();
    public List<Product> findByProductName(String productName);
    public List<Product> findByCategoryOfProduct(String categoryName);
    public List<Product> findByBrandOfProduct(String brandName);
}
