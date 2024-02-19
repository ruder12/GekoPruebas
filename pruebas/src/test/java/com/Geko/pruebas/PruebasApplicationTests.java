package com.Geko.pruebas;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class) // Habilita el soporte de Spring en JUnit 5
@SpringBootTest // Carga el contexto de la aplicación Spring para las pruebas
class PruebasApplicationTests {

	@Test
	void contextLoads() {
	}

}
