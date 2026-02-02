package co.edu.cur.semillero.dto;

import co.edu.cur.semillero.model.Facultad;
import lombok.Data;

@Data
public class UsuarioRegistroDTO {
    private String nombre;
    private String correo; // Coincide con la BD
    private String password;
    private Facultad carrera; // INGENIERIA_SISTEMAS o CONTADURIA
}