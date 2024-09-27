package com.upc.trabajoarquitectura.controller;

import com.upc.trabajoarquitectura.dtos.RolDTO;
import com.upc.trabajoarquitectura.entities.Rol;
import com.upc.trabajoarquitectura.interfaces.IRolService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = {"http://localhost:4200","http://18.223.169.236/"})
@RestController
@RequestMapping("/api")
public class RolController {
    @Autowired
    private IRolService rolService;

    @GetMapping("/roles")
    @PreAuthorize("hasRole('ADMIN')")
    public List<RolDTO> listarRoles() {
        ModelMapper modelMapper = new ModelMapper();
        List<Rol> roles = rolService.listarRoles();
        List<RolDTO> rolDTOs = modelMapper.map(roles, List.class);
        return rolDTOs;
    }

    @PostMapping("/rol")
    @PreAuthorize("hasRole('ADMIN')")
    public RolDTO registrarRol(@RequestBody RolDTO rolDTO){
        ModelMapper mapper = new ModelMapper();
        Rol rol = mapper.map(rolDTO, Rol.class);
        rol = rolService.registrarRol(rol);
        rolDTO = mapper.map(rol, RolDTO.class);
        return rolDTO;
    }

    @PutMapping("/rol/actualizar")
    @PreAuthorize("hasRole('ADMIN')")
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

    @DeleteMapping("/rol/eliminar/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public void eliminarRol(@PathVariable Long id) throws Exception{
        try{
            rolService.eliminarRol(id);
        }catch (Exception e){
            throw new Exception("Disculpe la molestia");
        }
    }

    @PostMapping("/rol/asignar/{rolID}/{usuarioID}")
    @PreAuthorize("hasRole('ADMIN')")
    public void asignarRol(@PathVariable Long rolID, @PathVariable Long usuarioID) throws Exception{
        try {
            rolService.asignarRol(rolID, usuarioID);
        }catch (Exception e){
            throw new Exception("Disculpe la molestia");
        }
    }
}
