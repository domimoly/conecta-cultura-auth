package cl.duoc.auth.repository;

import cl.duoc.auth.model.Usuario;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
  Optional<Usuario> findByCorreoIgnoreCase(String correo);
}
