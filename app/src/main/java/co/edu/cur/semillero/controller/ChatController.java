package co.edu.cur.semillero.controller;

import co.edu.cur.semillero.model.MensajeChat;
import co.edu.cur.semillero.repository.MensajeChatRepository;
import co.edu.cur.semillero.repository.SemilleroRepository;
import co.edu.cur.semillero.repository.UsuarioRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/chat")
public class ChatController {

    private final MensajeChatRepository chatRepository;
    private final UsuarioRepository usuarioRepository;
    private final SemilleroRepository semilleroRepository;

    public ChatController(MensajeChatRepository chatRepository, UsuarioRepository usuarioRepository, SemilleroRepository semilleroRepository) {
        this.chatRepository = chatRepository;
        this.usuarioRepository = usuarioRepository;
        this.semilleroRepository = semilleroRepository;
    }

    // Obtener historial de un semillero
    @GetMapping("/{semilleroId}")
    public List<MensajeChat> obtenerMensajes(@PathVariable Long semilleroId) {
        return chatRepository.findBySemilleroIdOrderByFechaAsc(semilleroId);
    }

    // Guardar nuevo mensaje
    @PostMapping("/enviar")
    public ResponseEntity<?> enviarMensaje(@RequestBody Map<String, Object> payload) {
        MensajeChat nuevo = new MensajeChat();
        nuevo.setContenido((String) payload.get("contenido"));
        nuevo.setUsuario(usuarioRepository.findById(Long.valueOf(payload.get("usuarioId").toString())).orElseThrow());
        nuevo.setSemillero(semilleroRepository.findById(Long.valueOf(payload.get("semilleroId").toString())).orElseThrow());
        
        chatRepository.save(nuevo);
        return ResponseEntity.ok().build();
    }
}