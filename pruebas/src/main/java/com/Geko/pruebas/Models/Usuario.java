package com.Geko.pruebas.Models;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
@Table(name = "usuario")
@Getter @Setter @NoArgsConstructor 
public class Usuario {


	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;
	
	@Column(name="name")
	private String nombre;

	@Column(name = "user",unique = true)
	@NotNull
	@Email
	private String email;

	@Column(name = "password")
	private String password;
	
	@Column(name="token")
	private String token;
	@Column(name="role")
	private String rol;


	
	
	
	
}
