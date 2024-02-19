package com.Geko.pruebas.Controller;

import com.Geko.pruebas.Dto.ProductoDto;
import com.Geko.pruebas.Models.Producto;
import com.Geko.pruebas.Service.ServiceImpl.ProductoServiceImple;
import com.Geko.pruebas.Service.ServiceImpl.usuarioServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.List;

/**
 * Controlador que gestiona las operaciones relacionadas con los productos.
 */
@RestController
@RequestMapping(value = "/api/products")
public class ControllerProducto {

	@Autowired
	private ProductoServiceImple ServiceProducto;

	/**
	 * Endpoint para obtener una lista de todos los productos.
	 * @param moneda
	 * @return
	 */
	@GetMapping(value = "")
	public ResponseEntity<List<ProductoDto>> ListaProductos(@RequestParam(value = "moneda",required = false) String moneda) {
		List<ProductoDto> Listaproductos = ServiceProducto.ListaProductos();
		if (moneda != null && moneda.length() > 0){
			Listaproductos = ServiceProducto.ListaProductos(moneda);
		}
		if (Listaproductos != null) {
			return ResponseEntity.ok(Listaproductos);
		}
		return ResponseEntity.notFound().build();
	}

	/**
	 * Endpoint para obtener un producto específico por su ID.
	 * @param id El ID del producto que se desea obtener.
	 * @return Una respuesta HTTP con el producto correspondiente al ID proporcionado.
	 */
	@GetMapping(value = "/{id}")
	public ResponseEntity<ProductoDto> getProducto(@PathVariable("id") Long id) {
		if (id != null && id != 0) {
			ProductoDto Listaproductos = ServiceProducto.getProductoById(id);
			if (Listaproductos != null) {
				return ResponseEntity.ok(Listaproductos);
			} else {
				return ResponseEntity.noContent().build();
			}
		}
		return ResponseEntity.notFound().build();
	}

	/**
	 * Endpoint para crear un nuevo producto.
	 * @param producto Los datos del producto que se desea crear.
	 * @param result El resultado de la validación de los datos del producto.
	 * @return Una respuesta HTTP con el producto creado.
	 */
	@PostMapping(value = "")
	public ResponseEntity<ProductoDto> CreateProducto(@Valid @RequestBody ProductoDto producto, BindingResult result) {
		if (result.hasErrors()) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, result.getFieldError().getDefaultMessage());
		}

		if (producto != null) {
			ProductoDto produ = ServiceProducto.CreateProducto(producto);
			if (produ != null) {
				return ResponseEntity.status(HttpStatus.OK).body(produ);
			} else {
				return ResponseEntity.notFound().build();
			}
		}
		return ResponseEntity.noContent().build();
	}

	/**
	 * Endpoint para actualizar un producto existente.
	 * @param producto Los nuevos datos del producto.
	 * @return Una respuesta HTTP con el producto actualizado.
	 */
	@PutMapping(value = "/{id}")
	public ResponseEntity<ProductoDto> UpdateProducto(@RequestBody ProductoDto producto) {
		if (producto != null) {
			ProductoDto produ = ServiceProducto.UpdateProducto(producto);
			if (produ != null) {
				return ResponseEntity.status(HttpStatus.OK).body(produ);
			}
		}
		return ResponseEntity.notFound().build();
	}

	/**
	 * Endpoint para eliminar un producto existente por su ID.
	 * @param id El ID del producto que se desea eliminar.
	 * @return Una respuesta HTTP indicando si el producto fue eliminado correctamente.
	 */
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Boolean> DeleteProducto(@PathVariable("id") Long id) {
		if (id != 0) {
			Boolean produ = ServiceProducto.DeleteProducto(id);
			if (produ != null) {
				return ResponseEntity.ok(produ);
			}
		}
		return ResponseEntity.notFound().build();
	}
}