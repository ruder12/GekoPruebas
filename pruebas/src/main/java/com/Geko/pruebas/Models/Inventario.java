package com.Geko.pruebas.Models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
@Table(name = "inventory")
@Getter
@Setter
@NoArgsConstructor
public class Inventario {

    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY,optional = false)
    @JoinColumn(name = "productid")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Producto producto;

    @Column(name="movements",nullable = false)
    @NotNull
    private String movimiento;

    @Column(name="quantity")
    private int cantidad;

    @Column(name="date",nullable = false)
    @Temporal(TemporalType.DATE)
    @CreatedDate
    private Date FechaRegistro;

    public Inventario(Long id, Producto producto, String movimiento, int cantidad) {
        this.id = id;
        this.producto = producto;
        this.movimiento = movimiento;
        this.cantidad = cantidad;
    }
}
