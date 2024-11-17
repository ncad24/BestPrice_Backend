package com.upc.trabajoarquitectura.servicies;

import com.upc.trabajoarquitectura.dtos.querys.PricesByProductSupermarketsDTO;
import com.upc.trabajoarquitectura.dtos.querys.ProductsPerUserDTO;
import com.upc.trabajoarquitectura.entities.ProductsByList;
import com.upc.trabajoarquitectura.entities.ProductsBySupermarket;
import com.upc.trabajoarquitectura.entities.ProductsByUser;
import com.upc.trabajoarquitectura.exceptions.RequestException;
import com.upc.trabajoarquitectura.interfaces.IProductsByListService;
import com.upc.trabajoarquitectura.respositories.ProductRepository;
import com.upc.trabajoarquitectura.respositories.ProductsByListRepository;
import com.upc.trabajoarquitectura.respositories.UserAppRepository;
import com.upc.trabajoarquitectura.respositories.UserListRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class ProductsByListService implements IProductsByListService {
    @Autowired
    private ProductsByListRepository productsByListRepository;
    @Autowired
    private UserListRepository userListRepository;
    @Autowired
    private ProductRepository productRepository;

    @Transactional
    @Override
    public void assignProductsByList(Long userListId, Long productId) {
        ProductsByList productsByList = productsByListRepository.findByPrimaryKey_UserList_ListIdAndPrimaryKey_Product_ProductId(userListId, productId);
        LocalDate date = LocalDate.now();
        if (productsByList != null) {
            productsByList.setDateAdded(date);
            productsByListRepository.save(productsByList);
        }else{
            ProductsByList newProductsByUser = new ProductsByList();
            newProductsByUser.setUserList(userListRepository.findById(userListId).get());
            newProductsByUser.setProduct(productRepository.findById(productId).get());
            newProductsByUser.setDateAdded(date);
            productsByListRepository.save(newProductsByUser);
        }
    }

    @Transactional
    @Override
    public void unassignProductByList(Long userListId, Long productId) {
        ProductsByList productsByList = productsByListRepository
                .findByPrimaryKey_UserList_ListIdAndPrimaryKey_Product_ProductId(userListId, productId);

        if (productsByList != null) {
            productsByListRepository.delete(productsByList);
        } else {
            throw new RequestException("P005", HttpStatus.NOT_FOUND,
                    "La relación entre la lista y el producto no existe.");
        }
    }

    @Override
    public List<ProductsPerUserDTO> findProductsByListPerUserId(Long listId) {
        return productsByListRepository.findProductsByUserId(listId);
    }

    @Override
    public List<PricesByProductSupermarketsDTO> findTotalPricesBySupermarketForUserList(Long listId){
        return productsByListRepository.findTotalPricesBySupermarketForUserList(listId);
    }

}
