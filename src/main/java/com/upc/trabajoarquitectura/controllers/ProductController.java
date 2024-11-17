package com.upc.trabajoarquitectura.controllers;

import com.upc.trabajoarquitectura.dtos.entities.ProductDTO;
import com.upc.trabajoarquitectura.entities.Product;
import com.upc.trabajoarquitectura.interfaces.IBrandService;
import com.upc.trabajoarquitectura.interfaces.ICategoryService;
import com.upc.trabajoarquitectura.interfaces.IProductService;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Arrays;
import java.util.List;

@Slf4j
@CrossOrigin(origins = {"http://localhost:4200","http://18.223.169.236/"})
@RestController
@RequestMapping("/api")
public class ProductController {
    @Autowired
    private IProductService productService;

    @Autowired
    private ICategoryService categoryService;

    @Autowired
    private IBrandService brandService;

    @GetMapping("/products")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public List<ProductDTO> getProducts(){
        ModelMapper mapper = new ModelMapper();
        List<Product> products = productService.getProducts();
        List<ProductDTO> productDTO = Arrays.asList(mapper.map(products, ProductDTO[].class));
        return productDTO;
    }

    @PostMapping("/product")
    @PreAuthorize("hasRole('ADMIN')")
    public ProductDTO registerProduct(@ModelAttribute ProductDTO productDTO,
                                      @RequestParam(value = "image", required = false) MultipartFile image/*},
                                      @RequestParam Long categoryID,
                                      @RequestParam Long brandID*/) throws Exception {
        log.info(productDTO.getCategory().getCategoryId().toString());
        log.info(productDTO.getBrand().getBrandId().toString());

        ModelMapper mapper = new ModelMapper();
        Product product = mapper.map(productDTO, Product.class);
        product = productService.registerProduct(product, product.getBrand().getBrandId(), product.getCategory().getCategoryId(), image);
        productDTO = mapper.map(product, ProductDTO.class);
        return productDTO;
    }

    @PutMapping("/product/update")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ProductDTO> updateProducts(@ModelAttribute ProductDTO productDTO,
                                                     @RequestParam(value = "image", required = false) MultipartFile image) throws Exception {
        ModelMapper mapper = new ModelMapper();
        Product product = mapper.map(productDTO, Product.class);
        product = productService.updateProduct(product, image);
        productDTO = mapper.map(product, ProductDTO.class);
        return ResponseEntity.ok(productDTO);
    }

    @DeleteMapping("/product/delete/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public void deleteProducts(@PathVariable Long id) {
        productService.deleteProduct(id);
    }

    @GetMapping("/product/id/{id}")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public ResponseEntity<Product> getProductById(@PathVariable Long id) {
        return ResponseEntity.ok(productService.findProductByID(id));
    }

    @GetMapping("/products/count")
    @PreAuthorize("hasRole('ADMIN')")
    public Long countTotalProductsBySupermarket() {
        return productService.countTotalProducts();
    }

    @GetMapping("/products/searchName")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public List<ProductDTO> getProductsByName(@RequestParam String productName){
        ModelMapper mapper = new ModelMapper();
        List<Product> products = productService.findByProductName(productName);
        List<ProductDTO> productDTO = Arrays.asList(mapper.map(products, ProductDTO[].class));
        return productDTO;
    }

    @GetMapping("/products/searchCategory")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public List<ProductDTO> getProductsByCategoryName(@RequestParam String categoryName){
        ModelMapper mapper = new ModelMapper();
        List<Product> products = productService.findByCategoryOfProduct(categoryName);
        List<ProductDTO> productDTO = Arrays.asList(mapper.map(products, ProductDTO[].class));
        return productDTO;
    }

    @GetMapping("/products/searchBrand")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public List<ProductDTO> getProductsByBrandName(@RequestParam String brandName){
        ModelMapper mapper = new ModelMapper();
        List<Product> products = productService.findByBrandOfProduct(brandName);
        List<ProductDTO> productDTO = Arrays.asList(mapper.map(products, ProductDTO[].class));
        return productDTO;
    }
}
