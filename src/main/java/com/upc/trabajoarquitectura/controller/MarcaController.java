package com.upc.trabajoarquitectura.controller;

import com.upc.trabajoarquitectura.dtos.MarcaDTO;
import com.upc.trabajoarquitectura.entities.Marca;
import com.upc.trabajoarquitectura.interfaces.IMarcaService;
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
public class MarcaController {
    @Autowired
    private IMarcaService marcaService;

    @GetMapping("/marcas")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public ResponseEntity<List<MarcaDTO>> listarMarcas(){
        ModelMapper mapper = new ModelMapper();
        try{
            List<Marca> marcas = marcaService.listarMarca();
            List<MarcaDTO> marcaDTO = Arrays.asList(mapper.map(marcas, MarcaDTO[].class));
            return new ResponseEntity<>(marcaDTO, HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/marca")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<MarcaDTO> registrarMarca(@RequestBody MarcaDTO marcaDTO){
        ModelMapper mapper = new ModelMapper();
        try {
            Marca marca = mapper.map(marcaDTO, Marca.class);
            marca = marcaService.registrarMarca(marca);
            marcaDTO = mapper.map(marca, MarcaDTO.class);
            return new ResponseEntity<>(marcaDTO, HttpStatus.CREATED);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/marca/actualizar")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<MarcaDTO> actualizarMarca(@RequestBody MarcaDTO marcaDTO){
        ModelMapper mapper = new ModelMapper();
        try {
            Marca marca = mapper.map(marcaDTO, Marca.class);
            marca = marcaService.actualizarMarca(marca);
            marcaDTO = mapper.map(marca, MarcaDTO.class);
        }
        catch (Exception e){
            return new ResponseEntity<>(marcaDTO, HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(marcaDTO);
    }

    @DeleteMapping("/marca/eliminar/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public void eliminarMarca(@PathVariable Long id) throws Exception{
        try{
            marcaService.eliminarMarca(id);
        }catch (Exception e){
            throw new Exception("Disculpe la molestia");
        }
    }
}
