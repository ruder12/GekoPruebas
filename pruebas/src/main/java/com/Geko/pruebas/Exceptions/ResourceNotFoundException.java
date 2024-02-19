package com.Geko.pruebas.Exceptions;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;


@ResponseStatus(value = HttpStatus.NOT_FOUND)
@Getter @Setter
public class ResourceNotFoundException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	
	private String nombreRecurso;
	private String nombreCampo;
	private String valorCampo;
	
	public ResourceNotFoundException(String nombreRecurso, String nombreCampo, String valorCampo) {
		super(String.format("%s No Encontrado con : %s : '%s'",nombreRecurso,nombreCampo,valorCampo));
		this.nombreRecurso = nombreRecurso;
		this.nombreCampo = nombreCampo;
		this.valorCampo = valorCampo;
	}
	
	
}
