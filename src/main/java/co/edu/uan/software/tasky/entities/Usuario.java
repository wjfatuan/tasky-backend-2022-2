package co.edu.uan.software.tasky.entities;

import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

/**
 * Usuario Entity, it's not complete, it's an example.
 */
@Entity(name = "usuarios")
public class Usuario {

    @Id
    @GeneratedValue
    @Column(columnDefinition = "UUID")
    private UUID id;
    private String nombreUsuario;
    // @JsonProperty(access = Access.WRITE_ONLY) // we don't want to return the user
    // password
    private String password;
    @JsonProperty(access = Access.WRITE_ONLY)
    private String token;

    public Usuario() {
    }

    /**
     * Creates a new user with name and password.
     * @param u username
     * @param p password
     */
    public Usuario(String u, String p) {
        this.nombreUsuario = u;
        this.password = p;
        this.token = "null";
    }

    /**
     * Returns a string representing the user.
     */
    @Override
    public String toString() {
        return "Usuario " + id + ":" + nombreUsuario;
    }

    /**
     * @return UUID return the id
     */
    public UUID getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(UUID id) {
        this.id = id;
    }

    /**
     * @return String return the nombreUsuario
     */
    public String getNombreUsuario() {
        return nombreUsuario;
    }

    /**
     * @param nombreUsuario the nombreUsuario to set
     */
    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    /**
     * @return String return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * @return String return the token
     */
    public String getToken() {
        return token;
    }

    /**
     * @param token the token to set
     */
    public void setToken(String token) {
        this.token = token;
    }

}
