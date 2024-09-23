package com.upc.trabajoarquitectura.controller;

import com.upc.trabajoarquitectura.dtos.UsuarioDTO;
import com.upc.trabajoarquitectura.entities.Usuario;
import com.upc.trabajoarquitectura.interfaces.IUsuarioService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/api")
public class UsuarioController {
    @Autowired
    private IUsuarioService usuarioService;
    @Autowired
    private PasswordEncoder bcrypt;

    public static String UPLOAD_DIRECTORY = System.getProperty("user.dir") + "/src/main/java/com/upc/trabajoarquitectura/imagenes/usuario";

    @GetMapping("/usuarios")
    @PreAuthorize("hasRole('ADMIN')")
    public List<UsuarioDTO> listarUsuarios(){
        ModelMapper mapper = new ModelMapper();
        List<Usuario> usuarios = usuarioService.listarUsuarios();
        List<UsuarioDTO> usuarioDTO = Arrays.asList(mapper.map(usuarios, UsuarioDTO[].class));
        return usuarioDTO;
    }

    @PostMapping("/usuario")
    public UsuarioDTO registrarUsuario(@ModelAttribute UsuarioDTO usuarioDTO,
                                       @RequestParam("imagen") MultipartFile imagen) throws IOException {
        ModelMapper mapper = new ModelMapper();
        Usuario usuario = mapper.map(usuarioDTO, Usuario.class);
        String bcryptPassword = bcrypt.encode(usuario.getContrasenia());
        usuario.setContrasenia(bcryptPassword);
        // Verificar si el archivo de imagen no está vacío
        if (!imagen.isEmpty()) {
            // Definir la ruta donde se va a guardar la imagen
            String nombreArchivo = System.currentTimeMillis() + "_" + imagen.getOriginalFilename();
            String rutaDirectorio = "C:/Arquitectura de Aplicaciones/trabajoarquitectura/src/main/java/com/upc/trabajoarquitectura/imagenes/usuario/";
            String rutaCompleta = rutaDirectorio + nombreArchivo;

            // Guardar el archivo en el sistema de archivos
            File archivo = new File(rutaCompleta);
            imagen.transferTo(archivo);

            // Asignar la ruta de la imagen al producto
            usuario.setRutaImagen(rutaCompleta);
        }

        usuario = usuarioService.registrarUsuario(usuario);
        usuarioDTO= mapper.map(usuario, UsuarioDTO.class);
        return usuarioDTO;
    }

    @PutMapping("/usuario/actualizar")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public ResponseEntity<UsuarioDTO> actualizarUsuario(@RequestBody UsuarioDTO usuarioDTO){
        ModelMapper mapper = new ModelMapper();
        try {
            Usuario usuario = mapper.map(usuarioDTO, Usuario.class);
            usuario = usuarioService.actualizarUsuario(usuario);
            usuarioDTO = mapper.map(usuario, UsuarioDTO.class);
        }
        catch (Exception e){
            return new ResponseEntity<>(usuarioDTO, HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(usuarioDTO);
    }

    @DeleteMapping("/usuario/eliminar/{id}")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public void eliminarUsuario(@PathVariable Long id) throws Exception{
        try{
            usuarioService.eliminarUsuario(id);
        }catch (Exception e){
            throw new Exception("Disculpe la molestia");
        }
    }
}
