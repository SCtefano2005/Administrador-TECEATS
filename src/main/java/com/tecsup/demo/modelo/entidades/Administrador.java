package com.tecsup.demo.modelo.entidades;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "administrador")
public class Administrador {

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
    @Column(name = "contrasena", nullable = false, length = 255)
    private String contrasena;

    // Constructores
    public Administrador() {}

    public Administrador(String nombre, String correoElectronico, String contrasena) {
        this.nombre = nombre;
        this.correoElectronico = correoElectronico;
        this.contrasena = contrasena;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public @NotBlank String getNombre() {
        return nombre;
    }

    public void setNombre(@NotBlank String nombre) {
        this.nombre = nombre;
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

    public @NotBlank String getContrasena() {
        return contrasena;
    }

    public void setContrasena(@NotBlank String contrasena) {
        this.contrasena = contrasena;
    }
// Getters y Setters
    // ... (omitir por brevedad)

    // toString, equals, hashCode
    // ... (omitir por brevedad)
}
