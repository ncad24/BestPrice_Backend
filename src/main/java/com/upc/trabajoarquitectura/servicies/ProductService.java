package com.upc.trabajoarquitectura.servicies;

import com.upc.trabajoarquitectura.entities.Brand;
import com.upc.trabajoarquitectura.entities.Category;
import com.upc.trabajoarquitectura.entities.Product;
import com.upc.trabajoarquitectura.exceptions.RequestException;
import com.upc.trabajoarquitectura.interfaces.IProductService;
import com.upc.trabajoarquitectura.respositories.BrandRepository;
import com.upc.trabajoarquitectura.respositories.CategoryRepository;
import com.upc.trabajoarquitectura.respositories.ProductRepository;
import com.upc.trabajoarquitectura.util.ValidationService;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
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
import java.util.UUID;

@Slf4j
@Service
public class ProductService implements IProductService {
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private BrandRepository brandRepository;
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private ValidationService validationService;

    @Transactional
    public List<Product> getProducts()
    {
        return productRepository.findAll();
    }

    public void verifyProduct(Product product){
        validationService.verifyNoNulls(product.getName(),product.getDescription(),product.getAdvertisement(),product.getBrand(),product.getCategory());
        validationService.verifyNoEmpty(product.getName(),product.getDescription(),product.getAdvertisement());
        //validationService.verifyOnlyLetters(product.getDescription(),product.getAdvertisement());
        validationService.verifyLength(product.getName(),2,50);
        validationService.verifyLength(product.getDescription(),10,75);
        validationService.verifyLength(product.getAdvertisement(),10,120);
        /*if (!product.getCategory().getName().equalsIgnoreCase("Pan")) {
            throw new RequestException("P-001", HttpStatus.BAD_REQUEST, "El producto debe tener una marca asignada, excepto si es pan.");
        }*/
    }

    @Transactional
    public Product registerProduct(Product product, Long brandId, Long categoryId, MultipartFile image) throws IOException {
        verifyProduct(product);
        //Buscar marca, categoria
        Brand brand = brandRepository.findById(brandId).orElseThrow(() -> new RequestException("E-006",HttpStatus.NOT_FOUND,"La marca no existe"));
        Category category = categoryRepository.findById(categoryId).orElseThrow(()-> new RequestException("E-006",HttpStatus.NOT_FOUND,"La categoria no existe"));
        //Asignar
        product.setBrand(brand);
        product.setCategory(category);

        // Obtener la ruta base del proyecto
        Path projectPath = Paths.get("").toAbsolutePath();

        String uploadDir = projectPath.toString() + File.separator + "images" + File.separator + "product";
        Path uploadPath = Paths.get(uploadDir);

        // Crear el directorio si no existe
        if (Files.notExists(uploadPath)) {
            Files.createDirectories(uploadPath);
        }

        String defaultImagePath = projectPath.toString() + File.separator + "images" + File.separator + "product" + File.separator + "default.png";

        if (image != null && !image.isEmpty()) {
            String originalFilename = image.getOriginalFilename();
            String extension = "";

            if (originalFilename != null && originalFilename.contains(".")) {
                extension = originalFilename.substring(originalFilename.lastIndexOf("."));
            }

            String nombreArchivo = image.getOriginalFilename() + "_" + UUID.randomUUID().toString() + extension;
            String rutaDirectorio = projectPath.toString() + File.separator + "images" + File.separator + "product";
            Path rutaDirectorioPath = Paths.get(rutaDirectorio);

            Path rutaCompleta = rutaDirectorioPath.resolve(nombreArchivo);
            Files.copy(image.getInputStream(), rutaCompleta, StandardCopyOption.REPLACE_EXISTING);

            product.setImagePath(nombreArchivo);
        }

        return productRepository.save(product);
    }
    @Transactional
    public Product updateProduct(Product product, MultipartFile image) throws IOException{
        Product existingProduct = productRepository.findByProductId(product.getProductId());
        if (productRepository.findById(product.getProductId()).isPresent()){
            verifyProduct(product);
            if (image != null && !image.isEmpty()) {
                // Delete the previous image if it exists
                String oldImagePath = existingProduct.getImagePath();
                if (oldImagePath != null && !oldImagePath.isEmpty()) {
                    File oldImageFile = new File(oldImagePath);
                    if (oldImageFile.exists()) {
                        try {
                            Files.delete(oldImageFile.toPath());
                        } catch (IOException e) {
                            System.err.println("Error deleting old image: " + e.getMessage());

                        }
                    }
                }

                // Save the new image
                String filename = image.getOriginalFilename() + "_" + UUID.randomUUID().toString();
                String uploadDir = ".../trabajoarquitectura/images/product/";
                Path uploadPath = Paths.get(uploadDir);

                // Create new path if it doesn't exist
                if (Files.notExists(uploadPath)) {
                    Files.createDirectories(uploadPath);
                }

                // Save the archive in the server
                Path filePath = uploadPath.resolve(filename);
                Files.copy(image.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);
                product.setImagePath(filePath.toString()); // Actualizar la ruta de la imagen en el producto
            }
            return productRepository.save(product);
        } else {
            throw new RequestException("E-006", HttpStatus.NOT_FOUND,"El producto a actualizar busca no existe");
        }
    }
    @Transactional
    public void deleteProduct(Long productId){
        if (productRepository.existsById(productId)){
            productRepository.deleteById(productId);
        }else {
            throw new RequestException("E-006", HttpStatus.NOT_FOUND, "El producto a eliminar no existe");
        }
    }
    @Override
    public Product findProductByID(Long productId) {
        if (productRepository.existsById(productId)){
            return productRepository.findByProductId(productId);
        } else {
            throw new RequestException("E-006", HttpStatus.NOT_FOUND, "No existe el ID");
        }
    }
    @Override
    public List<Product> findByProductName(String productName){
        return productRepository.findByNameStartingWithIgnoreCase(productName.toLowerCase());
    }
    @Override
    public List<Product> findByCategoryOfProduct(String categoryName){
        return productRepository.findByCategory_Name(categoryName);
    }
    @Override
    public List<Product> findByBrandOfProduct(String brandName){
        return productRepository.findByBrand_Name(brandName);
    }
    @Override
    public Long countTotalProducts(){
        return productRepository.countTotalProduct();
    }
}
