package co.edu.cur.semillero.model;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

@Entity
@Table(name = "moderacion_interna")
@Data
public class ModeracionInterna {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_usuario_afectado", nullable = false)
    private Usuario usuarioAfectado;

    @ManyToOne
    @JoinColumn(name = "id_semillero", nullable = false)
    private Semillero semillero;

    @ManyToOne
    @JoinColumn(name = "id_autor", nullable = false)
    private Usuario autor; // Quien ejecutó la acción

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private AccionModeracion accion;

    private LocalDateTime fecha;

    @PrePersist
    public void prePersist() {
        this.fecha = LocalDateTime.now();
    }
}