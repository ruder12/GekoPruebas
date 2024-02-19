package com.Geko.pruebas.Service;

import com.Geko.pruebas.Dto.ProductoDto;
import com.Geko.pruebas.Models.Producto;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigInteger;
import java.util.List;

@Service
public interface ProductoService {

	
	ProductoDto CreateProducto(ProductoDto producto);
	
	ProductoDto UpdateProducto(ProductoDto producto);
	
	ProductoDto getProductoById(long idproducto);

	@SuppressWarnings("unchecked")
	@Transactional
	String GeneradorCodigo();

	List<ProductoDto> ListaProductos();

    List<ProductoDto> ListaProductos(String moneda);

    Boolean DeleteProducto(Long id);
	
	
	
}
