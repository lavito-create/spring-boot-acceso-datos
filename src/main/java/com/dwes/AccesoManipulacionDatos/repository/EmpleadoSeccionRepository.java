package com.dwes.AccesoManipulacionDatos.repository;

import com.dwes.AccesoManipulacionDatos.model.EmpleadoSeccion;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.Set;

@Repository
public interface EmpleadoSeccionRepository extends CrudRepository<EmpleadoSeccion, Long> {
    Set<EmpleadoSeccion> findByEmpleadoId(Long idEmpleado);
    Set<EmpleadoSeccion> findBySeccionId(Long idSeccion);

    Optional<EmpleadoSeccion> findBySeccionIdAndEmpleadoId(Long idSeccion, Long idEmpleado);

    void deleteByEmpleadoIdAndSeccionId(Long empleadoId, Long seccionId);
}
