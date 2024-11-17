package com.upc.trabajoarquitectura.controllers;

import com.upc.trabajoarquitectura.dtos.entities.SupermarketDTO;
import com.upc.trabajoarquitectura.entities.Brand;
import com.upc.trabajoarquitectura.entities.Supermarket;
import com.upc.trabajoarquitectura.interfaces.ISupermarketService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Arrays;
import java.util.List;

@CrossOrigin(origins = {"http://localhost:4200","http://18.223.169.236/"})
@RestController
@RequestMapping("/api")
public class SupermarketController {
    @Autowired
    private ISupermarketService supermarketService;

    @GetMapping("/supermarkets")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public List<SupermarketDTO> getSupermarkets(){
        ModelMapper mapper = new ModelMapper();
        List<Supermarket> supermarket = supermarketService.getSupermarkets();
        List<SupermarketDTO> supermarketDTO = Arrays.asList(mapper.map(supermarket, SupermarketDTO[].class));
        return supermarketDTO;
    }

    @PostMapping("/supermarket")
    @PreAuthorize("hasRole('ADMIN')")
    public SupermarketDTO registerSupermarket(@RequestBody SupermarketDTO supermarketDTO,
                                              @RequestParam(value = "image", required = false) MultipartFile image) throws Exception {
        ModelMapper mapper = new ModelMapper();
        Supermarket supermarket = mapper.map(supermarketDTO, Supermarket.class);
        supermarket = supermarketService.registerSupermarket(supermarket, image);
        supermarketDTO = mapper.map(supermarket, SupermarketDTO.class);
        return supermarketDTO;
    }

    @PutMapping("/supermarket/update")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<SupermarketDTO> updateSupermarket(@RequestBody SupermarketDTO supermarketDTO){
        ModelMapper mapper = new ModelMapper();
        Supermarket supermarket = mapper.map(supermarketDTO, Supermarket.class);
        supermarket = supermarketService.updateSupermarket(supermarket);
        supermarketDTO = mapper.map(supermarket, SupermarketDTO.class);
        return ResponseEntity.ok(supermarketDTO);
    }

    @DeleteMapping("/supermarket/delete/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public void deleteSupermarket(@PathVariable Long id){
        supermarketService.deleteSupermarket(id);
    }

    @GetMapping("/supermarket/counts")
    @PreAuthorize("hasRole('ADMIN')")
    public Long countTotalsSupermarket(){
        return supermarketService.countTotalsSupermarket();
    }

    @GetMapping("/supermarket/id/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public Supermarket findSupermarketById(@PathVariable Long id){
        return supermarketService.findSupermarketById(id);
    }

    @GetMapping("/supermarket/name/{supermarketName}")
    @PreAuthorize("hasRole('ADMIN')")
    public List<Supermarket> findByBrandName(@PathVariable String supermarketName) {
        return supermarketService.findByNameStartsWith(supermarketName);
    }
}
