package com.upc.trabajoarquitectura.controllers;

import com.upc.trabajoarquitectura.dtos.entities.UserAppDTO;
import com.upc.trabajoarquitectura.dtos.manageinfo.UserInfoDTO;
import com.upc.trabajoarquitectura.entities.UserApp;
import com.upc.trabajoarquitectura.interfaces.IUserAppService;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

@CrossOrigin(origins = {"http://localhost:4200","http://18.223.169.236/"})
@RestController
@RequestMapping("/api")
@Slf4j
public class UserAppController {
    @Autowired
    private IUserAppService userService;
    @Autowired
    private PasswordEncoder bcrypt;

    @GetMapping("/users")
    @PreAuthorize("hasRole('ADMIN')")
    public List<UserAppDTO> getUsers(){
        ModelMapper mapper = new ModelMapper();
        List<UserApp> userApps = userService.getUsers();
        List<UserAppDTO> userAppDTO = Arrays.asList(mapper.map(userApps, UserAppDTO[].class));
        return userAppDTO;
    }

    @PostMapping("/user")
    public ResponseEntity<UserAppDTO> registerUser(@ModelAttribute UserAppDTO userAppDTO,
                                   @RequestParam(value = "image", required = false) MultipartFile image) throws IOException {
        ModelMapper mapper = new ModelMapper();
        UserApp userApp = mapper.map(userAppDTO, UserApp.class);
        String bcryptPassword = bcrypt.encode(userApp.getPassword());
        userApp.setPassword(bcryptPassword);
        log.info("Registrando usuario: {}", userAppDTO);
        if (image != null) {
            log.info("Imagen recibida: {}", image.getOriginalFilename());
        } else {
            log.warn("No se recibió ninguna imagen");
        }
        userApp = userService.registerUser(userApp, image);
        userAppDTO = mapper.map(userApp, UserAppDTO.class);
        return ResponseEntity.ok(userAppDTO);
    }

    @PutMapping("/user/update")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public ResponseEntity<UserAppDTO> updateUser(@ModelAttribute UserAppDTO userAppDTO,
                                                 @RequestParam(value = "image", required = false) MultipartFile image) throws Exception{
        ModelMapper mapper = new ModelMapper();
        UserApp userApp = mapper.map(userAppDTO, UserApp.class);
        String bcryptPassword = bcrypt.encode(userApp.getPassword());
        userApp.setPassword(bcryptPassword);
        userApp = userService.updateUser(userApp, image);
        userAppDTO = mapper.map(userApp, UserAppDTO.class);
        return ResponseEntity.ok(userAppDTO);
    }

    @DeleteMapping("/user/delete/{id}")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public void deleteUser(@PathVariable Long id) throws Exception{
        userService.deleteUser(id);
    }

    @GetMapping("/user/username/{username}")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public UserInfoDTO findInfoUser(@PathVariable String username){
        return userService.findByUsername(username);
    }

    @GetMapping("/user/count")
    @PreAuthorize("hasRole('ADMIN')")
    public Long countTotalUser(){
        return userService.countTotalUsers();
    }
}
