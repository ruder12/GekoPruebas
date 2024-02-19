package com.Geko.pruebas.Models;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "category")
@Getter @Setter @NoArgsConstructor
public class Categoria {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="id")
	private Long id;
	
	@Column(name="nombre")
	@NotNull
	private String nombre;
	


}
