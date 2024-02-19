package com.Geko.pruebas.Assets;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

public class passwordEncoder {

	public static void encoder() {
		PasswordEncoder  pass = new BCryptPasswordEncoder();
		System.out.println(pass.encode("invitado11"));
		//passInvitado = invitado11
		//passAdmin = geko12

	}
	
}
