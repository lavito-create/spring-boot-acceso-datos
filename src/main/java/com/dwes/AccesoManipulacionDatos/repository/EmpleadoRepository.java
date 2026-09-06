package com.dwes.AccesoManipulacionDatos.repository;

import com.dwes.AccesoManipulacionDatos.model.Empleado;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmpleadoRepository extends CrudRepository<Empleado, Long> {
}
