package com.Geko.pruebas.Service.ServiceImpl;

import com.Geko.pruebas.Dao.EmpresaDao;
import com.Geko.pruebas.Dto.EmpresaDto;
import com.Geko.pruebas.Mapper.GenericModelMapper;
import com.Geko.pruebas.Models.Empresa;
import com.Geko.pruebas.Service.EmpresaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Date;
import java.util.List;

@Component
public class EmpresaServiceImple implements EmpresaService {

    @PersistenceContext
    private EntityManager em;

    @Autowired
    private EmpresaDao getDao;

    private GenericModelMapper mapper = GenericModelMapper.singleModelMapper();
    @Override
    public EmpresaDto CreateEmpresa(EmpresaDto empresaDto) {
        if (empresaDto != null) {
            empresaDto.setFecha(new Date());
            Empresa empresa = mapper.Mapper(empresaDto,Empresa.class);
            return mapper.Mapper(getDao.save(empresa),EmpresaDto.class);

        }
        return null;
    }

    @Override
    public EmpresaDto getEmpresaById(long idEmpresa) {
        if (idEmpresa != 0) {
            Empresa empresa = getDao.getEmpresaById(idEmpresa);
            if (empresa != null) {
                return mapper.Mapper(empresa, EmpresaDto.class);
            }
        }
        return null;
    }
    @Override
    public EmpresaDto getEmpresaByNit(String NIT) {
        if (NIT.length() >= 0) {
            Empresa empresa = getDao.getEmpresaByNit(NIT);
            if (empresa != null) {
                return mapper.Mapper(empresa, EmpresaDto.class);
            }
        }
        return null;
    }
    @Override
    public EmpresaDto UpdateEmpresa(EmpresaDto empresaDto) {

        Empresa empresa1 = getDao.getEmpresaById(empresaDto.getId());
        if (empresa1 != null) {
            empresa1.setNit(empresaDto.getNit());
            empresa1.setNombre(empresaDto.getNombre());
            empresa1.setAddress(empresaDto.getAddress());
            empresa1.setTelefono(empresaDto.getTelefono());

            return mapper.Mapper(getDao.save(empresa1), EmpresaDto.class);
        }

        return null;
    }


    @SuppressWarnings("unchecked")
    @Override
    public List<EmpresaDto> ListaEmpresa() {
        return mapper.ListMapper(getDao.findAll(), EmpresaDto.class);
    }

    @Override
    public Boolean DeleteEmpresa(Long id) {
        if (id != 0) {
            try{
                getDao.deleteById(id);
                return true;
            }catch (Exception e){
                return null;
            }

        }
        return false;
    }

}
