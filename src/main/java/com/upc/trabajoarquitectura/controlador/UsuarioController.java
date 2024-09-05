package com.upc.trabajoarquitectura.controlador;

import com.upc.trabajoarquitectura.dtos.UsuarioDTO;
import com.upc.trabajoarquitectura.entities.Usuario;
import com.upc.trabajoarquitectura.interfaces.IUsuarioService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/api")
public class UsuarioController {
    @Autowired
    private IUsuarioService usuarioService;
    @GetMapping("/usuarios")
    public List<UsuarioDTO> listarUsuarios(){
        ModelMapper mapper = new ModelMapper();
        List<Usuario> usuarios = usuarioService.listarUsuarios();
        List<UsuarioDTO> usuarioDTO = Arrays.asList(mapper.map(usuarios, UsuarioDTO[].class));
        return usuarioDTO;
    }

    @PostMapping("/usuario")
    public UsuarioDTO registrarUsuario(@RequestBody UsuarioDTO usuarioDTO){
        ModelMapper mapper = new ModelMapper();
        Usuario usuario = mapper.map(usuarioDTO, Usuario.class);
        usuario = usuarioService.registrarUsuario(usuario);
        usuarioDTO = mapper.map(usuario, UsuarioDTO.class);
        return usuarioDTO;
    }

    @PutMapping("/usuario/actualizar")
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
    public void eliminarUsuario(@PathVariable Long id) throws Exception{
        try{
            usuarioService.eliminarUsuario(id);
        }catch (Exception e){
            throw new Exception("Disculpe la molestia");
        }
    }
}
