package co.edu.uan.software.tasky.repositories;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import co.edu.uan.software.tasky.entities.Usuario;

/**
 * This is the repository for @see co.edu.uan.software.tasky.entities.Usuario
 */
public interface UsuariosRepository extends JpaRepository<Usuario, UUID> {

    Optional<Usuario> findByNombreUsuario(String nombreUsuario);

}
