package com.tecsup.demo.modelo.entidades;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.DecimalMin;
import java.math.BigDecimal;

@Entity
@Table(name = "detallepedido")
public class DetallePedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Min(1)
    @Column(name = "cantidad", nullable = false)
    private Integer cantidad;

    @DecimalMin("0.0")
    @Column(name = "precio_total", nullable = false, precision = 10, scale = 2)
    private BigDecimal precioTotal;

    @ManyToOne
    @JoinColumn(name = "pedido", nullable = false)
    private Pedido pedido;

    @ManyToOne
    @JoinColumn(name = "plato", nullable = false)
    private Plato plato;

    // Constructores
    public DetallePedido() {}

    public DetallePedido(Integer cantidad, BigDecimal precioTotal, Pedido pedido, Plato plato) {
        this.cantidad = cantidad;
        this.precioTotal = precioTotal;
        this.pedido = pedido;
        this.plato = plato;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public @Min(1) Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(@Min(1) Integer cantidad) {
        this.cantidad = cantidad;
    }

    public @DecimalMin("0.0") BigDecimal getPrecioTotal() {
        return precioTotal;
    }

    public void setPrecioTotal(@DecimalMin("0.0") BigDecimal precioTotal) {
        this.precioTotal = precioTotal;
    }

    public Pedido getPedido() {
        return pedido;
    }

    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
    }

    public Plato getPlato() {
        return plato;
    }

    public void setPlato(Plato plato) {
        this.plato = plato;
    }
// Getters y Setters
    // ... (omitir por brevedad)

    // toString, equals, hashCode
    // ... (omitir por brevedad)
}
