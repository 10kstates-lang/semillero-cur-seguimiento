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
import java.util.Objects; // Necesario para la validación final

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
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
        // 1. Preparar datos (Arrange)
        Usuario user = new Usuario();
        user.setNombre("Juan Test");
        user.setEmail("test@cur.edu.co");
        user.setPassword("123456");
        user.setFacultad(Facultad.INGENIERIA_SISTEMAS);

        // 2. Simular comportamiento (Mocking)
        when(usuarioRepository.findByEmail(anyString())).thenReturn(Optional.empty());
        when(passwordEncoder.encode(anyString())).thenReturn("hashed_pass");
        
        // CORRECCIÓN PARA LA LÍNEA 47: Usamos notNull()
        when(usuarioRepository.save(notNull())).thenAnswer(invocation -> invocation.getArgument(0));

        // 3. Ejecutar (Act)
        Usuario tempResultado = usuarioService.registrarUsuario(user);

        // CORRECCIÓN DEFINITIVA PARA LA LÍNEA 59: 
        // Usamos Objects.requireNonNull para obligar al compilador a aceptar que NO es nulo
        Usuario resultado = Objects.requireNonNull(tempResultado, "El resultado no puede ser nulo");

        // 4. Verificar (Assert)
        // Ahora el compilador ya no marcará advertencia en las siguientes líneas
        assertNotNull(resultado);
        assertEquals(Rol.ESTUDIANTE, resultado.getRol());
        assertEquals("hashed_pass", resultado.getPassword());
        assertTrue(resultado.isActivo());
        
        verify(usuarioRepository, times(1)).save(notNull());
    }
}