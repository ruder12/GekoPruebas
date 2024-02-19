package com.Geko.pruebas.Security;

import com.Geko.pruebas.Dao.UsuarioDao;
import com.Geko.pruebas.Models.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class UserDetailSerivices implements UserDetailsService {

	@Autowired
	private UsuarioDao getDao;

	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Usuario user = getDao.findByEmail(username);

		if (user == null ) {
			throw new UsernameNotFoundException("No existe usuario o Esta Inactivo " + username);
		}

		GrantedAuthority grantedAuthority = new SimpleGrantedAuthority(user.getRol());
		List<GrantedAuthority> roles = new ArrayList<GrantedAuthority>();
		roles.add(grantedAuthority);
		UserDetails userDet = new User(user.getEmail(), user.getPassword(), roles);
		return userDet;
	}

}
