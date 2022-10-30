package co.edu.uan.software.tasky.entities;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import co.edu.uan.software.tasky.repositories.TareaRepository;

@SpringBootTest()
public class TareaTest {
    @Autowired
    private TareaRepository repo;
    @Test
    public void unsavedToString() {
        Tarea de = new Tarea("Comprar",LocalDate.now(),LocalDate.now().plusDays(10),"Pedro");
        String expected = "Comprar " +String.valueOf( LocalDate.now()) +
        String.valueOf(LocalDate.now().plusDays(10)) + "Predro";
        assertEquals(expected, de.toString());
    }

    @Test
    public void savedToString() {
        Tarea de = new Tarea("Comprar",LocalDate.now(),LocalDate.now().plusDays(10),"Pedro");
        String expected = "Comprar " +String.valueOf( LocalDate.now()) +
        String.valueOf(LocalDate.now().plusDays(10)) + "Predro";
        assertEquals(expected, de.toString());
    }
}
