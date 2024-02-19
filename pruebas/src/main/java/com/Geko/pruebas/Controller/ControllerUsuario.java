package com.Geko.pruebas.Controller;

import com.Geko.pruebas.Dto.UsuarioDto;
import com.Geko.pruebas.Models.Usuario;
import com.Geko.pruebas.Service.ServiceImpl.usuarioServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;



/**
 * Controlador que gestiona las operaciones relacionadas con los usuarios.
 */
@RestController
@RequestMapping(value = "/api/users")
public class ControllerUsuario {

	@Autowired
	private usuarioServiceImpl serviceUsuario;

	/**
	 * Endpoint para obtener un usuario por su ID.
	 * @param id El ID del usuario que se desea obtener.
	 * @return Una respuesta HTTP con el usuario correspondiente al ID proporcionado.
	 */
	@GetMapping(value = "/{id}")
	public ResponseEntity<UsuarioDto> getUsuario(@PathVariable("id") Long id){
		if(id != null) {
			UsuarioDto user = serviceUsuario.getUsuario(id);
			if(user != null) {
				return ResponseEntity.ok(user);
			}
		}
		return ResponseEntity.noContent().build();
	}

	/**
	 * Endpoint para actualizar el token de un usuario.
	 * @param email El correo electrónico del usuario cuyo token se va a actualizar.
	 * @param token El nuevo token.
	 * @return Una respuesta HTTP indicando si el token se actualizó correctamente.
	 */
	@PostMapping(value = "/UpdateToken")
	public ResponseEntity<String> UpdateToken(@RequestParam("email") String email,@RequestParam("token") String token){
		UsuarioDto update = serviceUsuario.ActualizarToken(email, token);
		if(update != null) {
			return ResponseEntity.status(HttpStatus.CREATED).body(update.getToken());
		}
		return ResponseEntity.notFound().build();
	}

	/**
	 * Endpoint para validar un token de usuario.
	 * @param token El token que se desea validar.
	 * @return Una respuesta HTTP con el usuario correspondiente al token proporcionado.
	 */
	@GetMapping(value = "/validarToken")
	public ResponseEntity<UsuarioDto> ValidarToken(@RequestParam("token") String token){
		if(token != null) {
			UsuarioDto user = serviceUsuario.ValidarToken(token);
			if(user != null) {
				return ResponseEntity.ok(user);
			}
		}
		return ResponseEntity.noContent().build();
	}
}

