package com.Geko.pruebas.Exceptions;


import com.Geko.pruebas.Dto.ResponseMessage;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.util.Date;

@ControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<ResponseMessage> ManejarResourceNotFoundException(ResourceNotFoundException Excepcion,
																			WebRequest webrequest){
		ResponseMessage errordetalles = new ResponseMessage(new Date(),Excepcion.getMessage(),webrequest.getDescription(false));
		return new ResponseEntity<>(errordetalles,HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(GekoAppException.class)
	public ResponseEntity<ResponseMessage> ManejarGekoException(GekoAppException Excepcion,
			WebRequest webrequest){
		ResponseMessage errordetalles = new ResponseMessage(new Date(),Excepcion.getMessage(),webrequest.getDescription(false));
		return new ResponseEntity<>(errordetalles,HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<ResponseMessage> ManejarglobalException(Exception Excepcion,
			WebRequest webrequest){
		ResponseMessage errordetalles = new ResponseMessage(new Date(),Excepcion.getMessage(),webrequest.getDescription(false));
		return new ResponseEntity<>(errordetalles,HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
