package com.upc.trabajoarquitectura.controller;

import com.upc.trabajoarquitectura.dtos.ProductoxUsuarioDTO;
import com.upc.trabajoarquitectura.entities.ProductoxUsuario;
import com.upc.trabajoarquitectura.servicies.ProductoxUsuarioService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/api")
public class ProductoxUsuarioController {
    @Autowired
    private ProductoxUsuarioService productoxUsuarioService;

    @GetMapping("/productos/usuarios")
    public ResponseEntity<List<ProductoxUsuarioDTO>> obtenerProductoxUsuarios() {
        try{
            ModelMapper mapper = new ModelMapper();
            List<ProductoxUsuario> productoxUsuarios = productoxUsuarioService.listarProductoxUsuario();
            List<ProductoxUsuarioDTO> productoxUsuarioDTOS = Arrays.asList(mapper.map(productoxUsuarios, ProductoxUsuarioDTO[].class));
            return new ResponseEntity<>(productoxUsuarioDTOS, HttpStatus.CREATED);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/producto/usuario/{productoID}/{usuarioID}")
    public void registrarProudctoxUsuario(@PathVariable Long productoID, @PathVariable Long usuarioID) throws Exception{
        try{
            productoxUsuarioService.registrarProductoxUsuario(productoID, usuarioID);
        }catch (Exception e){
            throw new Exception("No se pudo registrar producto con usuario");
        }
    }
}
