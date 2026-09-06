package com.dwes.AccesoManipulacionDatos.service;

import com.dwes.AccesoManipulacionDatos.dto.SeccionRequestDTO;
import com.dwes.AccesoManipulacionDatos.model.Seccion;
import com.dwes.AccesoManipulacionDatos.repository.SeccionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.ParameterResolutionDelegate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SeccionService {
    private final SeccionRepository seccionRepository;

    @Autowired
    public SeccionService(SeccionRepository seccionRepository) {
        this.seccionRepository = seccionRepository;
    }

    //Crear seccion
    public Seccion saveSeccion(Seccion seccion) {
        return seccionRepository.save(seccion);
    }

    // LiSTAR secciones
    public List<SeccionRequestDTO> listarTodas() {
        List<Seccion> secciones= (List<Seccion>) seccionRepository.findAll();
        return secciones.stream()
                .map(this::mapToRequestDTO)
                .collect(Collectors.toList());
    }

    //Eliminar Secciones
    public void deleteSeccionById(Long idseccion) {
        seccionRepository.deleteById(idseccion);
    }


    // mAPEO dto
    private SeccionRequestDTO mapToRequestDTO(Seccion seccion) {
        return SeccionRequestDTO.builder()
                .nombre(seccion.getNombre())
                .build();
    }
}
