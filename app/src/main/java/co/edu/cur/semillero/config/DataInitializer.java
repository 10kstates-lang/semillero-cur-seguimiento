package co.edu.cur.semillero.config;

import co.edu.cur.semillero.model.Facultad;
import co.edu.cur.semillero.model.Rol;
import co.edu.cur.semillero.model.Usuario;
import co.edu.cur.semillero.repository.UsuarioRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class DataInitializer {

    @Bean
    CommandLineRunner initDatabase(UsuarioRepository repository, PasswordEncoder passwordEncoder) {
        return args -> {
            // Verificamos si ya existe el admin usando findByEmail
            if (repository.findByEmail("admin@cur.edu.co").isEmpty()) {
                Usuario admin = new Usuario();
                admin.setNombre("Super Admin");
                admin.setEmail("admin@cur.edu.co"); // Corregido: setEmail
                admin.setPassword(passwordEncoder.encode("admin123"));
                admin.setRol(Rol.SUPERADMIN);
                admin.setFacultad(Facultad.INGENIERIA_SISTEMAS); // Corregido: setFacultad
                admin.setActivo(true); // Corregido: setActivo

                repository.save(admin);
                System.out.println(">> SuperAdmin creado: admin@cur.edu.co / admin123");
            }
        };
    }
}