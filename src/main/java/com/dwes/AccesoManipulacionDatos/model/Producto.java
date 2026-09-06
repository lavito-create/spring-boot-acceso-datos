package com.dwes.AccesoManipulacionDatos.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
public class Producto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_producto;
    private String nombre;
    private String descipcion;
    private Double precio;
    private Integer cantidad_disponible;

    // Un producto puede aparecer en varios detalles de pedido
    @OneToMany(mappedBy = "producto", cascade = CascadeType.ALL)
    private List<DetallesPedido> detallesPedidos;
}
