package com.Geko.pruebas.Controller;



import com.Geko.pruebas.Dto.UsuarioDto;
import com.Geko.pruebas.Models.Login;
import com.Geko.pruebas.Security.JwtAuthResponseDto;
import com.Geko.pruebas.Security.JwtTokenProvider;
import com.Geko.pruebas.Service.ServiceImpl.usuarioServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

/**
 * Controlador que gestiona las operaciones de autenticación de usuarios.
 */
@RestController
@RequestMapping(value = "/api/auth")
public class AuthController {

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private JwtTokenProvider jwtTokenProvider;

	@Autowired
	private usuarioServiceImpl serviceUsuario;

	/**
	 * Endpoint para autenticar a un usuario y generar un token JWT.
	 * @param login Los datos de inicio de sesión del usuario.
	 * @return Una respuesta HTTP con el token JWT generado.
	 */
	@PostMapping("/login")
	public ResponseEntity<JwtAuthResponseDto> authenticateUser(@RequestBody Login login) {
		Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
				login.getUsername(), login.getPassword()));
		SecurityContextHolder.getContext().setAuthentication(authentication);
		UsuarioDto user = jwtTokenProvider.generateToken(authentication);
		if(user != null){
			return ResponseEntity.ok(new JwtAuthResponseDto(user));
		}
		return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
	}

	/**
	 * Endpoint para cerrar la sesión de un usuario.
	 * @param email El correo electrónico del usuario cuya sesión se va a cerrar.
	 * @return Una respuesta HTTP indicando si la sesión se cerró correctamente.
	 */
	@PostMapping("/logout")
	public ResponseEntity<String> destroySession(@RequestParam("email") String email) {
		if(serviceUsuario.ActualizarToken(email, "") != null) {
			return ResponseEntity.ok("Sesión Finalizada");
		}
		return ResponseEntity.notFound().build();
	}

	/**
	 * Endpoint para restablecer la contraseña de un usuario.
	 * @return Una redirección a la página principal ("/").
	 */
	@PostMapping("/ResetPass")
	public String resetPass() {
		// Este método podría implementar la lógica para restablecer la contraseña del usuario.
		// En este caso, simplemente devuelve una redirección a la página principal.
		return "redirect:/";
	}
}