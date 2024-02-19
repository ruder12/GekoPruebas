package com.Geko.pruebas.Dao;


import com.Geko.pruebas.Models.Empresa;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmpresaDao extends JpaRepository<Empresa, Long> {

    Empresa getEmpresaById(Long id);
    Empresa getEmpresaByNit(String id);
}
