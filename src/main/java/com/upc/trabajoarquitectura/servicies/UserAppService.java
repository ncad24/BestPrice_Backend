package com.upc.trabajoarquitectura.servicies;

import com.upc.trabajoarquitectura.dtos.manageinfo.UserInfoDTO;
import com.upc.trabajoarquitectura.entities.Role;
import com.upc.trabajoarquitectura.entities.UserApp;
import com.upc.trabajoarquitectura.exceptions.RequestException;
import com.upc.trabajoarquitectura.interfaces.IUserAppService;
import com.upc.trabajoarquitectura.respositories.RoleRepository;
import com.upc.trabajoarquitectura.respositories.UserAppRepository;
import com.upc.trabajoarquitectura.util.ValidationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
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
public class UserAppService implements IUserAppService {
    @Autowired
    private UserAppRepository userAppRepository;

    //

    @Autowired
    private ValidationService validationService;
    @Autowired
    private RoleRepository roleRepository;

    @Transactional
    public List<UserApp> getUsers() {
        return userAppRepository.findAll();
    }

    public void verifyUser(UserApp userApp){
        validationService.verifyNoNulls(userApp.getNames(),userApp.getSurnames(),userApp.getPhoneNumber(),userApp.getEmail(),userApp.getGender(),userApp.getPassword());
        validationService.verifyNoEmptyWithInvisibleSpaces(userApp.getNames(),userApp.getSurnames());
        validationService.verifyNoEmpty(userApp.getPhoneNumber(),userApp.getEmail(),userApp.getGender(),userApp.getUsername(),userApp.getPassword());
        validationService.verifyOnlyLetters(userApp.getNames(),userApp.getSurnames(),userApp.getUsername());
        validationService.verifyLength(userApp.getNames(),3,80);
        validationService.verifyLength(userApp.getSurnames(),3,80);
        validationService.verifyLength(userApp.getPhoneNumber(),1,9);
        validationService.checkForDuplicate(userAppRepository.existsByEmail(userApp.getEmail()),"UserApp");
        validationService.checkForDuplicate(userAppRepository.existsByUsername(userApp.getUsername()),"UserApp");
        //phoneNumber
        if (!userApp.getPhoneNumber().matches("\\d+")) {
            throw new RequestException("U-001", HttpStatus.BAD_REQUEST,
                    "Solo se permite digitos");
        }
        //email
        if (!userApp.getEmail().matches("^[\\w-\\.]+@(gmail\\.com|yahoo\\.es|hotmail\\.com)$")) {
            throw new RequestException("U-002", HttpStatus.BAD_REQUEST,
                    "El correo electrónico debe ser un @gmail.com, @yahoo.es o @hotmail.com válido");
        }
        //gender
        if (!userApp.getGender().matches("[MF]")) {
            throw new RequestException("U-003", HttpStatus.BAD_REQUEST,
                    "El género solo puede ser 'M' para masculino o 'F' para femenino");
        }
        //password
        if (!userApp.getPassword().matches("^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)(?=.*[\\W_]).{8,}$")) {
            throw new RequestException("U-004", HttpStatus.BAD_REQUEST,
                    "La contraseña debe tener al menos 8 caracteres, incluyendo mayúsculas, minúsculas, números y al menos un símbolo.");
        }

        if (userAppRepository.existsByEmail(userApp.getEmail())) {
            throw new RequestException("U-005", HttpStatus.BAD_REQUEST,
                    "El Email ya fue registrado anteriormente");
        }
        if (userAppRepository.existsByUsername(userApp.getUsername())) {
            throw new RequestException("U-006", HttpStatus.BAD_REQUEST,
                    "El Username ya fue registrado anteriormente");
        }
        if (userAppRepository.existsByPhoneNumber(userApp.getPhoneNumber())) {
            throw new RequestException("U-007", HttpStatus.BAD_REQUEST,
                    "El número de teléfono ya fue registrado anteriormente");
        }
    }

    @Transactional
    public UserApp registerUser(UserApp userApp, MultipartFile image) throws IOException {
        verifyUser(userApp);
        // Obtener la ruta base del proyecto
        Path projectPath = Paths.get("").toAbsolutePath();

        String uploadDir = projectPath.toString() + File.separator + "images" + File.separator + "user";
        Path uploadPath = Paths.get(uploadDir);

        // Crear el directorio si no existe
        if (Files.notExists(uploadPath)) {
            Files.createDirectories(uploadPath);
        }

        String defaultImagePath = "default.png";

        if (image != null && !image.isEmpty()) {
            String originalFilename = image.getOriginalFilename();
            String extension = "";

            if (originalFilename != null && originalFilename.contains(".")) {
                extension = originalFilename.substring(originalFilename.lastIndexOf("."));
            }

            String nombreArchivo = userApp.getNames() + "_" + UUID.randomUUID().toString() + extension;
            String rutaDirectorio = projectPath.toString() + File.separator + "images" + File.separator + "user";
            Path rutaDirectorioPath = Paths.get(rutaDirectorio);

            Path rutaCompleta = rutaDirectorioPath.resolve(nombreArchivo);
            Files.copy(image.getInputStream(), rutaCompleta, StandardCopyOption.REPLACE_EXISTING);

            userApp.setImagePath(nombreArchivo);
        } else {
            userApp.setImagePath(defaultImagePath);
        }
        Role roleUser = roleRepository.findByRoleId(1);
        userApp.setRole(roleUser);
        return userAppRepository.save(userApp);
    }

    @Transactional
    public UserApp updateUser(UserApp userApp, MultipartFile image) throws Exception {
        UserApp existingUserApp = userAppRepository.findByUserId(userApp.getUserId());
        log.info(userApp.getUserId().toString());

        Path projectPath = Paths.get("").toAbsolutePath();
        String defaultImagePath = projectPath.toString() + File.separator + "images" + File.separator + "user" + File.separator + "default.png";
        if (userAppRepository.findById(userApp.getUserId()).isPresent()) {
            //verifyUser(userApp);
            log.info("1");
            if (image != null && !image.isEmpty()) {
                String oldImagePath = existingUserApp.getImagePath();
                log.info("2");
                log.info(oldImagePath);
                if (oldImagePath != null && !oldImagePath.isEmpty() && !oldImagePath.equals(defaultImagePath)) {
                    File oldImageFile = new File(oldImagePath);
                    if (oldImageFile.exists()) {
                        try{
                            Files.delete(oldImageFile.toPath());
                        }catch (IOException e){
                            System.err.println("Error deleting old image: " + e.getMessage());
                        }
                    }
                }

                String nombreArchivo = userApp.getUserId().toString() + "_" + image.getOriginalFilename();
                String rutaDirectorio = projectPath.toString() + File.separator + "images" + File.separator + "user";
                Path rutaDirectorioPath = Paths.get(rutaDirectorio);

                if (Files.notExists(rutaDirectorioPath)) {
                    Files.createDirectories(rutaDirectorioPath);
                }

                Path rutaCompleta = rutaDirectorioPath.resolve(nombreArchivo);
                Files.copy(image.getInputStream(), rutaCompleta, StandardCopyOption.REPLACE_EXISTING);

                userApp.setImagePath(rutaCompleta.toString());
            }else {
                userApp.setImagePath(defaultImagePath);
            }

            return userAppRepository.save(userApp);
        }else {
            throw new RequestException("E-006", HttpStatus.NOT_FOUND,"El usuario a actualizar no existe");
        }

    }

    @Transactional
    public void deleteUser(Long userId) throws Exception {
        if (userAppRepository.existsById(userId)) {
            userAppRepository.deleteById(userId);
        }else{
            throw new RequestException("E-006", HttpStatus.NOT_FOUND,"El usuario a eliminar no existe");
        }
    }
    @Override
    public Long countTotalUsers(){
        return userAppRepository.countTotalUsers();
    }
    @Override
    public UserApp findByUserId(Long userId){
        if (!userAppRepository.existsByUserId(userId)){
            throw new RequestException("E00", HttpStatus.NOT_FOUND, "El usuario a buscar no existe");
        }
        return userAppRepository.findByUserId(userId);
    }

    @Override
    public UserInfoDTO findByUsername(String username) {
        return userAppRepository.findByUsernameDTO(username);
    }
}
