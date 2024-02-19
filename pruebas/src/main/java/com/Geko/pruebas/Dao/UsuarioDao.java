package com.Geko.pruebas.Dao;



import com.Geko.pruebas.Models.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioDao extends JpaRepository<Usuario, Long> {

	Usuario findByEmail(String email);
	Usuario findByToken(String token);
}
