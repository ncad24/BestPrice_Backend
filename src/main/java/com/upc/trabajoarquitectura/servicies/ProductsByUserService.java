package com.upc.trabajoarquitectura.servicies;

import com.upc.trabajoarquitectura.entities.ProductsByUser;
import com.upc.trabajoarquitectura.exceptions.RequestException;
import com.upc.trabajoarquitectura.interfaces.IProductsByUserService;
import com.upc.trabajoarquitectura.respositories.ProductRepository;
import com.upc.trabajoarquitectura.respositories.ProductsByUserRepository;
import com.upc.trabajoarquitectura.respositories.UserAppRepository;
import com.upc.trabajoarquitectura.util.ValidationService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class ProductsByUserService implements IProductsByUserService {
    @Autowired
    private ProductsByUserRepository productsByUserRepository;
    @Autowired
    private UserAppRepository userAppRepository;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private ValidationService validationService;

    @Transactional
    public List<ProductsByUser> getProductsByUser() {
        return productsByUserRepository.findAll();
    }

    public void verifyProductUser(Long productId, Long userId){
        validationService.checkForDuplicate(productsByUserRepository.existsByPrimaryKey_Product_ProductIdAndPrimaryKey_UserApp_UserId(productId, userId), "ProductoXUsuario");
        validationService.verifyExistsID(productRepository.existsById(productId), "Producto");
        validationService.verifyExistsID(userAppRepository.existsById(userId), "Usuario");
    }

    @Transactional
    public void registerProductsByUser(Long productId, Long userId) {
        verifyProductUser(productId,userId);
        ProductsByUser productsByUser = productsByUserRepository.findByPrimaryKey_Product_ProductIdAndPrimaryKey_UserApp_UserId(productId, userId);
        LocalDate date = LocalDate.now();
        if (productsByUser != null) {
            productsByUser.setDate(date);
            productsByUserRepository.save(productsByUser);
        }else{
            ProductsByUser newProductsByUser = new ProductsByUser();
            newProductsByUser.setUserApp(userAppRepository.findById(userId).get());
            newProductsByUser.setProduct(productRepository.findById(productId).get());
            newProductsByUser.setDate(date);
            productsByUserRepository.save(newProductsByUser);
        }
    }

    public List<ProductsByUser> getProductsByUserId(Long userId) {
        return productsByUserRepository.findAllByPrimaryKey_UserApp_UserId(userId);
    }
}