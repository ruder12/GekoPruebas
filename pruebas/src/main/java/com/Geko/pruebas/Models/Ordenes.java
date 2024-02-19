package com.Geko.pruebas.Models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "orders")
@Getter
@Setter
@NoArgsConstructor
public class Ordenes {


    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "TotalPrice")
    private Double totalPrecio;

    @ManyToOne(fetch = FetchType.LAZY,optional = false)
    @JoinColumn(name = "customerId")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Cliente cliente;

    @Column(name="orderDate",nullable = false)
    @Temporal(TemporalType.DATE)
    @CreatedDate
    private Date fecha;

}
