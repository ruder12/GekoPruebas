package com.Geko.pruebas.Dao;

import com.Geko.pruebas.Models.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CategoriaDao extends JpaRepository<Categoria, Long> {


}
