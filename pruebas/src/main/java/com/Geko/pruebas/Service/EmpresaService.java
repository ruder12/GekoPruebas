package com.Geko.pruebas.Service;

import com.Geko.pruebas.Dto.EmpresaDto;
import com.Geko.pruebas.Dto.ProductoDto;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public interface EmpresaService {
    EmpresaDto CreateEmpresa(EmpresaDto empresaDto);

    EmpresaDto getEmpresaByNit(String NIT);

    EmpresaDto UpdateEmpresa(EmpresaDto empresaDto);

    EmpresaDto getEmpresaById(long idproducto);


    List<EmpresaDto> ListaEmpresa();

    Boolean DeleteEmpresa(Long id);
}
