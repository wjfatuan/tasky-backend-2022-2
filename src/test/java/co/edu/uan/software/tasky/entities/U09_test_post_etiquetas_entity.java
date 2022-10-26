package co.edu.uan.software.tasky.entities;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import co.edu.uan.software.tasky.repositories.U09_post_etiquetas_repository;

@SpringBootTest()
public class U09_test_post_etiquetas_entity {

    @Autowired
    private U09_post_etiquetas_repository repo;

    //Creacion de test con los valores de nombreEtiqueta = Comida y color = Rojo
    @Test
    public void unsavedToString() {
        U09_post_etiquetas_entity de = new U09_post_etiquetas_entity("Comida", "Rojo");
        String expected ="{" +
        " uid='" + de.getUid() + "'" +
        ", nombreEtiqueta='" + "Comida" + "'" +
        ", color='" + "Rojo" + "'" +
        "}";
        assertEquals(expected, de.toString());
    }

    @Test
    public void savedToString() {
        U09_post_etiquetas_entity de = new U09_post_etiquetas_entity("Comida", "Rojo");
        repo.save(de);
        String expected ="{" +
        " uid='" + de.getUid() + "'" +
        ", nombreEtiqueta='" + "Comida" + "'" +
        ", color='" + "Rojo" + "'" +
        "}";
        assertEquals(expected, de.toString());
    }
}