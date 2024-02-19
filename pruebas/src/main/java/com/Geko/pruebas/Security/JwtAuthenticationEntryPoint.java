package com.Geko.pruebas.Security;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collections;

/**
 * Clase encargada de manejar la entrada no autorizada en la autenticación JWT.
 */
@Component
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint {

	/**
	 * Método para manejar la entrada no autorizada en la autenticación JWT.
	 * @param request La solicitud HTTP que desencadenó el punto de entrada.
	 * @param response La respuesta HTTP que se enviará al cliente.
	 * @param authException La excepción de autenticación que se produjo.
	 * @throws IOException Si ocurre un error de entrada/salida al escribir en el cuerpo de la respuesta.
	 * @throws ServletException Si se produce un error durante el proceso de inicio de sesión.
	 */
	@Override
	public void commence(HttpServletRequest request, HttpServletResponse response,
						 AuthenticationException authException) throws IOException, ServletException {

		// Configura la respuesta con un estado HTTP de no autorizado
		response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
		// Establece el tipo de contenido de la respuesta como JSON
		response.setContentType(MediaType.APPLICATION_JSON_VALUE);

		// Construye el mensaje de error
		String message;
		if (authException.getCause() != null) {
			message = authException.getCause().toString() + " " + authException.getMessage();
		} else {
			message = authException.getMessage();
		}

		// Convierte el mensaje de error en bytes JSON
		byte[] body = new ObjectMapper().writeValueAsBytes(Collections.singletonMap("error", message));

		// Escribe el cuerpo de la respuesta en el flujo de salida de la respuesta HTTP
		response.getOutputStream().write(body);
	}
}
