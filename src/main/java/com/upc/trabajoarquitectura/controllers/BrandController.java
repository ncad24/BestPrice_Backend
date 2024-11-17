package com.upc.trabajoarquitectura.controllers;

import com.upc.trabajoarquitectura.dtos.entities.BrandDTO;
import com.upc.trabajoarquitectura.entities.Brand;
import com.upc.trabajoarquitectura.interfaces.IBrandService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@CrossOrigin(origins = {"http://localhost:4200","http://18.223.169.236/"})
@RestController
@RequestMapping("/api")
public class BrandController {
    @Autowired
    private IBrandService brandService;

    @GetMapping("/brands")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public List<BrandDTO> getBrands(){
        ModelMapper mapper = new ModelMapper();
        List<Brand> marcas = brandService.getBrands();
        List<BrandDTO> brandDTO = Arrays.asList(mapper.map(marcas, BrandDTO[].class));
        return brandDTO;
    }

    @PostMapping("/brand")
    @PreAuthorize("hasRole('ADMIN')")
    public BrandDTO registerBrand(@RequestBody BrandDTO brandDTO){
        ModelMapper mapper = new ModelMapper();
        Brand marca = mapper.map(brandDTO, Brand.class);
        marca = brandService.registerBrand(marca);
        brandDTO = mapper.map(marca, BrandDTO.class);
        return brandDTO;
    }

    @PutMapping("/brand/update")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<BrandDTO> updateBrand(@RequestBody BrandDTO brandDTO){
        ModelMapper mapper = new ModelMapper();
        Brand marca = mapper.map(brandDTO, Brand.class);
        marca = brandService.updateBrand(marca);
        brandDTO = mapper.map(marca, BrandDTO.class);
        return ResponseEntity.ok(brandDTO);
    }

    @DeleteMapping("/brand/delete/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public void deleteBrand(@PathVariable Long id) throws Exception{
        brandService.deleteBrand(id);
    }

    @GetMapping("/brand/counts")
    @PreAuthorize("hasRole('ADMIN')")
    public Long countTotalsBrand() {
        return brandService.countTotalsBrand();
    }

    @GetMapping("/brand/id/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public Brand findCategoryById(@PathVariable Long id) {
        return brandService.findBrandById(id);
    }

    @GetMapping("/brand/name/{brandName}")
    @PreAuthorize("hasRole('ADMIN')")
    public List<Brand> findByBrandName(@PathVariable String brandName) {
        return brandService.findByNameStartsWith(brandName);
    }
}
