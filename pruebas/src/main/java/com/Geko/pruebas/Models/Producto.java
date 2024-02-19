package com.Geko.pruebas.Models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;



@Entity
@Table(name = "product")
@Getter @Setter @NoArgsConstructor 
public class Producto implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy =  GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;
	
	@Column(name = "code", nullable = false)
	private String Codigo;
	
	@Column(name="name",nullable = false)
	@NotNull
	private String Nombre;
	
	@Column(name = "characteristics")
	private String caracteristicas;
	@Column(name="price")
	private Double Precio;

	@ManyToOne(fetch = FetchType.LAZY,optional = false)
	@JoinColumn(name = "companyid")
	@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
	private Empresa empresa;
	
	@Column(name="date",nullable = false)
	@Temporal(TemporalType.DATE)
	@CreatedDate
	private Date FechaRegistro;

	public Producto(Long id, String nombre, Double precio) {
		this.id = id;
		Nombre = nombre;
		Precio = precio;
	}
}
