package com.upc.trabajoarquitectura.interfaces;

import com.upc.trabajoarquitectura.entities.Supermarket;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

public interface ISupermarketService {
    public List<Supermarket> getSupermarkets();
    public Supermarket registerSupermarket(Supermarket supermarket, MultipartFile image) throws IOException;
    public Supermarket updateSupermarket(Supermarket supermarket);
    public void deleteSupermarket(Long supermarketId);
    public Long countTotalsSupermarket();
    public Supermarket findSupermarketById(Long supermarketId);
    public List<Supermarket> findByNameStartsWith(String supermarketName);
}
