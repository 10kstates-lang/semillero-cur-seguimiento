package co.edu.cur.semillero.service;

import co.edu.cur.semillero.model.MensajeChat;
import co.edu.cur.semillero.model.Semillero;
import co.edu.cur.semillero.model.Usuario;
import co.edu.cur.semillero.repository.MensajeChatRepository;
import co.edu.cur.semillero.repository.SemilleroRepository;
import co.edu.cur.semillero.repository.UsuarioRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Objects;

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
        // Validación obligatoria para Java 25
        Long uId = Objects.requireNonNull(usuarioId, "usuarioId es requerido");
        Long sId = Objects.requireNonNull(semilleroId, "semilleroId es requerido");

        Usuario usuario = usuarioRepo.findById(uId)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
        Semillero semillero = semilleroRepo.findById(sId)
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