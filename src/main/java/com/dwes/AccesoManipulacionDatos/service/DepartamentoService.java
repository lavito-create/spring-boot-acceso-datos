package com.dwes.AccesoManipulacionDatos.service;

import com.dwes.AccesoManipulacionDatos.model.Departamento;
import com.dwes.AccesoManipulacionDatos.repository.DepartamentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class DepartamentoService {

    private final DepartamentoRepository departamentoRepository;

    @Autowired
    public DepartamentoService(DepartamentoRepository departamentoRepository) {
        this.departamentoRepository=departamentoRepository;
    }

    // operation CREATE
    public Departamento saveDepartment(Departamento departamento) {
        return departamentoRepository.save(departamento);
    }

    // Read operation
    public List<Departamento> fetchDepartamentoList() {
        return (List<Departamento>) departamentoRepository.findAll();
    }

    // Update operation
    //este método actualiza un objeto Departamento
    // en la base de datos con los nuevos valores proporcionados,
    // pero solo actualiza los campos que no son nulos
    // ni cadenas vacías en el objeto pasado como parámetro.
    //Por lo tanto, el método toma dos argumentos: el objeto Departamento
    // con los nuevos valores y el ID del departamento que se va a actualizar.
    public Departamento updateDepartamento(Departamento departamento, Long iddepartamento) {
        Departamento depDB = departamentoRepository.findById(iddepartamento).get();

        if (Objects.nonNull(departamento.getNombre()) && !"".equalsIgnoreCase(departamento.getNombre())) {
            depDB.setNombre(departamento.getNombre());
        }

        if (Objects.nonNull(departamento.getDireccion()) && !"".equalsIgnoreCase(departamento.getDireccion())) {
            depDB.setDireccion(departamento.getDireccion());
        }

        if (Objects.nonNull(departamento.getCodigo()) && !"".equalsIgnoreCase(departamento.getCodigo())) {
            depDB.setCodigo(departamento.getCodigo());
        }

        return departamentoRepository.save(depDB);
    }

    // Delete operation
    public void deleteDepartmentById(Long iddepartamento) {
        departamentoRepository.deleteById(iddepartamento);
    }

}
