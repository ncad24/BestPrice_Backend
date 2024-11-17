package com.upc.trabajoarquitectura.servicies;

import com.upc.trabajoarquitectura.entities.ProductClick;
import com.upc.trabajoarquitectura.entities.ProductsByUser;
import com.upc.trabajoarquitectura.exceptions.RequestException;
import com.upc.trabajoarquitectura.interfaces.IProductClickService;
import com.upc.trabajoarquitectura.respositories.ProductClickRepository;
import com.upc.trabajoarquitectura.respositories.ProductRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.PageRequest;
import java.util.List;
import java.util.Optional;

@Service
public class ProductClickService implements IProductClickService {
    @Autowired
    private ProductClickRepository productClickRepository;
    @Autowired
    private ProductRepository productRepository;

    @Transactional
    public void createProductClick(String nombre) {
        ProductClick productClick = new ProductClick();
        productClick.setName(nombre);
        productClickRepository.save(productClick);
    }

    @Override
    public List<ProductClick> getProductClicks() {
        return productClickRepository.findAll();
    }

    @Override
    public Optional<ProductClick> findById(Long Id) {
        if (productClickRepository.existsById(Id)){
            return productClickRepository.findById(Id);
        } else {
            throw new RequestException("E-006", HttpStatus.NOT_FOUND, "No existe el ID");
        }
    }

    @Override
    public List<ProductClick> findTopBy() {
        Pageable topFive = PageRequest.of(0, 5);
        return productClickRepository.findTop5ByOrderByClicksDesc(topFive);
    }

    @Override
    public void incrementclick(Long id) {
        if (productClickRepository.existsById(id)){
            ProductClick productClick = productClickRepository.findById(id).get();
            int increaseClicks = productClick.getClicks() + 1;
            productClick.setClicks(increaseClicks);
            productClickRepository.save(productClick);
        }
        else {
            throw new RequestException("E-006", HttpStatus.NOT_FOUND, "No existe el ID");
        }
    }
}
