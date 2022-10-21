package co.edu.uan.software.tasky.controllers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.params.provider.Arguments.arguments;

import java.util.List;
import java.util.stream.Stream;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

import co.edu.uan.software.tasky.entities.Usuario;
import co.edu.uan.software.tasky.repositories.UsuariosRepository;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class UsuariosControllerTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;
    @Autowired
    private UsuariosRepository repo;

    private String endpoint = "";
    private static int userCount = 0;
    private Usuario u1 = new Usuario("john.doe" + (++userCount), "secret");
    private Usuario u2 = new Usuario("bart.simpson" + (++userCount), "secret");

    @BeforeEach
    public void init() {
        repo.save(u1);
        repo.save(u2);
        endpoint = "http://localhost:" + port + "/api/v1/usuarios";
    }

    @Test
    public void createUser() throws Exception {
        String url = endpoint;
        Usuario data = new Usuario("john.doe" + (++userCount), "secret");
        data.setToken("newtoken");
        HttpEntity<Usuario> request = new HttpEntity<>(data);
        ResponseEntity<Usuario> response = restTemplate.exchange(url, HttpMethod.POST, request, Usuario.class);
        assertEquals(201, response.getStatusCodeValue());
        Usuario body = request.getBody();
        assertNotNull(body);
        // assertNotNull(body.getId());
        assertEquals(data.getNombreUsuario(), body.getNombreUsuario());
        assertEquals(data.getToken(), body.getToken());
    }

    private static Stream<Arguments> createInvalidUserData() {
        return Stream.of(
                arguments("john.doe", "  "),
                arguments("john.doe", ""),
                arguments("john.doe", null),
                arguments("  ", "secret"),
                arguments("", "secret"),
                arguments(null, "secret"),
                arguments("  ", "  "),
                arguments("", ""),
                arguments(null, null));
    }

    @ParameterizedTest
    @MethodSource("createInvalidUserData")
    public void createInvalidUser(String username, String password) throws Exception {
        String url = endpoint;
        HttpEntity<Usuario> request = new HttpEntity<>(new Usuario(username, password));
        ResponseEntity<Usuario> response = restTemplate.exchange(url, HttpMethod.POST, request, Usuario.class);
        assertEquals(400, response.getStatusCodeValue());
        assertNull(response.getBody());
    }

    @Test
    public void createExistingUser() throws Exception {
        String url = endpoint;
        HttpEntity<Usuario> request = new HttpEntity<>(new Usuario(u1.getNombreUsuario(), u1.getPassword()));
        ResponseEntity<Usuario> response = restTemplate.exchange(url, HttpMethod.POST, request, Usuario.class);
        assertEquals(400, response.getStatusCodeValue());
        assertNull(response.getBody());
    }

    @Test
    public void getUserList() throws Exception {
        String url = endpoint;
        ResponseEntity<List<Usuario>> response = restTemplate.exchange(url, HttpMethod.GET, null,
                new ParameterizedTypeReference<List<Usuario>>() {
                });
        assertEquals(200, response.getStatusCodeValue());
        assertNotNull(response.getBody());
    }

    @Test
    public void getUser() throws Exception {
        String url = endpoint + "/" + u1.getNombreUsuario();
        ResponseEntity<Usuario> response = restTemplate.exchange(url, HttpMethod.GET, null, Usuario.class);
        assertEquals(200, response.getStatusCodeValue());
        assertNotNull(response.getBody());
    }

    @Test
    public void getNonExistingUser() throws Exception {
        String url = endpoint + "/trump";
        ResponseEntity<Usuario> response = restTemplate.exchange(url, HttpMethod.GET, null, Usuario.class);
        assertEquals(404, response.getStatusCodeValue());
        assertNull(response.getBody());
    }

    @Test
    public void updateUser() throws Exception {
        String url = endpoint + "/" + u1.getId();
        Usuario data = new Usuario("homer.simpson", "secret");
        HttpEntity<Usuario> request = new HttpEntity<>(data);
        ResponseEntity<Usuario> response = restTemplate.exchange(url, HttpMethod.PUT, request, Usuario.class);
        assertEquals(200, response.getStatusCodeValue());
        assertNotNull(response.getBody());
        assertNotNull(response.getBody().getId());
        assertEquals(data.getNombreUsuario(), response.getBody().getNombreUsuario());
    }

    @Test
    public void updateNonExisting() throws Exception {
        String url = endpoint + "/5cfc5d7a-124c-451f-9ddc-7cc68d80f083";
        Usuario data = new Usuario("homer.simpson", "secret");
        HttpEntity<Usuario> request = new HttpEntity<>(data);
        ResponseEntity<Usuario> response = restTemplate.exchange(url, HttpMethod.PUT, request, Usuario.class);
        assertEquals(404, response.getStatusCodeValue());
        assertNull(response.getBody());
    }

    @Test
    public void deleteUser() throws Exception {
        String url = endpoint + "/" + u2.getId();
        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.DELETE, null, String.class);
        assertEquals(200, response.getStatusCodeValue());
        assertNull(response.getBody());
    }

    @Test
    public void deleteNonExistingUser() throws Exception {
        String url = endpoint + "/5cfc5d7a-124c-451f-9ddc-7cc68d80f083";
        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.DELETE, null, String.class);
        assertEquals(404, response.getStatusCodeValue());
        assertNull(response.getBody());
    }

}