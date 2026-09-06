package com.dwes.AccesoManipulacionDatos.controller;

import com.dwes.AccesoManipulacionDatos.dto.LibroRequestDTO;
import com.dwes.AccesoManipulacionDatos.dto.LibrosRequestDTO;
import com.dwes.AccesoManipulacionDatos.model.Libro;
import com.dwes.AccesoManipulacionDatos.service.LibroService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class LibroController {
    private final LibroService libroService;

    @Autowired
    public LibroController(LibroService libroService) {
        this.libroService = libroService;
    }

    @PostMapping("/crearLibro")
    public Libro saveDepartment(@Valid @RequestBody Libro libro) {
        return libroService.saveLibro(libro);
    }

    @GetMapping("/listarTodos")
    public LibrosRequestDTO listarTodos() {
        return libroService.listarTodos();
    }

    @GetMapping("/listarTodosSinStream")
    public LibrosRequestDTO listarTodosSinStream() {
        return libroService.listarTodosSinStream();
    }

    @GetMapping("/listarLibroPorId/{id}")
    public LibroRequestDTO buscarLibroPorId(@PathVariable Long id) {
        return libroService.buscarLibroPorId(id);
    }

    @PutMapping("/libros/{id}")
    public Libro updateLibro(@RequestBody Libro libro, @PathVariable("id") Long idlibro) {
        return libroService.updateLibro(libro, idlibro);
    }

    @DeleteMapping("/libros/{id}")
    public String deleteDepartmentById(@PathVariable("id") Long idlibro) {
        libroService.deleteLibroById(idlibro);
        return "Deleted Successfully";
    }

}
