package com.Geko.pruebas.Models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "company")
@Getter
@Setter
@NoArgsConstructor
public class Empresa {

    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "nit", nullable = false)
    private String nit;

    @Column(name = "name", nullable = false)
    private String nombre;
    @Column(name = "address", nullable = true)
    private String address;
    @Column(name = "phone", nullable = true)
    private String telefono;

    @Column(name="date",nullable = false)
    @Temporal(TemporalType.DATE)
    @CreatedDate
    private Date fecha;

    public Empresa(Long id, String nit, String nombre, String address, String telefono) {
        this.id = id;
        this.nit = nit;
        this.nombre = nombre;
        this.address = address;
        this.telefono = telefono;
    }
}
