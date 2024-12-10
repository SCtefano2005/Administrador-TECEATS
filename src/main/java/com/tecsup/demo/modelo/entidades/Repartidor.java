package com.tecsup.demo.modelo.entidades;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "repartidor")
public class Repartidor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank
    @Column(name = "nombre", nullable = false)
    private String nombre;

    @Size(max = 20)
    @Column(name = "telefono")
    private String telefono;

    @NotBlank
    @Column(name = "vehiculo", nullable = false, length = 20)
    private String vehiculo;

    @ManyToOne
    @JoinColumn(name = "restaurante", nullable = false)
    private Restaurante restaurante;

    // Constructores
    public Repartidor() {}

    public Repartidor(String nombre, String vehiculo, Restaurante restaurante) {
        this.nombre = nombre;
        this.vehiculo = vehiculo;
        this.restaurante = restaurante;
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

    public @NotBlank String getVehiculo() {
        return vehiculo;
    }

    public void setVehiculo(@NotBlank String vehiculo) {
        this.vehiculo = vehiculo;
    }

    public @Size(max = 20) String getTelefono() {
        return telefono;
    }

    public void setTelefono(@Size(max = 20) String telefono) {
        this.telefono = telefono;
    }

    public Restaurante getRestaurante() {
        return restaurante;
    }

    public void setRestaurante(Restaurante restaurante) {
        this.restaurante = restaurante;
    }
// Getters y Setters
    // ... (omitir por brevedad)

    // toString, equals, hashCode
    // ... (omitir por brevedad)
}
