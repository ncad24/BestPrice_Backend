package com.upc.trabajoarquitectura.respositories;

import com.upc.trabajoarquitectura.dtos.querys.ListProductsBySupermarketDTO;
import com.upc.trabajoarquitectura.dtos.querys.PricesByProductSupermarketsDTO;
import com.upc.trabajoarquitectura.dtos.querys.ProductPriceDTO;
import com.upc.trabajoarquitectura.dtos.querys.SupermarketProductsPricesDTO;
import com.upc.trabajoarquitectura.entities.ProductsBySupermarket;
import com.upc.trabajoarquitectura.util.ProductsBySupermarketID;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProductsBySupermarketRepository extends JpaRepository<ProductsBySupermarket, ProductsBySupermarketID> {
    public boolean existsByPrimaryKey_Product_ProductIdAndPrimaryKey_Supermarket_SupermarketId(Long supermarketId, Long productId);
    //Queries
    //Products by Supermarket
    @Query("SELECT new com.upc.trabajoarquitectura.dtos.querys.ListProductsBySupermarketDTO(" +
            "pxs.primaryKey.product.productId, pxs.primaryKey.product.name, pxs.price, pxs.primaryKey.product.imagePath) " +
            "FROM ProductsBySupermarket pxs " +
            "WHERE pxs.primaryKey.supermarket.supermarketId = :supermarketId")
    List<ListProductsBySupermarketDTO> findProductsBySupermarketId(@Param("supermarketId") Long supermarketId);

    //numero total productos por supermercado
    @Query("SELECT p.primaryKey.supermarket, COUNT(p.primaryKey.product) " +
            "FROM ProductsBySupermarket p " +
            "GROUP BY p.primaryKey.supermarket")
    List<ProductsBySupermarket[]> countTotalProductsBySupermarket();

    //Buscar productos por nombre de acuerdo a un supemercado
    @Query("SELECT new com.upc.trabajoarquitectura.dtos.querys.ListProductsBySupermarketDTO(" +
            "pxs.primaryKey.product.productId, pxs.primaryKey.product.name, pxs.price, pxs.primaryKey.product.imagePath) " +
            "FROM ProductsBySupermarket pxs " +
            "WHERE pxs.primaryKey.supermarket.supermarketId = :supermarketId " +
            "AND LOWER(pxs.primaryKey.product.name) LIKE LOWER(CONCAT('%', :productName, '%'))")
    List<ListProductsBySupermarketDTO> findProductsBySupermarketAndProductName(
            @Param("supermarketId") Long supermarketId,
            @Param("productName") String productName);
    //Top 5 supermercados con los productos con menor precio
    @Query("SELECT new com.upc.trabajoarquitectura.dtos.querys.ProductPriceDTO(p.primaryKey.product.name, MIN(p.price)) " +
            "FROM ProductsBySupermarket p " +
            "GROUP BY p.primaryKey.product.productId, p.primaryKey.product.name " +
            "ORDER BY MIN(p.price) ASC")
    List<ProductPriceDTO> findTop5ProductsByLowestPrice(Pageable pageable);

    /*
    Query para registrar
    US54: Como administrador quiero registrarme en la plataforma proporcionando mis datos personales,
    como nombre, correo electrónico y contraseña, para poder acceder al sistema de gestión.
    */
    public ProductsBySupermarket findByPrimaryKey_Product_ProductIdAndPrimaryKey_Supermarket_SupermarketId(Long productId, Long supermarketId);

    //Listar precios de un producto en varias tiendas
    /*
    US08: Como cliente quiero ver los precios de un producto en varias tiendas para poder elegir
    la opción más económica.

    */
    @Query("SELECT new com.upc.trabajoarquitectura.dtos.querys.PricesByProductSupermarketsDTO(pxs.primaryKey.supermarket.name, pxs.price, pxs.primaryKey.supermarket.imagePath) FROM ProductsBySupermarket pxs WHERE pxs.primaryKey.product.productId = :idProduct")
    public List<PricesByProductSupermarketsDTO> findPricesProductsForEachSupermarket(@Param("idProduct") Long productId);

    //Listar productos con menor precio por supermercado para mostrar en front
    @Query("SELECT new com.upc.trabajoarquitectura.dtos.querys.SupermarketProductsPricesDTO(pxs.primaryKey.product.productId, pxs.primaryKey.product.name, pxs.primaryKey.product.description, MIN(pxs.price), pxs.primaryKey.product.brand.name, pxs.primaryKey.product.category.name, pxs.primaryKey.supermarket.name, pxs.primaryKey.product.imagePath) FROM ProductsBySupermarket pxs GROUP BY pxs.primaryKey.product.productId, pxs.primaryKey.product.name, pxs.primaryKey.product.brand.name, pxs.primaryKey.product.description, pxs.primaryKey.product.category.name, pxs.primaryKey.supermarket.name, pxs.primaryKey.product.imagePath")
    public List<SupermarketProductsPricesDTO> findMinPricesForProductsBySupermarket();

    //Listar productos con menor precio por supermercado para mostrar en front
    @Query("SELECT new com.upc.trabajoarquitectura.dtos.querys.SupermarketProductsPricesDTO(pxs.primaryKey.product.productId, pxs.primaryKey.product.name, pxs.primaryKey.product.description, MIN(pxs.price), pxs.primaryKey.product.brand.name, pxs.primaryKey.product.category.name, pxs.primaryKey.supermarket.name, pxs.primaryKey.product.imagePath) FROM ProductsBySupermarket pxs WHERE pxs.price BETWEEN :minPrice AND :maxPrice GROUP BY pxs.primaryKey.product.productId, pxs.primaryKey.product.name, pxs.primaryKey.product.description, pxs.primaryKey.product.brand.name, pxs.primaryKey.product.category.name, pxs.primaryKey.supermarket.name, pxs.primaryKey.product.imagePath")
    public List<SupermarketProductsPricesDTO> findMinPricesForProductsBySupermarketBetweenPrices(@Param("minPrice") double minPrice, @Param("maxPrice") double maxPrice);

    //Listar productos con menor precio por supermercado para mostrar en front ordenados por menor precio
    @Query("SELECT new com.upc.trabajoarquitectura.dtos.querys.SupermarketProductsPricesDTO(pxs.primaryKey.product.productId, pxs.primaryKey.product.name, pxs.primaryKey.product.description, MIN(pxs.price), pxs.primaryKey.product.brand.name, pxs.primaryKey.product.category.name, pxs.primaryKey.supermarket.name, pxs.primaryKey.product.imagePath) FROM ProductsBySupermarket pxs " +
            "GROUP BY pxs.primaryKey.product.productId, pxs.primaryKey.product.name " +
            "ORDER BY MIN(pxs.price) ASC")
    public List<SupermarketProductsPricesDTO> findMinPricesForProductsBySupermarketOrderAsc();

    @Query("SELECT new com.upc.trabajoarquitectura.dtos.querys.SupermarketProductsPricesDTO(" +
            "pxs.primaryKey.product.productId, pxs.primaryKey.product.name, pxs.primaryKey.product.description, " +
            "MIN(pxs.price), pxs.primaryKey.product.brand.name, pxs.primaryKey.product.category.name, " +
            "MIN(pxs.primaryKey.supermarket.name), pxs.primaryKey.product.imagePath) " +
            "FROM ProductsBySupermarket pxs " +
            "WHERE (:productName IS NULL OR :productName = '' OR pxs.primaryKey.product.name ILIKE CONCAT('%', :productName, '%')) " +
            "AND (:brandName IS NULL OR :brandName = '' OR pxs.primaryKey.product.brand.name ILIKE :brandName) " +
            "AND (:categoryName IS NULL OR :categoryName = '' OR pxs.primaryKey.product.category.name ILIKE :categoryName) " +
            "AND (:minPrice IS NULL OR pxs.price >= :minPrice) " +
            "AND (:maxPrice IS NULL OR pxs.price <= :maxPrice) " +
            "AND (:supermarketName IS NULL OR :supermarketName = '' OR pxs.primaryKey.supermarket.name ILIKE :supermarketName) " +
            "GROUP BY pxs.primaryKey.product.productId, pxs.primaryKey.product.name, pxs.primaryKey.product.description, " +
            "pxs.primaryKey.product.brand.name, pxs.primaryKey.product.category.name, pxs.primaryKey.product.imagePath " +
            "ORDER BY CASE WHEN :sortOption = 'lowest' THEN MIN(pxs.price) END ASC, " +
            "CASE WHEN :sortOption = 'highest' THEN MIN(pxs.price) END DESC, " +
            "CASE WHEN :sortOption = '' THEN pxs.primaryKey.product.name END ASC")  // Default sorting if empty
    public List<SupermarketProductsPricesDTO> findProductsWithFilters(
            @Param("productName") String productName,
            @Param("brandName") String brandName,
            @Param("categoryName") String categoryName,
            @Param("minPrice") Double minPrice,
            @Param("maxPrice") Double maxPrice,
            @Param("supermarketName") String supermarketName,
            @Param("sortOption") String sortOption);
}
