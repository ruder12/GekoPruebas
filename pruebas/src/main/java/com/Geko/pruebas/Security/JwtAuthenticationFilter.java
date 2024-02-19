package com.Geko.pruebas.Security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


/**
 * Filtro encargado de procesar y validar el token JWT en cada solicitud HTTP.
 */
@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

	@Autowired
	private JwtTokenProvider jwtTokenProvider;

	@Autowired
	private UserDetailsService userDetailService;

	/**
	 * Método para realizar el filtrado de cada solicitud HTTP y procesar el token JWT.
	 * @param request La solicitud HTTP entrante.
	 * @param response La respuesta HTTP saliente.
	 * @param filterChain El filtro de cadenas para continuar con el procesamiento de la solicitud.
	 * @throws ServletException Si se produce un error durante el procesamiento de la solicitud.
	 * @throws IOException Si se produce un error de entrada/salida durante el procesamiento de la solicitud.
	 */
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		String token = getJwtFromRequest(request);
			if(StringUtils.hasText(token) && jwtTokenProvider.validateToken(token)) {
				String username = jwtTokenProvider.getUsernameFromJwt(token);

				UserDetails userDetail = userDetailService.loadUserByUsername(username);
				UsernamePasswordAuthenticationToken authenticationToken =
						new UsernamePasswordAuthenticationToken(userDetail, null,userDetail.getAuthorities());
				authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

				SecurityContextHolder.getContext().setAuthentication(authenticationToken);
			}


		filterChain.doFilter(request, response);
	}


	/**
	 * Método para obtener el token JWT de la cabecera de autorización de la solicitud HTTP.
	 * @param request La solicitud HTTP entrante.
	 * @return El token JWT de la solicitud, o null si no se encuentra.
	 */
	private String getJwtFromRequest(HttpServletRequest request) {
		String bearerToken = request.getHeader("Authorization");
		if (StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer ")) {
			return bearerToken.substring(7);
		}
		return null;
	}
}
