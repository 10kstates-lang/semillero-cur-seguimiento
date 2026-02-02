package co.edu.cur.semillero.service;

import co.edu.cur.semillero.model.Usuario;
import co.edu.cur.semillero.model.Rol;
import co.edu.cur.semillero.model.Facultad;
import co.edu.cur.semillero.repository.UsuarioRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UsuarioServiceTest {

    @Mock
    private UsuarioRepository usuarioRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private UsuarioService usuarioService;

    @Test
    void registrarUsuario_DebeGuardarUsuarioExitosamente() {
        // 1. Preparar datos (Arrange) - Usamos el constructor o setters
        Usuario nuevoUsuario = new Usuario();
        nuevoUsuario.setNombre("Juan Test");
        nuevoUsuario.setEmail("test@estudiante.com"); // Correcto: setEmail
        nuevoUsuario.setPassword("123456");
        nuevoUsuario.setFacultad(Facultad.INGENIERIA_SISTEMAS);

        // 2. Simular comportamiento (Mocking) - Usamos findByEmail
        when(usuarioRepository.findByEmail("test@estudiante.com")).thenReturn(Optional.empty());
        when(passwordEncoder.encode("123456")).thenReturn("hashed_password");
        // Simulamos que al guardar, devuelve el mismo usuario
        when(usuarioRepository.save(any(Usuario.class))).thenAnswer(invocation -> invocation.getArgument(0));

        // 3. Ejecutar (Act)
        Usuario resultado = usuarioService.registrarUsuario(nuevoUsuario);

        // 4. Verificar (Assert)
        assertNotNull(resultado);
        assertEquals(Rol.ESTUDIANTE, resultado.getRol()); // Debe ser ESTUDIANTE
        assertEquals("hashed_password", resultado.getPassword()); // La clave debe estar hasheada
        assertTrue(resultado.isActivo());
        
        // Verificamos que se llamó a save() una vez
        verify(usuarioRepository).save(any(Usuario.class));
    }
}