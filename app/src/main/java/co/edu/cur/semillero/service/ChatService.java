package co.edu.cur.semillero.service;

import co.edu.cur.semillero.model.MensajeChat;
import co.edu.cur.semillero.model.Semillero;
import co.edu.cur.semillero.model.Usuario;
import co.edu.cur.semillero.repository.MensajeChatRepository;
import co.edu.cur.semillero.repository.SemilleroRepository;
import co.edu.cur.semillero.repository.UsuarioRepository;
import org.springframework.stereotype.Service;
import java.util.List;

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
        Usuario usuario = usuarioRepo.findById(usuarioId).orElseThrow();
        Semillero semillero = semilleroRepo.findById(semilleroId).orElseThrow();

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