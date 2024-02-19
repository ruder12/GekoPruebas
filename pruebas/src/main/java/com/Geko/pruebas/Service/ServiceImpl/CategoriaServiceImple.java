package com.Geko.pruebas.Service.ServiceImpl;


import com.Geko.pruebas.Dao.CategoriaDao;
import com.Geko.pruebas.Models.Categoria;
import com.Geko.pruebas.Service.CategoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class CategoriaServiceImple implements CategoriaService {

	@Autowired
	private CategoriaDao getDao;



	@Override
	public Categoria getCategoria(Long id) {
		if (id != null && id != 0) {
			return getDao.findById(id).get();
		}
		return null;
	}


}
