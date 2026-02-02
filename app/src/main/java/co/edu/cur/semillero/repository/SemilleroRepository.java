package co.edu.cur.semillero.repository;
import co.edu.cur.semillero.model.Semillero;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SemilleroRepository extends JpaRepository<Semillero, Long> {
}