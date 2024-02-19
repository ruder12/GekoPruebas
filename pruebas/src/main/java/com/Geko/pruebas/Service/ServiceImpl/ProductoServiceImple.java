package com.Geko.pruebas.Service.ServiceImpl;

import com.Geko.pruebas.Assets.Utils;
import com.Geko.pruebas.Dao.ProductoDao;
import com.Geko.pruebas.Dto.ProductoDto;
import com.Geko.pruebas.Mapper.GenericModelMapper;
import com.Geko.pruebas.Models.Empresa;
import com.Geko.pruebas.Models.Producto;
import com.Geko.pruebas.Service.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Component
public class ProductoServiceImple implements ProductoService {

	@PersistenceContext
	private EntityManager em;

	@Autowired
	private ProductoDao getDao;
	
	private GenericModelMapper mapper = GenericModelMapper.singleModelMapper();
	@Override
	public ProductoDto CreateProducto(ProductoDto producto) {
		if (producto != null) {
			String code = GeneradorCodigo();
			producto.setCodigo(code);
			producto.setFechaRegistro(new Date());
			Producto product = mapper.Mapper(producto, Producto.class);

			return mapper.Mapper(getDao.save(product),ProductoDto.class);

		}
		return null;
	}

	@Override
	public ProductoDto getProductoById(long idproducto) {
		if (idproducto != 0) {
			Producto produ = getDao.getProductoById(idproducto);
			if (produ != null) {
				return mapper.Mapper(produ, ProductoDto.class);
			}
		}
		return null;
	}

	@Override
	public ProductoDto UpdateProducto(ProductoDto producto) {

		Producto product = getDao.getProductoById(producto.getId());
			if (product != null) {

				product.setNombre(producto.getNombre());
				product.setCaracteristicas(producto.getCaracteristicas());
				product.setPrecio(producto.getPrecio());
				product.setEmpresa(mapper.Mapper(producto.getEmpresa(), Empresa.class));

				return mapper.Mapper(getDao.save(product), ProductoDto.class);
			}

		return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	@Transactional
	public String GeneradorCodigo() {
		
		StringBuffer sql = new StringBuffer();
		String codigo = "";
		int j;
		String c = "";
		sql.append("SELECT MAX(code) FROM product;");
		List<String> code = em.createNativeQuery(sql.toString()).getResultList();

		if (code.size() > 0) {
			c = code.get(0);
		}
		if (c == null) {
			codigo = "CO0001";
		} else {
			char r1 = c.charAt(2);
			char r2 = c.charAt(3);
			char r3 = c.charAt(4);
			char r4 = c.charAt(5);
			String r = "";
			r = "" + r1 + r2 + r3 + r4;
			j = Integer.parseInt(r);
			int CodeMax = j + 1;
			if (j < 10) {
				codigo = "CO000" + CodeMax;
			} else if (j >= 10 && j < 100) {
				codigo = "CO00" + CodeMax;
			} else if (j >= 100 && j < 1000) {
				codigo = "CO0" + CodeMax;
			} else if (j >= 1000) {
				codigo = "CO" + CodeMax;
			}

		}

		return codigo;
	}


	@SuppressWarnings("unchecked")
	@Override
	public List<ProductoDto> ListaProductos() {
		return mapper.ListMapper(getDao.findAll(), ProductoDto.class);
	}
	@Override
	public List<ProductoDto> ListaProductos(String moneda) {
		List<Producto> productos = getDao.findAll();
		List<ProductoDto> productosConvertidos = new ArrayList<>();

		for (Producto producto : productos) {
			ProductoDto productoDto = mapper.map(producto, ProductoDto.class);

			// Aquí tendrías lógica para convertir el valor del producto a la moneda seleccionada
			double valorConvertido = convertirValor(producto.getPrecio(), Utils.Moneda, moneda);
			productoDto.setPrecio(valorConvertido);

			productosConvertidos.add(productoDto);
		}

		return productosConvertidos;
	}

	private double convertirValor(double valor, String monedaOrigen, String monedaDestino) {
		// Definir tasas de cambio predefinidas
		double tasaCambioUSD_AUD = 1.35; // Ejemplo: 1 USD = 1.35 AUD
		double tasaCambioUSD_ARS = 98.45; // Ejemplo: 1 USD = 98.45 ARS
		double tasaCambioUSD_JPY = 110.32; // Ejemplo: 1 USD = 110.32 JPY
		double tasaCambioUSD_COP = 3772.50; // Ejemplo: 1 USD = 3772.50 COP

		// Convertir el valor de la moneda de origen a US

		// Convertir el valor de USD a la moneda de destino
		double valorDestino;
		switch (monedaDestino) {
			case "AUD":
				valorDestino = valor * tasaCambioUSD_AUD;
				break;
			case "ARS":
				valorDestino = valor * tasaCambioUSD_ARS;
				break;
			case "JPY":
				valorDestino = valor * tasaCambioUSD_JPY;
				break;
			case "COP":
				valorDestino = valor * tasaCambioUSD_COP;
				break;
			default:
				// Si la moneda de destino no es reconocida, devuelve el valor en USD
				return valor;
		}

		return valorDestino;
	}
	@Override
	public Boolean DeleteProducto(Long id) {
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
