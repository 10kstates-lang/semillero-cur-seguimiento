package co.edu.cur.semillero.model;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

@Entity
@Table(name = "semilleros")
@Data
public class Semillero {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String nombre;

    @Column(columnDefinition = "TEXT")
    private String descripcion;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Facultad facultad; // "carrera" en tu tabla, pero "facultad" es más claro aquí

    @Column(name = "password_acceso", nullable = false)
    private String passwordAcceso;

    @ManyToOne
    @JoinColumn(name = "id_lider", nullable = false)
    private Usuario lider;

    @Column(name = "estado_bloqueo")
    private boolean estadoBloqueo = false; // Control del SuperAdmin

    @Column(name = "fecha_creacion")
    private LocalDateTime fechaCreacion;

    @PrePersist
    public void prePersist() {
        this.fechaCreacion = LocalDateTime.now();
    }
}