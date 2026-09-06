package com.dwes.AccesoManipulacionDatos.controller;

import com.dwes.AccesoManipulacionDatos.dto.ProductoRequestDTO;
import com.dwes.AccesoManipulacionDatos.model.Producto;
import com.dwes.AccesoManipulacionDatos.service.ProductoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ProductoController {
    private final ProductoService productoService;

    @Autowired
    public ProductoController(ProductoService productoService) {
        this.productoService = productoService;
    }

    // Crear producto
    @PostMapping("/crearProducto")
    public Producto crearProducto(@Valid @RequestBody Producto producto) {
        return productoService.saveProducto(producto);
    }

    // Actualizar datos de un producto
    @PutMapping("/actualizarProducto/{id}")
    public Producto actualizarProducto(@RequestBody Producto producto, @PathVariable("id") Long idProducto) {
        return productoService.updateProducto(producto, idProducto);
    }

    // Listar productos
    @GetMapping("/listarProductos")
    private List<ProductoRequestDTO> listarTodos() {
        return productoService.listarTodos();
    }

    // Eliminar producto por Id
    @DeleteMapping("/eliminarProducto/{id}")
    public String eliminarProducto (@PathVariable ("id") Long idProducto) {
        productoService.eliminarProducto(idProducto);
        return "Producto eliminado";
    }
}
