package co.edu.cur.semillero.repository;
import co.edu.cur.semillero.model.MensajeChat;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface MensajeChatRepository extends JpaRepository<MensajeChat, Long> {
    // Traer los mensajes de un semillero ordenados por fecha
    List<MensajeChat> findBySemilleroIdOrderByFechaAsc(Long semilleroId);
}