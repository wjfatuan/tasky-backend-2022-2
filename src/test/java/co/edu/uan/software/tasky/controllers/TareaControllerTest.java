package co.edu.uan.software.tasky.controllers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.time.LocalDate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

import co.edu.uan.software.tasky.entities.Tarea;
import co.edu.uan.software.tasky.repositories.TareaRepository;


@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class TareaControllerTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;
    
    @Autowired
    private TareaRepository repo;
    private String endpoint = "";

    private Tarea u1 = new Tarea("Comprar",LocalDate.now(),LocalDate.now().plusDays(10),"Pedro");
    private Tarea u2 = new Tarea("Trabajar",LocalDate.now(),LocalDate.now().plusDays(10),"Alfonso");
    
    @BeforeEach
    public void init() {
        repo.save(u1);
        repo.save(u2);
        endpoint = "http://localhost:" + port + "/tarea/&";
    }
     
    @Test
    public void createTarea() throws Exception {
        String url = endpoint;
        Tarea data = new Tarea("Estudiar",LocalDate.now(),LocalDate.now().plusDays(10),"Bob");
        HttpEntity<Tarea> request = new HttpEntity<>(data);
        ResponseEntity<Tarea> response = restTemplate.exchange(url, HttpMethod.POST, request, Tarea.class);
        assertEquals(200, response.getStatusCodeValue());
        Tarea body = request.getBody();
        assertNotNull(body);
        assertEquals(data.getNombreTarea(), body.getNombreTarea());
        assertEquals(data.getUsuario(), body.getUsuario());
        assertEquals(data.getFechaCierre(), body.getFechaCierre());
        assertEquals(data.getFechaCreacion(), body.getFechaCreacion());
    }
}