package com.Geko.pruebas.Dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;


@Getter @Setter @AllArgsConstructor
public class ResponseMessage {

	private  Date fechaError;
	private String mensaje;
	private String detalle;
}
