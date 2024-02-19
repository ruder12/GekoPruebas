package com.Geko.pruebas.Dao;

import com.Geko.pruebas.Models.Empresa;
import com.Geko.pruebas.Models.Inventario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InventarioDao  extends JpaRepository<Inventario, Long> {
}
