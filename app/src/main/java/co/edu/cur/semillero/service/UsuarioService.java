package co.edu.cur.semillero.service;

import co.edu.cur.semillero.model.*;
import co.edu.cur.semillero.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.Objects;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public Usuario registrarUsuario(Usuario usuario) {
        // Validar que el objeto no sea nulo al entrar
        Objects.requireNonNull(usuario, "El usuario no puede ser nulo");

        if (usuarioRepository.findByEmail(usuario.getEmail()).isPresent()) {
            throw new RuntimeException("El correo ya está registrado");
        }

        usuario.setRol(Rol.ESTUDIANTE);
        usuario.setPassword(passwordEncoder.encode(usuario.getPassword()));
        usuario.setActivo(true);
        
        Usuario guardado = usuarioRepository.save(usuario);
        return Objects.requireNonNull(guardado, "Error al guardar");
    }
}