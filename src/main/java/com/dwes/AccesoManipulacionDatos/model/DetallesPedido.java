package com.dwes.AccesoManipulacionDatos.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Set;

@Data
@Entity
public class DetallesPedido {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Integer cantidad;

    // Muchos detalles pueden referirse a un mismo producto
    @ManyToOne
    @JoinColumn(name = "id_producto")
    private Producto producto;

    // Muchos detalles pertenecen a un mismo pedido
    @ManyToOne
    @JoinColumn(name = "id_pedido")
    private Pedido pedido;
}
