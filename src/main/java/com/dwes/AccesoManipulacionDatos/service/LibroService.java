package com.dwes.AccesoManipulacionDatos.service;

import com.dwes.AccesoManipulacionDatos.dto.LibroRequestDTO;
import com.dwes.AccesoManipulacionDatos.dto.LibrosRequestDTO;
import com.dwes.AccesoManipulacionDatos.model.Libro;
import com.dwes.AccesoManipulacionDatos.model.Vuelo;
import com.dwes.AccesoManipulacionDatos.repository.LibroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class LibroService {
    private final LibroRepository libroRepository;

    @Autowired
    public LibroService(LibroRepository libroRepository) {
        this.libroRepository = libroRepository;
    }

    // Crear libro
    public Libro saveLibro(Libro libro) {
        return libroRepository.save(libro);
    }

    // Listar libros
    // Read operation
    public LibrosRequestDTO listarTodos() {
        List<Libro> listaLibros= ((List<Libro>) libroRepository.findAll());
        Integer total = listaLibros.stream()
                .mapToInt(Libro::getCantidadDisponible)
                .sum();
        /*  for (Libro l : listaLibros) {
            total += l.getCantidadDisponible();
        }*/
        return LibrosRequestDTO.builder()
                .cantidadTotal(total)
                .libros(listaLibros.stream().map(this::mapToRequestDTO).toList())
                .build();
    }

    // Listar libros sin utilizar stream ni mapToInt
    public LibrosRequestDTO listarTodosSinStream() {
        List<Libro> listaLibros = ((List<Libro>) libroRepository.findAll());
        int total = 0;

        for (Libro l: listaLibros) {
            if (l.getCantidadDisponible() != null) {
                total += l.getCantidadDisponible();
            }
        }
        List<LibroRequestDTO> listaDTO = new ArrayList<>();

        for (Libro l: listaLibros) {
            LibroRequestDTO dto = LibroRequestDTO.builder()
                    .titulo(l.getTitulo())
                    .autor(l.getAutor())
                    .build();
            listaDTO.add(dto);
        }
        return LibrosRequestDTO.builder()
                .cantidadTotal(total)
                .libros(listaDTO)
                .build();
    }


    // Operación UPDATE
    public Libro updateLibro(Libro libro, Long idLibro) {
        Libro libroDB = libroRepository.findById(idLibro).get();

        if (Objects.nonNull(libro.getTitulo()) && !"".equalsIgnoreCase(libro.getTitulo())) {
            libroDB.setTitulo(libro.getTitulo());
        }
        if (Objects.nonNull(libro.getNumPagina()) && !"".equalsIgnoreCase(String.valueOf(libro.getNumPagina()))) {
            libroDB.setNumPagina(libro.getNumPagina());
        }
        if (Objects.nonNull(libro.getAutor()) && !"".equalsIgnoreCase(libro.getAutor())) {
            libroDB.setAutor(libro.getAutor());
        }
        if (Objects.nonNull(libro.getNumPagina()) && !"".equalsIgnoreCase(String.valueOf(libro.getCantidadDisponible()))) {
            libroDB.setCantidadDisponible(libro.getCantidadDisponible());
        }
        return libroRepository.save(libroDB);
    }

    // Operación DELETE
    public void deleteLibroById(Long idlibro) {
        libroRepository.deleteById(idlibro);
    }

    // Listar libros por su ID
    public LibroRequestDTO buscarLibroPorId(Long id) {
        Libro libro=libroRepository.findById(id).get();
        return mapToRequestDTO(libro);
    }

    // Mapeo DTO
    private LibroRequestDTO mapToRequestDTO(Libro libro) {
        return LibroRequestDTO.builder()
                .titulo(libro.getTitulo())
                .autor(libro.getAutor())
                .build();
    }
}
