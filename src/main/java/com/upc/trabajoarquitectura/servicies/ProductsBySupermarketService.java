package com.upc.trabajoarquitectura.servicies;

import com.upc.trabajoarquitectura.dtos.querys.ListProductsBySupermarketDTO;
import com.upc.trabajoarquitectura.dtos.querys.PricesByProductSupermarketsDTO;
import com.upc.trabajoarquitectura.dtos.querys.ProductPriceDTO;
import com.upc.trabajoarquitectura.dtos.querys.SupermarketProductsPricesDTO;
import com.upc.trabajoarquitectura.entities.ProductsBySupermarket;
import com.upc.trabajoarquitectura.exceptions.RequestException;
import com.upc.trabajoarquitectura.interfaces.IProductsBySupermarketService;
import com.upc.trabajoarquitectura.respositories.ProductRepository;
import com.upc.trabajoarquitectura.respositories.ProductsBySupermarketRepository;
import com.upc.trabajoarquitectura.respositories.SupermarketRepository;
import com.upc.trabajoarquitectura.util.ValidationService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
@Service
public class ProductsBySupermarketService implements IProductsBySupermarketService {
    @Autowired
    private ProductsBySupermarketRepository productsBySupermarketRepository;
    @Autowired
    private SupermarketRepository supermarketRepository;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private ValidationService validationService;

    @Transactional
    public List<ProductsBySupermarket> getProductsBySupermarket() {
        return productsBySupermarketRepository.findAll();
    }

    public void verifyProductsBySupermarket(Long productId, Long supermarketId, double price) {
        if (price < 0) {
            throw new RequestException("PS-001", HttpStatus.BAD_REQUEST, "El precio no puede ser negativo.");
        }

        validationService.verifyRepeatedAssign(productsBySupermarketRepository.existsByPrimaryKey_Product_ProductIdAndPrimaryKey_Supermarket_SupermarketId(productId, supermarketId));
        //throw new RequestException("PS002", HttpStatus.CONFLICT, "El producto ya está registrado en este supermercado.");
        validationService.verifyExistsID(productRepository.existsById(productId), "Producto");
        //throw new RequestException("PS003", HttpStatus.NOT_FOUND, "El producto con el ID proporcionado no existe.");
        validationService.verifyExistsID(supermarketRepository.existsById(supermarketId), "Supermercado");
        //throw new RequestException("PS004", HttpStatus.NOT_FOUND, "El supermercado con el ID proporcionado no existe.");

    }

    @Transactional
    public void assignProductsBySupermarket(Long productId, Long supermarketId, double price) {
        verifyProductsBySupermarket(productId, supermarketId, price);
        ProductsBySupermarket productsBySupermarket = productsBySupermarketRepository.findByPrimaryKey_Product_ProductIdAndPrimaryKey_Supermarket_SupermarketId(productId, supermarketId);
        LocalDate date = LocalDate.now();
        if (productsBySupermarket != null) {
            productsBySupermarket.setDate(date);
            productsBySupermarket.setPrice(price);
            productsBySupermarketRepository.save(productsBySupermarket);
        }else{
            ProductsBySupermarket newProductsBySupermarket = new ProductsBySupermarket();
            newProductsBySupermarket.setSupermarket(supermarketRepository.findById(supermarketId).get());
            newProductsBySupermarket.setProduct(productRepository.findById(productId).get());
            newProductsBySupermarket.setDate(date);
            newProductsBySupermarket.setPrice(price);

            productsBySupermarketRepository.save(newProductsBySupermarket);
        }
    }

    @Transactional
    public void unassignProductBySupermarket(Long productId, Long supermarketId) {
        ProductsBySupermarket productsBySupermarket = productsBySupermarketRepository
                .findByPrimaryKey_Product_ProductIdAndPrimaryKey_Supermarket_SupermarketId(productId, supermarketId);

        if (productsBySupermarket != null) {
            productsBySupermarketRepository.delete(productsBySupermarket);
            //System.out.println("Producto desasignado correctamente del supermercado.");
        } else {
            throw new RequestException("P005", HttpStatus.NOT_FOUND,
                    "La relación entre el producto y el supermercado no existe.");
        }
    }

    @Transactional
    public void updateProductPrice(Long productId, Long supermarketId, double newPrice) {
        // Verificar si el precio es válido
        if (newPrice < 0) {
            throw new RequestException("PS-004", HttpStatus.BAD_REQUEST, "El precio no puede ser negativo.");
        }

        // Verificar si existe la relación entre producto y supermercado
        ProductsBySupermarket productsBySupermarket = productsBySupermarketRepository.findByPrimaryKey_Product_ProductIdAndPrimaryKey_Supermarket_SupermarketId(productId, supermarketId);

        if (productsBySupermarket == null) {
            throw new RequestException("P006", HttpStatus.NOT_FOUND, "La relación entre el producto y el supermercado no existe.");
        }

        // Actualizar el precio y la fecha
        productsBySupermarket.setPrice(newPrice);
        productsBySupermarket.setDate(LocalDate.now());

        // Guardar la actualización
        productsBySupermarketRepository.save(productsBySupermarket);
    }

    @Override
    public List<ListProductsBySupermarketDTO> findProductsBySupermarketId(Long supermarketId) {
        return productsBySupermarketRepository.findProductsBySupermarketId(supermarketId);
    }
    @Override
    public List<ProductPriceDTO> findTop5ProductsByLowestPrice(){
        Pageable pageable = PageRequest.of(0, 5);
        return productsBySupermarketRepository.findTop5ProductsByLowestPrice(pageable);
    }
    @Override
    public List<ListProductsBySupermarketDTO> findProductsBySupermarketAndProductName(Long supermarketId, String productName){
        return productsBySupermarketRepository.findProductsBySupermarketAndProductName(supermarketId, productName);
    }
    @Override
    public List<PricesByProductSupermarketsDTO> findProductPRicesForEachSupermarket(Long productId) {
        return productsBySupermarketRepository.findPricesProductsForEachSupermarket(productId);
    }

    @Override
    public List<SupermarketProductsPricesDTO> findMinPricesForProductsBySupermarket() {
        return productsBySupermarketRepository.findMinPricesForProductsBySupermarket();
    }

    @Override
    public List<SupermarketProductsPricesDTO> findMinPricesForProductsBySupermarketOrderAsc() {
        return productsBySupermarketRepository.findMinPricesForProductsBySupermarketOrderAsc();
    }

    @Override
    public List<SupermarketProductsPricesDTO> findMinPricesForProductsBySupermarketBetweenPrices(double minPrice, double maxPrice){
        return productsBySupermarketRepository.findMinPricesForProductsBySupermarketBetweenPrices(minPrice, maxPrice);
    }

    @Override
    public List<SupermarketProductsPricesDTO> findProductsWithFilters(String productName, String brandName, String categoryName, Double minPrice, Double maxPrice, String supermarketName, String sortOption) {
        return productsBySupermarketRepository.findProductsWithFilters(productName, brandName, categoryName, minPrice, maxPrice, supermarketName, sortOption);
    }

}
