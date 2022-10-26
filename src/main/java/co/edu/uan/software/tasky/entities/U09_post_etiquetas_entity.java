package co.edu.uan.software.tasky.entities;

import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity(name = "etiqueta")
public class U09_post_etiquetas_entity {

    @Id
    @GeneratedValue
    private UUID uid;
    private String nombreEtiqueta;
    private String color;

    @Override
    public String toString() {
        return "{" +
            " uid='" + getUid() + "'" +
            ", nombreEtiqueta='" + getNombreEtiqueta() + "'" +
            ", color='" + getColor() + "'" +
            "}";
    }

    public UUID getUid() {
        return this.uid;
    }

    public void setUid(UUID uid) {
        this.uid = uid;
    }

    public String getNombreEtiqueta() {
        return this.nombreEtiqueta;
    }

    public void setNombreEtiqueta(String nombreEtiqueta) {
        this.nombreEtiqueta = nombreEtiqueta;
    }

    public String getColor() {
        return this.color;
    }

    public void setColor(String color) {
        this.color = color;
    }

}
