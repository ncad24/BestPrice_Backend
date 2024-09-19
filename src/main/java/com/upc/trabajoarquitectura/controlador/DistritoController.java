package com.upc.trabajoarquitectura.controlador;

import com.upc.trabajoarquitectura.dtos.DistritoDTO;
import com.upc.trabajoarquitectura.entities.Distrito;
import com.upc.trabajoarquitectura.interfaces.IDistritoService;
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
public class DistritoController {
    @Autowired
    private IDistritoService distritoService;

    @GetMapping("/distritos")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public List<DistritoDTO> listarDistritos(){
        ModelMapper mapper = new ModelMapper();
        List<Distrito> distritos = distritoService.listarDistritos();
        List<DistritoDTO> distritoDTO = Arrays.asList(mapper.map(distritos, DistritoDTO[].class));
        return distritoDTO;
    }

    @PostMapping("/distrito")
    @PreAuthorize("hasRole('ADMIN')")
    public DistritoDTO registrarDistrito(@RequestBody DistritoDTO distritoDTO){
        ModelMapper mapper = new ModelMapper();
        Distrito distrito = mapper.map(distritoDTO, Distrito.class);
        distrito = distritoService.registrarDistrito(distrito);
        distritoDTO = mapper.map(distrito, DistritoDTO.class);
        return distritoDTO;
    }

    @PutMapping("/distrito/actualizar")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<DistritoDTO> actualizarDistrito(@RequestBody DistritoDTO distritoDTO){
        ModelMapper mapper = new ModelMapper();
        try {
            Distrito distrito = mapper.map(distritoDTO, Distrito.class);
            distrito = distritoService.actualizarDistrito(distrito);
            distritoDTO = mapper.map(distrito, DistritoDTO.class);
        }
        catch (Exception e){
            return new ResponseEntity<>(distritoDTO, HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(distritoDTO);
    }

    @DeleteMapping("/distrito/eliminar/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public void eliminarDistrito(@PathVariable Long id) throws Exception{
        try{
            distritoService.eliminarDistrito(id);
        }catch (Exception e){
            throw new Exception("Disculpe la molestia");
        }
    }
}
