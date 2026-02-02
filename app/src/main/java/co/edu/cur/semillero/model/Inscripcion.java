package co.edu.cur.semillero.model;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

@Entity
@Table(name = "inscripciones")
@Data
public class Inscripcion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_usuario", nullable = false)
    private Usuario usuario;

    @ManyToOne
    @JoinColumn(name = "id_semillero", nullable = false)
    private Semillero semillero;

    @Column(name = "fecha_union")
    private LocalDateTime fechaUnion;

    @PrePersist
    public void prePersist() {
        this.fechaUnion = LocalDateTime.now();
    }
}