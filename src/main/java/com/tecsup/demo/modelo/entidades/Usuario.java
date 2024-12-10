package com.tecsup.demo.modelo.entidades;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "usuario")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank
    @Column(name = "nombre", nullable = false)
    private String nombre;

    @NotBlank
    @Email
    @Column(name = "correo_electronico", nullable = false, unique = true, length = 254)
    private String correoElectronico;

    @Size(max = 20)
    @Column(name = "telefono")
    private String telefono;

    @NotBlank
    @Column(name = "google_id", nullable = false, unique = true)
    private String googleId;

    @Column(name = "token_acceso")
    private String tokenAcceso;

    @Column(name = "token_refresh")
    private String tokenRefresh;

    // Constructores
    public Usuario() {}

    public Usuario(String nombre, String correoElectronico, String telefono, String googleId) {
        this.nombre = nombre;
        this.correoElectronico = correoElectronico;
        this.telefono = telefono;
        this.googleId = googleId;
    }

    public @NotBlank String getNombre() {
        return nombre;
    }

    public void setNombre(@NotBlank String nombre) {
        this.nombre = nombre;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public @NotBlank @Email String getCorreoElectronico() {
        return correoElectronico;
    }

    public void setCorreoElectronico(@NotBlank @Email String correoElectronico) {
        this.correoElectronico = correoElectronico;
    }

    public @Size(max = 20) String getTelefono() {
        return telefono;
    }

    public void setTelefono(@Size(max = 20) String telefono) {
        this.telefono = telefono;
    }

    public @NotBlank String getGoogleId() {
        return googleId;
    }

    public void setGoogleId(@NotBlank String googleId) {
        this.googleId = googleId;
    }

    public String getTokenAcceso() {
        return tokenAcceso;
    }

    public void setTokenAcceso(String tokenAcceso) {
        this.tokenAcceso = tokenAcceso;
    }

    public String getTokenRefresh() {
        return tokenRefresh;
    }

    public void setTokenRefresh(String tokenRefresh) {
        this.tokenRefresh = tokenRefresh;
    }
// Getters y Setters
    // ... (omitir por brevedad)

    // toString, equals, hashCode
    // ... (omitir por brevedad)
}
