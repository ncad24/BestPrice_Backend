package com.upc.trabajoarquitectura.respositories;

import com.upc.trabajoarquitectura.dtos.querys.PricesByProductSupermarketsDTO;
import com.upc.trabajoarquitectura.dtos.querys.ProductsPerUserDTO;
import com.upc.trabajoarquitectura.entities.ProductsByList;
import com.upc.trabajoarquitectura.util.ProductsByListID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProductsByListRepository extends JpaRepository<ProductsByList, ProductsByListID> {
    public ProductsByList findByPrimaryKey_UserList_ListIdAndPrimaryKey_Product_ProductId(Long userListId, Long productId);
    @Query("SELECT new com.upc.trabajoarquitectura.dtos.querys.ProductsPerUserDTO(" +
            "pbl.primaryKey.product.productId, " +
            "pbl.primaryKey.product.name, " +
            "pbl.primaryKey.product.description, " +
            "pbl.primaryKey.product.advertisement, " +
            "pbl.primaryKey.product.imagePath, " +
            "pbl.primaryKey.product.brand.name, " +
            "pbl.primaryKey.product.category.name, " +
            "MIN(pxs.price)) " +
            "FROM ProductsByList pbl " +
            "JOIN ProductsBySupermarket pxs ON pxs.primaryKey.product.productId = pbl.primaryKey.product.productId " +
            "WHERE pbl.primaryKey.userList.listId = :listId " +
            "GROUP BY pbl.primaryKey.product.productId, pbl.primaryKey.product.name, pbl.primaryKey.product.description, " +
            "pbl.primaryKey.product.advertisement, pbl.primaryKey.product.imagePath, pbl.primaryKey.product.brand.name, " +
            "pbl.primaryKey.product.category.name")
    public List<ProductsPerUserDTO> findProductsByUserId(@Param("listId") Long listId);
    @Query("SELECT new com.upc.trabajoarquitectura.dtos.querys.PricesByProductSupermarketsDTO(pxs.primaryKey.supermarket.name, SUM(pxs.price), pxs.primaryKey.supermarket.imagePath)" +
            "FROM ProductsByList pbl " +
            "JOIN ProductsBySupermarket pxs ON pxs.primaryKey.product.productId = pbl.primaryKey.product.productId " +
            "WHERE pbl.primaryKey.userList.listId = :listId " +
            "GROUP BY pxs.primaryKey.supermarket.name, pxs.primaryKey.supermarket.imagePath")
    List<PricesByProductSupermarketsDTO> findTotalPricesBySupermarketForUserList(@Param("listId") Long listId);
}
