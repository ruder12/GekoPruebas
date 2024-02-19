package com.Geko.pruebas.Controller;

import com.Geko.pruebas.Dto.EmpresaDto;
import com.Geko.pruebas.Dto.ProductoDto;
import com.Geko.pruebas.Service.ServiceImpl.EmpresaServiceImple;
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
 * Controlador que gestiona las operaciones relacionadas con las empresas.
 */
@RestController
@RequestMapping(value = "/api/company")
public class ControllerEmpresa {

	@Autowired
	private EmpresaServiceImple ServiceEmpresa;

	/**
	 * Endpoint para obtener una lista de todas las empresas.
	 * @return Una respuesta HTTP con la lista de empresas.
	 */
	@GetMapping(value = "")
	public ResponseEntity<List<EmpresaDto>> ListaEmpresa() {
		List<EmpresaDto> ListaEmpresa = ServiceEmpresa.ListaEmpresa();
		if (ListaEmpresa != null) {
			return ResponseEntity.ok(ListaEmpresa);
		}
		return ResponseEntity.notFound().build();
	}

	/**
	 * Endpoint para obtener una empresa específica por su ID.
	 * @param id El ID de la empresa que se desea obtener.
	 * @return Una respuesta HTTP con la empresa correspondiente al ID proporcionado.
	 */
	@GetMapping(value = "getbynit/{nit}")
	public ResponseEntity<EmpresaDto> getEmpresa(@PathVariable("nit") String id) {
		if (id != null && id.length() != 0) {
			EmpresaDto ListEmpresa = ServiceEmpresa.getEmpresaByNit(id);
			if (ListEmpresa != null) {
				return ResponseEntity.ok(ListEmpresa);
			} else {
				return ResponseEntity.noContent().build();
			}
		}
		return ResponseEntity.notFound().build();
	}

	/**
	 * Endpoint para crear una nueva empresa.
	 * @param empresa Los datos de la empresa que se desea crear.
	 * @param result El resultado de la validación de los datos de la empresa.
	 * @return Una respuesta HTTP con la empresa creada.
	 */
	@PostMapping(value = "")
	public ResponseEntity<EmpresaDto> CreateEmpresa(@Valid @RequestBody EmpresaDto empresa, BindingResult result) {
		if (result.hasErrors()) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, result.getFieldError().getDefaultMessage());
		}
		if (empresa != null) {
			System.out.println(empresa.getNit());
			EmpresaDto produ = ServiceEmpresa.CreateEmpresa(empresa);
			if (produ != null) {
				return ResponseEntity.status(HttpStatus.CREATED).body(produ);
			} else {
				return ResponseEntity.notFound().build();
			}
		}
		return ResponseEntity.noContent().build();
	}

	/**
	 * Endpoint para actualizar una empresa existente.
	 * @param empresaDto Los nuevos datos de la empresa.
	 * @return Una respuesta HTTP con la empresa actualizada.
	 */
	@PutMapping(value = "/{id}")
	public ResponseEntity<EmpresaDto> UpdateEmpresa(@RequestBody EmpresaDto empresaDto) {
		if (empresaDto != null) {
			EmpresaDto empresa = ServiceEmpresa.UpdateEmpresa(empresaDto);
			if (empresa != null) {
				return ResponseEntity.status(HttpStatus.CREATED).body(empresa);
			}
		}
		return ResponseEntity.notFound().build();
	}

	/**
	 * Endpoint para eliminar una empresa existente por su ID.
	 * @param id El ID de la empresa que se desea eliminar.
	 * @return Una respuesta HTTP indicando si la empresa fue eliminada correctamente.
	 */
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Boolean> DeleteEmpresa(@PathVariable("id") Long id) {
		if (id != 0) {
			Boolean produ = ServiceEmpresa.DeleteEmpresa(id);
			if (produ != null) {
				return ResponseEntity.ok(produ);
			}
		}
		return ResponseEntity.notFound().build();
	}
}
