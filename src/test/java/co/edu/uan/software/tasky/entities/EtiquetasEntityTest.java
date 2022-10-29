package co.edu.uan.software.tasky.entities;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import co.edu.uan.software.tasky.repositories.EtiquetasRepository;

@SpringBootTest()
public class EtiquetasEntityTest {

    @Autowired
    private EtiquetasRepository repo;

    //Creacion de test con los valores de nombreEtiqueta = Comida y color = Rojo
    @Test
    public void unsavedToString() {
        EtiquetasEntity de = new EtiquetasEntity("Comida", "Rojo");
        String expected ="{" +
        " uid='" + de.getUid() + "'" +
        ", nombreEtiqueta='" + "Comida" + "'" +
        ", color='" + "Rojo" + "'" +
        "}";
        assertEquals(expected, de.toString());
    }

    @Test
    public void savedToString() {
        EtiquetasEntity de = new EtiquetasEntity("Comida", "Rojo");
        repo.save(de);
        String expected ="{" +
        " uid='" + de.getUid() + "'" +
        ", nombreEtiqueta='" + "Comida" + "'" +
        ", color='" + "Rojo" + "'" +
        "}";
        assertEquals(expected, de.toString());
    }
}