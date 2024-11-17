package com.upc.trabajoarquitectura.servicies;

import com.upc.trabajoarquitectura.entities.Supermarket;
import com.upc.trabajoarquitectura.exceptions.RequestException;
import com.upc.trabajoarquitectura.interfaces.ISupermarketService;
import com.upc.trabajoarquitectura.respositories.SupermarketRepository;
import com.upc.trabajoarquitectura.util.ValidationService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class SupermarketService implements ISupermarketService {
    @Autowired
    private SupermarketRepository supermarketRepository;
    @Autowired
    private ValidationService validationService;
    @Transactional
    public List<Supermarket> getSupermarkets() {
        return supermarketRepository.findAll();
    }

    public void verifySupermarket(Supermarket supermarket){

        validationService.checkForDuplicate(supermarketRepository.existsByName(supermarket.getName()),"Supermercado");
        //throw new RequestException("E00", HttpStatus.CONFLICT, "El nombre del supermercado ya existe.");
        validationService.verifyNoEmpty(supermarket.getName(), supermarket.getDescription());
        //throw new RequestException("E00", HttpStatus.BAD_REQUEST,"No se permiten espacios en blanco o caracteres invisibles");
        validationService.verifyOnlyLetters(supermarket.getName(), supermarket.getDescription());
        //throw new RequestException("E00", HttpStatus.BAD_REQUEST,"Solo se permite caracteres");
        validationService.verifyLength(supermarket.getName(), 3, 50);
        //throw new RequestException("E00", HttpStatus.BAD_REQUEST, "El nombre no debe de ser menor a tres caracteres o mayores a cincuenta");
        validationService.verifyLength(supermarket.getDescription(), 3, 255);
        //throw new RequestException("C004", HttpStatus.BAD_REQUEST,"La descripción no debe de ser menor a tres caracteres o mayores a doscientos cincuenta y cinco ");

    }

    @Transactional
    public Supermarket registerSupermarket(Supermarket supermarket, MultipartFile image) throws IOException {
        verifySupermarket(supermarket);
        Path projectPath = Paths.get("").toAbsolutePath();

        String uploadDir = projectPath.toString() + File.separator + "images" + File.separator + "supermarket";
        Path uploadPath = Paths.get(uploadDir);

        // Crear el directorio si no existe
        if (Files.notExists(uploadPath)) {
            Files.createDirectories(uploadPath);
        }

        if (image != null && !image.isEmpty()) {
            String originalFilename = image.getOriginalFilename();
            String extension = "";

            if (originalFilename != null && originalFilename.contains(".")) {
                extension = originalFilename.substring(originalFilename.lastIndexOf("."));
            }

            String nombreArchivo = image.getOriginalFilename() + "_" + UUID.randomUUID().toString() + extension;
            String rutaDirectorio = projectPath.toString() + File.separator + "images" + File.separator + "supermarket";
            Path rutaDirectorioPath = Paths.get(rutaDirectorio);

            Path rutaCompleta = rutaDirectorioPath.resolve(nombreArchivo);
            Files.copy(image.getInputStream(), rutaCompleta, StandardCopyOption.REPLACE_EXISTING);

            supermarket.setImagePath(nombreArchivo);
        }

        return supermarketRepository.save(supermarket);
    }

    @Transactional
    public Supermarket updateSupermarket(Supermarket supermarket) {
        if (supermarketRepository.findById(supermarket.getSupermarketId()).isPresent()) {
            verifySupermarket(supermarket);
            return supermarketRepository.save(supermarket);
        }else {
            throw new RequestException("E-006", HttpStatus.NOT_FOUND,"El supermercado a actualizar no existe");
        }
    }

    @Transactional
    public void deleteSupermarket(Long supermarketId) {
        if (supermarketRepository.existsById(supermarketId)) {
            supermarketRepository.deleteById(supermarketId);
        }else{
            throw new RequestException("E-006", HttpStatus.NOT_FOUND,"El supermercado a eliminar no existe");
        }
    }

    @Override
    public Long countTotalsSupermarket(){
        return supermarketRepository.countTotalSupermarkets();
    }
    @Override
    public Supermarket findSupermarketById(Long supermarketId) {
        return supermarketRepository. findSupermarketBySupermarketId(supermarketId);
    }

    @Override
    public List<Supermarket> findByNameStartsWith(String supermarketName) {
        return supermarketRepository.findByNameStartingWithIgnoreCase(supermarketName);
    }
}
