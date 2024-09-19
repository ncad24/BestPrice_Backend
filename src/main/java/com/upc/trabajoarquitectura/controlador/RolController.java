package com.upc.trabajoarquitectura.controlador;

import com.upc.trabajoarquitectura.dtos.RolDTO;
import com.upc.trabajoarquitectura.entities.Rol;
import com.upc.trabajoarquitectura.interfaces.IRolService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/api")
public class RolController {
    @Autowired
    private IRolService rolService;

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/roles")
    public List<RolDTO> listarRoles() {
        ModelMapper modelMapper = new ModelMapper();
        List<Rol> roles = rolService.listarRoles();
        List<RolDTO> rolDTOs = modelMapper.map(roles, List.class);
        return rolDTOs;
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/rol")
    public RolDTO registrarRol(@RequestBody RolDTO rolDTO){
        ModelMapper mapper = new ModelMapper();
        Rol rol = mapper.map(rolDTO, Rol.class);
        rol = rolService.registrarRol(rol);
        rolDTO = mapper.map(rol, RolDTO.class);
        return rolDTO;
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/rol/actualizar")
    public ResponseEntity<RolDTO> actualizarRol(@RequestBody RolDTO rolDTO){
        ModelMapper mapper = new ModelMapper();
        try {
            Rol rol = mapper.map(rolDTO, Rol.class);
            rol = rolService.actualizarRol(rol);
            rolDTO = mapper.map(rol, RolDTO.class);
        }
        catch (Exception e){
            return new ResponseEntity<>(rolDTO, HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(rolDTO);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/rol/eliminar/{id}")
    public void eliminarRol(@PathVariable Long id) throws Exception{
        try{
            rolService.eliminarRol(id);
        }catch (Exception e){
            throw new Exception("Disculpe la molestia");
        }
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/rol/asignar/{rolID}/{usuarioID}")
    public void asignarRol(@PathVariable Long rolID, @PathVariable Long usuarioID) throws Exception{
        try {
            rolService.asignarRol(rolID, usuarioID);
        }catch (Exception e){
            throw new Exception("Disculpe la molestia");
        }
    }
}
