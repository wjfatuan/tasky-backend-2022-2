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

import co.edu.uan.software.tasky.entities.U09_post_etiquetas_entity;
import co.edu.uan.software.tasky.repositories.U09_post_etiquetas_repository;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class U09_test_post_etiquetas_controller {
    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;
    @Autowired
    private U09_post_etiquetas_repository repo;

    private String endpoint = "";

    private U09_post_etiquetas_entity u1 = new U09_post_etiquetas_entity("Comida", "Rojo");
    private U09_post_etiquetas_entity u2 = new U09_post_etiquetas_entity("Hogar", "Verde");

    @BeforeEach
    public void init() {
        repo.save(u1);
        repo.save(u2);
        endpoint = "http://localhost:" + port + "/etiqueta/&";
    }

    @Test
    public void createEtiqueta() throws Exception {
        String url = endpoint;
        U09_post_etiquetas_entity data = new U09_post_etiquetas_entity("Hogar", "Verde");
        HttpEntity<U09_post_etiquetas_entity> request = new HttpEntity<>(data);
        ResponseEntity<U09_post_etiquetas_entity> response = restTemplate.exchange(url, HttpMethod.POST, request, U09_post_etiquetas_entity.class);
        assertEquals(200, response.getStatusCodeValue());
        U09_post_etiquetas_entity body = request.getBody();
        assertNotNull(body);
        assertEquals(data.getNombreEtiqueta(), body.getNombreEtiqueta());
        assertEquals(data.getColor(), body.getColor());
    }
}