package com.Geko.pruebas.Service.ServiceImpl;



import com.Geko.pruebas.Assets.Utils;
import com.Geko.pruebas.Dao.UsuarioDao;
import com.Geko.pruebas.Dto.UsuarioDto;
import com.Geko.pruebas.Mapper.GenericModelMapper;
import com.Geko.pruebas.Models.Usuario;
import com.Geko.pruebas.Service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;



@Component
public class usuarioServiceImpl implements UsuarioService {

	@Autowired
	private UsuarioDao getDao;
	
	@PersistenceContext
	private EntityManager em;

	private GenericModelMapper mapper = GenericModelMapper.singleModelMapper();


	@Override
	public UsuarioDto getUsuario(Long idusuario) {
		if (idusuario != null){
			Usuario user = getDao.findById(idusuario).orElse(null);
			if (user != null){
				return mapper.Mapper(user, UsuarioDto.class);
			}
		}
		return null;
	}



	@Override
	public UsuarioDto ValidarToken(String token) {
		if(token != null) {
			Usuario user = getDao.findByToken(token);
			if(user != null) {
				
				return mapper.Mapper(user, UsuarioDto.class);
			}
		}
		return null;
	}

	@Override
	public UsuarioDto ActualizarToken(String email, String token) {
		if(email != null) {
			Usuario user = getDao.findByEmail(email);
			if(user != null) {
				user.setToken(token);
				Usuario userBD = getDao.saveAndFlush(user);
				return mapper.Mapper(userBD,UsuarioDto.class);
			}
		}
		return null;
	}

	


	
}
