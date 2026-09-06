package com.dwes.AccesoManipulacionDatos.service;

import com.dwes.AccesoManipulacionDatos.dto.ProductoRequestDTO;
import com.dwes.AccesoManipulacionDatos.model.Producto;
import com.dwes.AccesoManipulacionDatos.repository.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class ProductoService {
    private final ProductoRepository productoRepository;

    @Autowired
    public ProductoService(ProductoRepository productoRepository) {
        this.productoRepository = productoRepository;
    }

    // Crear producto
    public Producto saveProducto(Producto producto) {
        return productoRepository.save(producto);
    }

    // Actualizar información producto
    public Producto updateProducto(Producto producto, Long idProducto) {
        Producto productoDB = productoRepository.findById(idProducto).get();

        /*if (Objects.nonNull(producto.getId()) && !"".equalsIgnoreCase(String.valueOf(producto.getId()))) {
            productoDB.setId(producto.getId());
        }*/
        if (Objects.nonNull(producto.getNombre()) && !"".equalsIgnoreCase(producto.getNombre())) {
            productoDB.setNombre(producto.getNombre());
        }
        if (Objects.nonNull(producto.getPrecio()) && !"".equalsIgnoreCase(String.valueOf(producto.getPrecio()))) {
            productoDB.setPrecio(producto.getPrecio());
        }
        if (Objects.nonNull(producto.getDescipcion()) && !"".equalsIgnoreCase(producto.getDescipcion())) {
            productoDB.setDescipcion(producto.getDescipcion());
        }
        if (Objects.nonNull(producto.getCantidad_disponible()) && !"".equalsIgnoreCase(String.valueOf(producto.getCantidad_disponible()))) {
            productoDB.setCantidad_disponible(producto.getCantidad_disponible());
        }
        return productoRepository.save(productoDB);
    }

    // Listar todos los productos
    public List<ProductoRequestDTO> listarTodos() {
        List<Producto> productos = (List<Producto>) productoRepository.findAll();

        return productos.stream()
                .map(this::mapToRequestDTO)
                .collect(Collectors.toList());
    }

    // Eliminar producto por ID
    public void eliminarProducto(Long idProducto) {
        productoRepository.deleteById(idProducto);
    }

    // Mapeo DTO
    private ProductoRequestDTO mapToRequestDTO(Producto producto) {
        return ProductoRequestDTO.builder()
                .nombre(producto.getNombre())
                .precio(producto.getPrecio())
                .cantidad_disponible(producto.getCantidad_disponible())
                .build();
    }
}
