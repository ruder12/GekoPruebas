package com.Geko.pruebas.Security;

import com.Geko.pruebas.Dto.UsuarioDto;
import com.Geko.pruebas.Exceptions.GekoAppException;
import com.Geko.pruebas.Service.ServiceImpl.usuarioServiceImpl;
import io.jsonwebtoken.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.util.Date;
/**
 * Clase encargada de proveer funcionalidades relacionadas con la generación, validación y obtención de tokens JWT.
 */
@Component
public class JwtTokenProvider {

	@Value("${app.jwt-secret}")
	private String jwtSecret;

	@Value("${app.jwt-expiration-milliseconds}")
	private int jwtExpirationMs;

	@Autowired
	private usuarioServiceImpl serviceUsuario;

	/**
	 * Genera un token JWT basado en la autenticación proporcionada.
	 * @param authentication La autenticación del usuario.
	 * @return Un objeto UsuarioDto que contiene el token JWT generado.
	 */
	public UsuarioDto generateToken(Authentication authentication) {
		String username = authentication.getName();
		Date currentDate = new Date();
		Date expirationDate = new Date(currentDate.getTime() + jwtExpirationMs);
		String token = Jwts.builder()
				.setSubject(username)
				.setIssuedAt(new Date())
				.setExpiration(expirationDate)
				.signWith(SignatureAlgorithm.HS512, jwtSecret)
				.compact();
		UsuarioDto usuarioDto = serviceUsuario.ActualizarToken(username, token);
		if (usuarioDto != null) {
			return usuarioDto;
		}
		return null;
	}

	/**
	 * Obtiene el nombre de usuario (subject) del token JWT.
	 * @param token El token JWT.
	 * @return El nombre de usuario (subject) del token JWT.
	 */
	public String getUsernameFromJwt(String token) {
		Claims claims = Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody();
		return claims.getSubject();
	}

	/**
	 * Valida la autenticidad y vigencia de un token JWT.
	 * @param token El token JWT a validar.
	 * @return true si el token es válido, false si no lo es.
	 * @throws GekoAppException Si el token es inválido por algún motivo.
	 */
	public boolean validateToken(String token) {
		try {
			Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token);
			return true;
		} catch (SignatureException | MalformedJwtException e) {
			throw new GekoAppException(HttpStatus.BAD_REQUEST, "Firma jwt no válida");
		} catch (ExpiredJwtException e) {
			throw new GekoAppException(HttpStatus.BAD_REQUEST, "El token jwt ha caducado");
		} catch (UnsupportedJwtException e) {
			throw new GekoAppException(HttpStatus.BAD_REQUEST, "Token jwt no compatible");
		} catch (IllegalArgumentException e) {
			throw new GekoAppException(HttpStatus.BAD_REQUEST, "La cadena jwt está vacía");
		}
	}
}
