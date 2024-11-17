package com.upc.trabajoarquitectura.servicies;


import com.upc.trabajoarquitectura.entities.Brand;
import com.upc.trabajoarquitectura.exceptions.RequestException;
import com.upc.trabajoarquitectura.interfaces.IBrandService;
import com.upc.trabajoarquitectura.respositories.BrandRepository;
import com.upc.trabajoarquitectura.util.ValidationService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class BrandService implements IBrandService {
    @Autowired
    private BrandRepository brandRepository;

    @Autowired
    private ValidationService validationService;

    @Transactional
    @Override
    public List<Brand> getBrands() {
        return brandRepository.findAll();
    }

    public void verifyBrand(Brand brand){
        validationService.verifyNoNulls(brand.getName());
        validationService.verifyNoEmpty(brand.getName());
        validationService.verifyOnlyLetters(brand.getName());
        validationService.verifyLength(brand.getName(),3,50);
        validationService.checkForDuplicate(brandRepository.existsByName(brand.getName()), "Marca");
    }

    @Transactional
    @Override
    public Brand registerBrand(Brand brand) {
        verifyBrand(brand);
        return brandRepository.save(brand);
    }

    @Transactional
    @Override
    public Brand updateBrand(Brand brand) {
        if (brandRepository.findById(brand.getBrandId()).isPresent()) {
           return brandRepository.save(brand);
        }else{
            throw new RequestException("E-006", HttpStatus.NOT_FOUND, "La marca a actualizar no existe.");
        }
    }

    @Transactional
    @Override
    public void deleteBrand(Long brandId){
        if (brandRepository.existsById(brandId)) {
            brandRepository.deleteById(brandId);
        }else{
            throw new RequestException("E-006",HttpStatus.NOT_FOUND,"La marca a eliminar no existe");
        }
    }

    @Override
    public Long countTotalsBrand(){
        return brandRepository.countTotalBrands();
    }
    @Override
    public Brand findBrandById(Long brandId) {
        return brandRepository.findByBrandId(brandId);
    }

    @Override
    public List<Brand> findByNameStartsWith(String name) {
        return brandRepository.findByNameStartsWith(name);
    }
}
