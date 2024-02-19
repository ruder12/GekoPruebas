package com.Geko.pruebas.Service.ServiceImpl;

import com.Geko.pruebas.Configuration.JaspertReportManager;
import com.Geko.pruebas.Dao.InventarioDao;
import com.Geko.pruebas.Dto.InformeHistorial;
import com.Geko.pruebas.Dto.InventarioDto;
import com.Geko.pruebas.Mapper.GenericModelMapper;
import com.Geko.pruebas.Models.Inventario;
import com.Geko.pruebas.Service.InventarioService;
import net.sf.jasperreports.engine.JRException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.sql.DataSource;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

@Component
public class InventarioServiceImple implements InventarioService {

    @PersistenceContext
    private EntityManager em;

    @Autowired
    private DataSource dataSource;
    @Autowired
    private JaspertReportManager reportManager;

    @Autowired
    private InventarioDao getDao;

    private GenericModelMapper mapper = GenericModelMapper.singleModelMapper();
    @Override
    public InventarioDto CreateInventario(InventarioDto inventarioDto) {
        if (inventarioDto != null) {
            Inventario inventario = mapper.Mapper(inventarioDto, Inventario.class);

            return mapper.Mapper(getDao.save(inventario),InventarioDto.class);

        }
        return null;
    }
    @Override
    public List<InventarioDto> getAllInventario() {
       return mapper.ListMapper(getDao.findAll(), InventarioDto.class);

    }
    @Override
    public InventarioDto getInventarioById(long idInventario) {
        if (idInventario != 0) {
            Inventario inventario = getDao.getReferenceById(idInventario);
            if (inventario != null) {
                return mapper.Mapper(inventario, InventarioDto.class);
            }
        }
        return null;
    }

    @Override
    public InformeHistorial GenerarInforme() throws SQLException, JRException, IOException {
        InformeHistorial informeH = new InformeHistorial();
        HashMap<String, Object> params = new HashMap();
        ByteArrayOutputStream stream = this.reportManager.GenerarPDF("reporteinventario", params, this.dataSource.getConnection());
        informeH.setFileName("Informe Inventario.pdf");
        byte[] ds = stream.toByteArray();
        informeH.setLength(ds.length);
        informeH.setStream(ds);
        return informeH;
    }

}
