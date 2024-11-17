package com.upc.trabajoarquitectura.respositories;

import com.upc.trabajoarquitectura.entities.ProductsByUser;
import com.upc.trabajoarquitectura.util.ProductsByUserID;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductsByUserRepository extends JpaRepository<ProductsByUser, ProductsByUserID> {
    //Querys
    public ProductsByUser findByPrimaryKey_Product_ProductIdAndPrimaryKey_UserApp_UserId(Long productId, Long userId);
    public boolean existsByPrimaryKey_Product_ProductIdAndPrimaryKey_UserApp_UserId(Long productId, Long userId);
    public List<ProductsByUser> findAllByPrimaryKey_UserApp_UserId(Long userId);
}
