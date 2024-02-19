package com.Geko.pruebas.Security;

import com.Geko.pruebas.Dto.UsuarioDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter @AllArgsConstructor
public class JwtAuthResponseDto {


	private UsuarioDto tokenAcceso;
	private String tipoToken="Bearer";
	public JwtAuthResponseDto(UsuarioDto tokenAcceso) {
		super();
		this.tokenAcceso = tokenAcceso;
	}

}
