package com.upc.trabajoarquitectura.interfaces;

import com.upc.trabajoarquitectura.dtos.querys.PricesByProductSupermarketsDTO;
import com.upc.trabajoarquitectura.dtos.querys.ProductsPerUserDTO;
import com.upc.trabajoarquitectura.entities.ProductsByList;

import java.util.List;

public interface IProductsByListService {
    public void assignProductsByList(Long userListId, Long productId);
    public void unassignProductByList(Long userListId, Long productId);
    public List<ProductsPerUserDTO> findProductsByListPerUserId(Long listId);
    public List<PricesByProductSupermarketsDTO> findTotalPricesBySupermarketForUserList(Long listId);
}
