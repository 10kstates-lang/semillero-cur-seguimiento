package co.edu.cur.semillero.repository;
import co.edu.cur.semillero.model.Inscripcion;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface InscripcionRepository extends JpaRepository<Inscripcion, Long> {
    // Buscar todas las inscripciones de un usuario (para ver 'Mis Semilleros')
    List<Inscripcion> findByUsuarioId(Long usuarioId);
    
    // Contar cuántos inscritos hay en un semillero (para el contador)
    long countBySemilleroId(Long semilleroId);
}