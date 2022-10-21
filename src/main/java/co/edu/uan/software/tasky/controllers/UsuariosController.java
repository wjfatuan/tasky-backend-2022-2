package co.edu.uan.software.tasky.controllers;

import java.util.List;
import java.util.UUID;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.edu.uan.software.tasky.entities.Usuario;
import co.edu.uan.software.tasky.repositories.UsuariosRepository;

/**
 * This is the Rest Controller for the Usuario entity. It provides basic CRUD
 * functionality.
 * - Create -> POST
 * - Read -> GET
 * - Update -> PUT (complete) and PATCH (partial)
 * - Delete -> DELETE
 */
@RestController
@RequestMapping(path = "${apiPrefix}/usuarios")
public class UsuariosController {

    private final UsuariosRepository repo;

    UsuariosController(UsuariosRepository r) {
        this.repo = r;
    }

    /**
     * Creates a new usuario in the database.
     * 
     * @param usuario The new usuario
     * @return Returns the usuario with an assigned PK
     */
    @PostMapping
    public ResponseEntity<Usuario> createusuario(@RequestBody Usuario usuario) {
        if (usuario.getNombreUsuario() == null || usuario.getPassword() == null
                || usuario.getNombreUsuario().isEmpty() || usuario.getPassword().isEmpty()
                || usuario.getNombreUsuario().isBlank() || usuario.getPassword().isBlank()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        if (repo.findByNombreUsuario(usuario.getNombreUsuario()).isPresent()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(this.repo.save(usuario), HttpStatus.CREATED);
    }

    /**
     * Returns a list with all the users.
     * 
     * @return Returns all the usuarios in database
     */
    @GetMapping
    public List<Usuario> findAllusuarios() {
        return this.repo.findAll();
    }

    /**
     * Finds a user given its username.
     * 
     * @param name The name of the usuario you are looking for.
     * @return Returns the usuario with the given name or a Not Found if the usuario
     *         doesn't exist.
     */
    @GetMapping("/{name}")
    public ResponseEntity<Usuario> findUsuario(@PathVariable("name") String name) {
        return this.repo.findByNombreUsuario(name)
                .map(result -> new ResponseEntity<>(result, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    /**
     * Updates an existing usuario in the database.
     * 
     * @param usuario The usuario to be updated
     * @return Returns the usuario with all fields changed, or throws an exception
     *         if the entoty doesn't exist.
     */
    @PutMapping("/{id}")
    public ResponseEntity<Usuario> updateusuario(@PathVariable("id") String usuarioId, 
                                                @RequestBody Usuario usuario) {
        UUID uid = UUID.fromString(usuarioId);
        if (repo.existsById(uid)) {
            usuario.setId(uid);
            return new ResponseEntity<Usuario>(repo.save(usuario), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Deletes an existing usuario in the database.
     * 
     * @param usuarioId The usuarioId to be deleted
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteusuario(@PathVariable("id") String usuarioId) {
        UUID uid = UUID.fromString(usuarioId);
        try {
            repo.deleteById(uid);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (EmptyResultDataAccessException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}
