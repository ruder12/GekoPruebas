package com.Geko.pruebas.Service;

import com.Geko.pruebas.Dto.InformeHistorial;
import com.Geko.pruebas.Dto.InventarioDto;
import net.sf.jasperreports.engine.JRException;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@Service
public interface InventarioService {
    InventarioDto CreateInventario(InventarioDto inventarioDto);

    List<InventarioDto> getAllInventario();

    InventarioDto getInventarioById(long idInventario);

    InformeHistorial GenerarInforme() throws SQLException, JRException, IOException;
}
