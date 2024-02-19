package com.Geko.pruebas.Service;

import com.Geko.pruebas.Dto.UsuarioDto;
import org.springframework.stereotype.Service;



/**
 * Servicio de usuario que define operaciones relacionadas con usuarios.
 */
@Service
public interface UsuarioService  {

	/**
	 * Obtiene los datos de un usuario por su ID.
	 * @param idusuario El ID del usuario que se desea obtener.
	 * @return Un objeto UsuarioDto que contiene los datos del usuario.
	 */
	UsuarioDto getUsuario(Long idusuario);

	/**
	 * Valida un token de usuario.
	 * @param token El token que se desea validar.
	 * @return Un objeto UsuarioDto que contiene los datos del usuario asociado al token.
	 */
	UsuarioDto ValidarToken(String token);

	/**
	 * Actualiza el token de un usuario.
	 * @param email El correo electrónico del usuario cuyo token se va a actualizar.
	 * @param token El nuevo token.
	 * @return Un String que indica si el token se actualizó correctamente.
	 */
	UsuarioDto ActualizarToken(String email,String token);
}
