package com.Geko.pruebas.Dao;


import com.Geko.pruebas.Models.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.math.BigInteger;
import java.util.Optional;


public interface ProductoDao extends JpaRepository<Producto, Long> {
	
	Producto getProductoById(Long id);

    

}
