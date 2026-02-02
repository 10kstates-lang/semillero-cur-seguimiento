package co.edu.cur.semillero.service;

import co.edu.cur.semillero.model.Usuario;
import co.edu.cur.semillero.model.Rol;
import co.edu.cur.semillero.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public Usuario registrarUsuario(Usuario usuario) {
        // Validamos usando 'getEmail'
        if (usuarioRepository.findByEmail(usuario.getEmail()).isPresent()) {
            throw new RuntimeException("El correo ya está registrado");
        }

        // Asignamos valores por defecto
        usuario.setRol(Rol.ESTUDIANTE);
        usuario.setPassword(passwordEncoder.encode(usuario.getPassword()));
        usuario.setActivo(true);
        
        return usuarioRepository.save(usuario);
    }
}