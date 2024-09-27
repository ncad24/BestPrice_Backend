package com.upc.trabajoarquitectura.controller;

import com.upc.trabajoarquitectura.dtos.ProductoxUsuarioDTO;
import com.upc.trabajoarquitectura.entities.ProductoxUsuario;
import com.upc.trabajoarquitectura.servicies.ProductoxUsuarioService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@CrossOrigin(origins = {"http://localhost:4200","http://18.223.169.236/"})
@RestController
@RequestMapping("/api")
public class ProductoxUsuarioController {
    @Autowired
    private ProductoxUsuarioService productoxUsuarioService;

    @GetMapping("/productos/usuarios")
    public List<ProductoxUsuarioDTO> obtenerProductoxUsuarios() {
        ModelMapper mapper = new ModelMapper();
        List<ProductoxUsuario> productoxUsuarios = productoxUsuarioService.listarProductoxUsuario();
        List<ProductoxUsuarioDTO> productoxUsuarioDTOS = Arrays.asList(mapper.map(productoxUsuarios, ProductoxUsuarioDTO[].class));
        return productoxUsuarioDTOS;
    }

    @PostMapping("/producto/usuario/{productoID}/{usuarioID}")
    public void registrarProudctoxUsuario(@PathVariable Long productoID, @PathVariable Long usuarioID){
        productoxUsuarioService.registrarProductoxUsuario(productoID, usuarioID);
    }
}
