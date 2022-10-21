package co.edu.uan.software.tasky.entities;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import co.edu.uan.software.tasky.repositories.UsuariosRepository;

@SpringBootTest()
public class UsuarioTest {

    @Autowired
    private UsuariosRepository repo;

    @Test
    public void unsavedToString() {
        Usuario de = new Usuario("john.doe","secret");
        String expected = "Usuario " + de.getId() + ":" + "john.doe";
        assertEquals(expected, de.toString());
    }

    @Test
    public void savedToString() {
        Usuario de = new Usuario("john.doe","secret");
        repo.save(de);
        String expected = "Usuario " + de.getId() + ":" + "john.doe";
        assertEquals(expected, de.toString());
    }
}
