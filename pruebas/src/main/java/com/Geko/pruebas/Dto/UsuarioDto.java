package com.Geko.pruebas.Dto;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

@Getter @Setter @NoArgsConstructor 
public class UsuarioDto {


	private Long id;

	private String nombre;

	private String email;
	private String password;
	private String token;
	private String rol;


	
	
	
	
}
