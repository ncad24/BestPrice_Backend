package com.upc.trabajoarquitectura.dtos;

import com.upc.trabajoarquitectura.entities.Categoria;
import com.upc.trabajoarquitectura.entities.Marca;
import com.upc.trabajoarquitectura.entities.Supermercado;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashSet;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class ProductoDTO {
    private Long productoID;
    private String nombre;
    private String descripcion;
    private String rutaimagen;
    private Marca marca;
    private Categoria categoria;
}
