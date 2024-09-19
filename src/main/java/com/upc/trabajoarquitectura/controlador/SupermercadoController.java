package com.upc.trabajoarquitectura.controlador;

import com.upc.trabajoarquitectura.dtos.SupermercadoDTO;
import com.upc.trabajoarquitectura.entities.Supermercado;
import com.upc.trabajoarquitectura.interfaces.ISupermercadoService;
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
public class SupermercadoController {
    @Autowired
    private ISupermercadoService supermercadoService;

    @GetMapping("/supermercados")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public List<SupermercadoDTO> listarSupermercados(){
        ModelMapper mapper = new ModelMapper();
        List<Supermercado> supermercado = supermercadoService.listarSupermercados();
        List<SupermercadoDTO> supermercadoDTO = Arrays.asList(mapper.map(supermercado, SupermercadoDTO[].class));
        return supermercadoDTO;
    }

    @PostMapping("/supermercado")
    public SupermercadoDTO registrarSupermercado(@RequestBody SupermercadoDTO supermercadoDTO){
        ModelMapper mapper = new ModelMapper();
        Supermercado supermercado = mapper.map(supermercadoDTO, Supermercado.class);
        supermercado = supermercadoService.registrarSupermercado(supermercado);
        supermercadoDTO = mapper.map(supermercado, SupermercadoDTO.class);
        return supermercadoDTO;
    }

    @PutMapping("/supermercado/actualizar")
    public ResponseEntity<SupermercadoDTO> actualizarSupermercado(@RequestBody SupermercadoDTO supermercadoDTO){
        ModelMapper mapper = new ModelMapper();
        try {
            Supermercado supermercado = mapper.map(supermercadoDTO, Supermercado.class);
            supermercado = supermercadoService.actualizarSupermercado(supermercado);
            supermercadoDTO = mapper.map(supermercado, SupermercadoDTO.class);
        }
        catch (Exception e){
            return new ResponseEntity<>(supermercadoDTO, HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(supermercadoDTO);
    }

    @DeleteMapping("/supermercado/eliminar/{id}")
    public void eliminarSupermercado(@PathVariable Long id) throws Exception{
        try{
            supermercadoService.eliminarSupermercado(id);
        }catch (Exception e){
            throw new Exception("Disculpe la molestia");
        }
    }
}
