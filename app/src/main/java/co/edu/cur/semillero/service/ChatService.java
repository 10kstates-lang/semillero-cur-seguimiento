package co.edu.cur.semillero.service;

import co.edu.cur.semillero.model.*;
import co.edu.cur.semillero.repository.*;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Objects; // Para validación de nulidad

@Service
public class ChatService {
    private final MensajeChatRepository mensajeRepo;
    private final UsuarioRepository usuarioRepo;
    private final SemilleroRepository semilleroRepo;

    public ChatService(MensajeChatRepository mensajeRepo, UsuarioRepository usuarioRepo, SemilleroRepository semilleroRepo) {
        this.mensajeRepo = mensajeRepo;
        this.usuarioRepo = usuarioRepo;
        this.semilleroRepo = semilleroRepo;
    }

    public MensajeChat enviarMensaje(Long usuarioId, Long semilleroId, String contenido) {
        // Validación explícita para eliminar el error de "Null type safety"
        Objects.requireNonNull(usuarioId, "El ID de usuario no puede ser nulo");
        Objects.requireNonNull(semilleroId, "El ID de semillero no puede ser nulo");

        Usuario usuario = usuarioRepo.findById(usuarioId)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
        Semillero semillero = semilleroRepo.findById(semilleroId)
                .orElseThrow(() -> new RuntimeException("Semillero no encontrado"));

        MensajeChat mensaje = new MensajeChat();
        mensaje.setUsuario(usuario);
        mensaje.setSemillero(semillero);
        mensaje.setContenido(contenido);
        
        return mensajeRepo.save(mensaje);
    }

    public List<MensajeChat> obtenerMensajes(Long semilleroId) {
        return mensajeRepo.findBySemilleroIdOrderByFechaAsc(semilleroId);
    }
}