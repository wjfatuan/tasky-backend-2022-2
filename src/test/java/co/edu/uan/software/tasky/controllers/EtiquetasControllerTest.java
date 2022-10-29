package co.edu.uan.software.tasky.controllers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

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

import co.edu.uan.software.tasky.entities.EtiquetasEntity;
import co.edu.uan.software.tasky.repositories.EtiquetasRepository;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class EtiquetasControllerTest {
    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;
    @Autowired
    private EtiquetasRepository repo;

    private String endpoint = "";

    private EtiquetasEntity u1 = new EtiquetasEntity("Comida", "Rojo");
    private EtiquetasEntity u2 = new EtiquetasEntity("Hogar", "Verde");

    @BeforeEach
    public void init() {
        repo.save(u1);
        repo.save(u2);
        endpoint = "http://localhost:" + port + "/etiqueta/&";
    }

    @Test
    public void createEtiqueta() throws Exception {
        String url = endpoint;
        EtiquetasEntity data = new EtiquetasEntity("Hogar", "Verde");
        HttpEntity<EtiquetasEntity> request = new HttpEntity<>(data);
        ResponseEntity<EtiquetasEntity> response = restTemplate.exchange(url, HttpMethod.POST, request, EtiquetasEntity.class);
        assertEquals(200, response.getStatusCodeValue());
        EtiquetasEntity body = request.getBody();
        assertNotNull(body);
        assertEquals(data.getNombreEtiqueta(), body.getNombreEtiqueta());
        assertEquals(data.getColor(), body.getColor());
    }
}