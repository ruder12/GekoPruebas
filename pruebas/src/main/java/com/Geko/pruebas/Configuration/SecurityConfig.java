package com.Geko.pruebas.Configuration;



import com.Geko.pruebas.Assets.Utils;
import com.Geko.pruebas.Security.JwtAuthenticationEntryPoint;
import com.Geko.pruebas.Security.JwtAuthenticationFilter;
import com.Geko.pruebas.Security.UserDetailSerivices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import java.util.Arrays;


/**
 * Configuración de seguridad para la aplicación.
 */
@SuppressWarnings("deprecation")
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private UserDetailSerivices userservice;

	@Autowired
	private JwtAuthenticationEntryPoint jwtAntenticationEntryPoint;

	/**
	 * Configura el filtro de autenticación JWT.
	 * @return El filtro de autenticación JWT.
	 */
	@Bean
	public JwtAuthenticationFilter jwtAutenticationFilter() {
		return new JwtAuthenticationFilter();
	}

	/**
	 * Configura el codificador de contraseñas.
	 * @return El codificador de contraseñas BCrypt.
	 */
	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		BCryptPasswordEncoder bycryp = new BCryptPasswordEncoder();
		return bycryp;
	}

	/**
	 * Configura el gestor de autenticación para utilizar el servicio de detalles de usuario y el codificador de contraseñas.
	 */
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userservice).passwordEncoder(passwordEncoder());
	}

	/**
	 * Configura la seguridad HTTP.
	 */
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
				.cors()
				.and()
				.csrf()
				.disable()
				.exceptionHandling().authenticationEntryPoint(jwtAntenticationEntryPoint)
				.and()
				.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
				.and()
				.authorizeRequests()
				.antMatchers(Utils.AUTH_WHITELIST).permitAll()
				.antMatchers("/api/auth/**").permitAll()
				.antMatchers("/api/admin/**").hasRole("ADMIN")
				.anyRequest().authenticated();

		http.addFilterBefore(jwtAutenticationFilter(), UsernamePasswordAuthenticationFilter.class);
	}

	/**
	 * Configura la fuente de configuración CORS.
	 */
	@Bean
	CorsConfigurationSource corsConfigurationSource() {
		CorsConfiguration cc = new CorsConfiguration();
		cc.setAllowedHeaders(Arrays.asList("Origin,Accept", "X-Requested-With", "Content-Type",
				"Access-Control-Request-Method", "Access-Control-Request-Headers", "Authorization"));
		cc.setExposedHeaders(Arrays.asList("Access-Control-Allow-Origin", "Access-Control-Allow-Credentials"));
		cc.setAllowedOrigins(Arrays.asList("/api/**"));
		cc.setAllowedMethods(Arrays.asList("GET", "POST","DELETE","PUT"));
		cc.addAllowedOriginPattern("*");
		cc.setAllowCredentials(Boolean.TRUE);
		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		source.registerCorsConfiguration("/**", cc);
		return source;
	}

	/**
	 * Configura el gestor de autenticación.
	 */
	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}
}
