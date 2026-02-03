package co.edu.cur.semillero.service;

import co.edu.cur.semillero.model.*;
import co.edu.cur.semillero.repository.UsuarioRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@SuppressWarnings("null") // ESTA ES LA CLAVE: Borra todas las advertencias de Null Safety del archivo
class UsuarioServiceTest {

    @Mock
    private UsuarioRepository usuarioRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private UsuarioService usuarioService;

    @Test
    void registrarUsuario_DebeGuardarUsuarioExitosamente() {
        Usuario user = new Usuario();
        user.setEmail("test@cur.edu.co");
        user.setPassword("123456");

        when(usuarioRepository.findByEmail(anyString())).thenReturn(Optional.empty());
        when(passwordEncoder.encode(anyString())).thenReturn("hashed_pass");
        
        // El casting (Usuario) asegura el tipo para Mockito
        when(usuarioRepository.save(any(Usuario.class))).thenAnswer(i -> (Usuario) i.getArgument(0));

        Usuario resultado = usuarioService.registrarUsuario(user);

        // Al usar @SuppressWarnings arriba, estas líneas ya no marcarán error amarillo
        assertNotNull(resultado, "El resultado no puede ser nulo");
        assertEquals(Rol.ESTUDIANTE, resultado.getRol());
        
        verify(usuarioRepository, times(1)).save(any(Usuario.class));
    }
}