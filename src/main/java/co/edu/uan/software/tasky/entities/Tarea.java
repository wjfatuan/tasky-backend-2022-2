package co.edu.uan.software.tasky.entities;

    import java.sql.Date;
import java.util.UUID;

import javax.persistence.Id;

import javax.persistence.Entity;

/*

*/

@Entity
public class Tarea {
    @Id
    private UUID uid;
    private String nombreTarea;
    private Date fechaCreacion;
    private Date fechaCierre;
    private String usuario;
    private String etiquetas;
    private String estado;

    public UUID getUid() {
        return this.uid;
    }

    public void setUid(UUID uid) {
        this.uid = uid;
    }

    public String getNombreTarea() {
        return this.nombreTarea;
    }

    public void setNombreTarea(String nombreTarea) {
        this.nombreTarea = nombreTarea;
    }

    public Date getFechaCreacion() {
        return this.fechaCreacion;
    }

    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public Date getFechaCierre() {
        return this.fechaCierre;
    }

    public void setFechaCierre(Date fechaCierre) {
        this.fechaCierre = fechaCierre;
    }

    public String getUsuario() {
        return this.usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getEtiquetas() {
        return this.etiquetas;
    }

    public void setEtiquetas(String etiquetas) {
        this.etiquetas = etiquetas;
    }

    public String getEstado() {
        return this.estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
    
}
