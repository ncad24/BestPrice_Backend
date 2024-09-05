package com.upc.trabajoarquitectura.dtos;

import com.upc.trabajoarquitectura.entities.Categoria;
import com.upc.trabajoarquitectura.entities.Distrito;
import com.upc.trabajoarquitectura.entities.Marca;
import com.upc.trabajoarquitectura.entities.Supermercado;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class ProductoDTO {
    private Long productoID;
    private String nombre;
    private String descripcion;
    private double precio;
    private List<Marca> marcas = new ArrayList<>();
    private List<Categoria> categorias = new ArrayList();
    private Set<Supermercado> supermercados = new HashSet<>();
    private Set<Distrito> distritos = new HashSet<>();
}
