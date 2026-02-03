package co.edu.cur.semillero.model;

import jakarta.persistence.*;

@Entity
@Table(name = "usuarios")
@SuppressWarnings("all") 
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nombre = "";

    @Column(nullable = false, unique = true)
    private String email = "";

    @Column(nullable = false)
    private String password = "";

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Rol rol = Rol.ESTUDIANTE; 

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Facultad facultad = Facultad.INGENIERIA_SISTEMAS;

    @Column(nullable = false)
    private boolean activo = true;

    // Getters y Setters con validación de nulidad interna
    public Long getId() { return id; }
    
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = (nombre != null) ? nombre : ""; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = (email != null) ? email : ""; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = (password != null) ? password : ""; }

    public Rol getRol() { return rol; }
    public void setRol(Rol rol) { this.rol = (rol != null) ? rol : Rol.ESTUDIANTE; }

    public Facultad getFacultad() { return facultad; }
    public void setFacultad(Facultad facultad) { this.facultad = (facultad != null) ? facultad : Facultad.INGENIERIA_SISTEMAS; }

    public boolean isActivo() { return activo; }
    public void setActivo(boolean activo) { this.activo = activo; }
}