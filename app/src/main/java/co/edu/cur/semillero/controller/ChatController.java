package co.edu.cur.semillero.controller;

import co.edu.cur.semillero.model.MensajeChat;
import co.edu.cur.semillero.service.ChatService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@RestController
@RequestMapping("/api/chat")
public class ChatController {
    private final ChatService chatService;

    public ChatController(ChatService chatService) {
        this.chatService = chatService;
    }

    @GetMapping("/{semilleroId}")
    public List<MensajeChat> obtenerMensajes(@PathVariable Long semilleroId) {
        return chatService.obtenerMensajes(semilleroId);
    }

    @PostMapping("/enviar")
    public ResponseEntity<?> enviarMensaje(@RequestBody Map<String, Object> payload) {
        try {
            // Extraer y validar nulidad inmediatamente
            Object uIdObj = Objects.requireNonNull(payload.get("usuarioId"), "usuarioId es requerido");
            Object sIdObj = Objects.requireNonNull(payload.get("semilleroId"), "semilleroId es requerido");
            String text = (String) payload.get("contenido");

            Long uId = Long.valueOf(uIdObj.toString());
            Long sId = Long.valueOf(sIdObj.toString());

            chatService.enviarMensaje(uId, sId, text);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error: " + e.getMessage());
        }
    }
}