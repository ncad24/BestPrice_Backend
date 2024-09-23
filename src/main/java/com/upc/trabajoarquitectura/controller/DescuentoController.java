package com.upc.trabajoarquitectura.controller;

import com.upc.trabajoarquitectura.dtos.DescuentoDTO;
import com.upc.trabajoarquitectura.dtos.MarcaDTO;
import com.upc.trabajoarquitectura.entities.Descuento;
import com.upc.trabajoarquitectura.entities.Marca;
import com.upc.trabajoarquitectura.servicies.DescuentoService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class DescuentoController {
    @Autowired
    private DescuentoService descuentoService;

    @GetMapping("/descuentos")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public ResponseEntity<List<DescuentoDTO> >listarDescuentos(){
        ModelMapper modelMapper = new ModelMapper();
        try{
            List<Descuento> descuentos = descuentoService.listarDescuentos();
            List<DescuentoDTO> descuentoDTOS = modelMapper.map(descuentos, List.class);
            return new ResponseEntity<>(descuentoDTOS, HttpStatus.CREATED);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/descuento")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<DescuentoDTO> registrarDescuento(@RequestBody DescuentoDTO descuentoDTO){
        ModelMapper mapper = new ModelMapper();
        try{
            Descuento descuento = mapper.map(descuentoDTO, Descuento.class);
            descuento = descuentoService.registrarDescuento(descuento);
            descuentoDTO = mapper.map(descuento, DescuentoDTO.class);
            return new ResponseEntity<>(descuentoDTO, HttpStatus.CREATED);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/descuento/actualizar")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<DescuentoDTO> actualizarDescuento(@RequestBody DescuentoDTO descuentoDTO){
        ModelMapper mapper = new ModelMapper();
        try {
            Descuento descuento = mapper.map(descuentoDTO, Descuento.class);
            descuento = descuentoService.actualizarDescuento(descuento);
            descuentoDTO = mapper.map(descuento, DescuentoDTO.class);
        }
        catch (Exception e){
            return new ResponseEntity<>(descuentoDTO, HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(descuentoDTO);
    }

    @DeleteMapping("/descuento/eliminar/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public void eliminarDescuento(@PathVariable Long id) throws Exception{
        try{
            descuentoService.eliminarDescuento(id);
        }catch (Exception e){
            throw new Exception("Disculpe la molestia");
        }
    }
}
