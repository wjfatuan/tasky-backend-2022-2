package co.edu.uan.software.tasky.entities;

import java.sql.Date;
import java.time.LocalDate;
import java.util.UUID;

import javax.persistence.Id;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;

/*

*/

@Entity (name = "tarea")
public class Tarea {
    @Id
    @GeneratedValue
    @Column(columnDefinition = "UUID")
    private UUID uid;
    private String nombreTarea;
    private LocalDate fechaCreacion;
    private LocalDate fechaCierre;
    private Usuario usuario;
    private String etiquetas;
    private String estado;

    /**
     * @return UUID return the uid
     */
    public UUID getUid() {
        return this.uid;
    }
    /**
     * @param uid the uid to set
     */
    public void setUid(UUID uid) {
        this.uid = uid;
    }
    /**
     * @return String return the nombreTarea
     */
    public String getNombreTarea() {
        return this.nombreTarea;
    }
    /**
     * @param nombreTarea the nombreTarea to set
     */
    public void setNombreTarea(String nombreTarea) {
        this.nombreTarea = nombreTarea;
    }
    /**
     * @return Date return the fechaCreacion
     */
    public LocalDate getFechaCreacion() {
        return this.fechaCreacion;
    }
    /**
     * @param fechaCreacion the fechaCreacion to set
     */
    public void setFechaCreacion(LocalDate fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }
    /**
     * @return Date return the fechaCierre
     */
    public LocalDate getFechaCierre() {
        return this.fechaCierre;
    }

    /**
     * @param fechaCierre the fechaCierre to set
     */
    public void setFechaCierre(LocalDate fechaCierre) {
        this.fechaCierre = fechaCierre;
    }

    /**
     * @return Strig return the usuario
     */
    public Usuario getUsuario() {
        return this.usuario;
    }

    /**
     * @param usuario the usuario to set
     */
    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    /**
     * @return String return the etiquetas
    */
    public String getEtiquetas() {
        return this.etiquetas;
    }

    /**
     * @param etiquetas  the etiquetas to set
     */
    public void setEtiquetas(String etiquetas) {
        this.etiquetas = etiquetas;
    }

    /**
     * @return String return estado
     */
    public String getEstado() {
        return this.estado;
    }

    /** 
     * @param estado the estado to set
    */
    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Tarea() {
    }

    public Tarea(UUID uid, String nombreTarea, LocalDate fechaCreacion, LocalDate fechaCierre, Usuario usuario, String etiquetas,
            String estado) {
        this.uid = uid;
        this.nombreTarea = nombreTarea;
        this.fechaCreacion = fechaCreacion;
        this.fechaCierre = fechaCierre;
        this.usuario = usuario;
        this.etiquetas = etiquetas;
        this.estado = estado;
    }
    public Tarea(String nombreTarea, LocalDate fechaCreacion, LocalDate fechaCierre, Usuario usuario, String etiquetas) {
        this.nombreTarea = nombreTarea;
        this.fechaCreacion = fechaCreacion;
        this.fechaCierre = fechaCierre;
        this.usuario = usuario;
        this.etiquetas = etiquetas;
    }
    public Tarea(String nombreTarea, LocalDate fechaCreacion, LocalDate fechaCierre, Usuario usuario) {
        this.nombreTarea = nombreTarea;
        this.fechaCreacion = fechaCreacion;
        this.fechaCierre = fechaCierre;
        this.usuario = usuario;
    }
    @Override
    public String toString() {
        return "Tarea [nombreTarea=" + nombreTarea + ", fechaCreacion=" + fechaCreacion + ", fechaCierre=" + fechaCierre
                + ", usuario=" + usuario + "]";
    }
    
    
}
