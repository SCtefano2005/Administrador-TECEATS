package com.tecsup.demo.modelo.entidades;

import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "restaurante")
public class Restaurante {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank
    @Column(name = "nombre", nullable = false)
    private String nombre;

    @NotBlank
    @Column(name = "direccion", nullable = false)
    private String direccion;

    @Size(max = 20)
    @Column(name = "telefono")
    private String telefono;

    @Size(max = 100)
    @Column(name = "tipo_cocina")
    private String tipoCocina;

    @DecimalMin("0.0")
    @DecimalMax("5.0")
    @Column(name = "calificacion")
    private Double calificacion;

    @ManyToOne
    @JoinColumn(name = "id_dueno", nullable = false)
    private DuenoRestaurante dueno;

    @Column(name = "delivery_dentro", nullable = false)
    private Boolean deliveryDentro = false;

    @Column(name = "imagen_url")
    private String imagenUrl;

    @NotBlank
    @Column(name = "status", nullable = false, length = 10)
    private String status = "Pendiente";

    // Constructores
    public Restaurante() {}

    public Restaurante(String nombre, String direccion, String tipoCocina, DuenoRestaurante dueno) {
        this.nombre = nombre;
        this.direccion = direccion;
        this.tipoCocina = tipoCocina;
        this.dueno = dueno;
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

    public @NotBlank String getDireccion() {
        return direccion;
    }

    public void setDireccion(@NotBlank String direccion) {
        this.direccion = direccion;
    }

    public @Size(max = 20) String getTelefono() {
        return telefono;
    }

    public void setTelefono(@Size(max = 20) String telefono) {
        this.telefono = telefono;
    }

    public @Size(max = 100) String getTipoCocina() {
        return tipoCocina;
    }

    public void setTipoCocina(@Size(max = 100) String tipoCocina) {
        this.tipoCocina = tipoCocina;
    }

    public DuenoRestaurante getDueno() {
        return dueno;
    }

    public void setDueno(DuenoRestaurante dueno) {
        this.dueno = dueno;
    }

    public @DecimalMin("0.0") @DecimalMax("5.0") Double getCalificacion() {
        return calificacion;
    }

    public void setCalificacion(@DecimalMin("0.0") @DecimalMax("5.0") Double calificacion) {
        this.calificacion = calificacion;
    }

    public Boolean getDeliveryDentro() {
        return deliveryDentro;
    }

    public void setDeliveryDentro(Boolean deliveryDentro) {
        this.deliveryDentro = deliveryDentro;
    }

    public String getImagenUrl() {
        return imagenUrl;
    }

    public void setImagenUrl(String imagenUrl) {
        this.imagenUrl = imagenUrl;
    }

    public @NotBlank String getStatus() {
        return status;
    }

    public void setStatus(@NotBlank String status) {
        this.status = status;
    }
// Getters y Setters
    // ... (omitir por brevedad)

    // toString, equals, hashCode
    // ... (omitir por brevedad)
}
