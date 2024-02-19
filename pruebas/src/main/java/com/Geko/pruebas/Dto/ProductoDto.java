package com.Geko.pruebas.Dto;


import com.Geko.pruebas.Models.Empresa;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.Date;


@Getter @Setter @NoArgsConstructor 
public class ProductoDto {


	private Long id;

	private String Codigo;

	private String Nombre;

	private String caracteristicas;
	private Double Precio;

	private EmpresaDto empresa;
	private Date FechaRegistro; 


	
	
	
}
