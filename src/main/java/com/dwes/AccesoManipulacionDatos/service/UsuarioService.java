package com.dwes.AccesoManipulacionDatos.service;

import com.dwes.AccesoManipulacionDatos.dto.UsuarioRequestDTO;
import com.dwes.AccesoManipulacionDatos.model.Usuario;
import com.dwes.AccesoManipulacionDatos.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class UsuarioService {
    public final UsuarioRepository usuarioRepository;

    @Autowired
    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    // Crear un usuario
    public Usuario saveUsuario(Usuario usuario) {
        return usuarioRepository.save(usuario);
    }

    // Actualizar información de usuario
    public Usuario updateUsuario(Usuario usuario, Long idUsuario) {
        Usuario usuarioDB = usuarioRepository.findById(idUsuario).get();

        /*if (Objects.nonNull(usuario.getId()) && !"".equalsIgnoreCase(String.valueOf(usuario.getId()))) {
            usuarioDB.setId(usuario.getId());
        }*/
        if (Objects.nonNull(usuario.getNombre()) && !"".equalsIgnoreCase(usuario.getNombre())) {
            usuarioDB.setNombre(usuario.getNombre());
        }
        if (Objects.nonNull(usuario.getDireccion()) && !"".equalsIgnoreCase(usuario.getDireccion())) {
            usuarioDB.setDireccion(usuario.getDireccion());
        }
        if (Objects.nonNull(usuario.getCorreo()) && !"".equalsIgnoreCase(usuario.getCorreo())) {
            usuarioDB.setCorreo(usuario.getCorreo());
        }
        if (Objects.nonNull(usuario.getPassword()) && !"".equalsIgnoreCase(usuario.getPassword())) {
            usuarioDB.setPassword(usuario.getPassword());
        }
        return usuarioRepository.save(usuarioDB);

    }

    // Listar usuarios
    public List<UsuarioRequestDTO> listarTodos() {
        List<Usuario> usuarios = (List<Usuario>) usuarioRepository.findAll();
        return usuarios.stream()
                .map(this::mapToRequestDTO)
                .collect(Collectors.toList());
    }

    // Eliminar usuario por ID
    public void eliminarUsuarioById(Long idUsuario) {
        usuarioRepository.deleteById(idUsuario);
    }

    // Mapeo DTO
    public UsuarioRequestDTO mapToRequestDTO(Usuario usuario) {
        return UsuarioRequestDTO.builder()
                .nombre(usuario.getNombre())
                .correo(usuario.getCorreo())
                .build();
    }
}
