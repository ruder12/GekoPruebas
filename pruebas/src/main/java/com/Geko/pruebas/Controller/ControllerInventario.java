package com.Geko.pruebas.Controller;

import com.Geko.pruebas.Dto.InformeHistorial;
import com.Geko.pruebas.Dto.InventarioDto;
import com.Geko.pruebas.Service.ServiceImpl.InventarioServiceImple;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Calendar;
import java.util.List;

/**
 * Controlador que gestiona las operaciones relacionadas con el inventario.
 */
@RestController
@RequestMapping(value = "/api/inventory")
public class ControllerInventario {

    @Autowired
    private InventarioServiceImple Serviceinventario;

    /**
     * Endpoint para obtener una lista de todos los elementos del inventario.
     * @return Una respuesta HTTP con la lista de elementos del inventario.
     */
    @GetMapping(value = "")
    public ResponseEntity<List<InventarioDto>> ListaInventario() {
        List<InventarioDto> Lista = Serviceinventario.getAllInventario();
        if (Lista != null) {
            return ResponseEntity.ok(Lista);
        }
        return ResponseEntity.notFound().build();
    }

    /**
     * Endpoint para obtener un elemento específico del inventario por su ID.
     * @param id El ID del elemento del inventario que se desea obtener.
     * @return Una respuesta HTTP con el elemento del inventario correspondiente al ID proporcionado.
     */
    @GetMapping(value = "/{id}")
    public ResponseEntity<InventarioDto> getInventario(@PathVariable("id") Long id) {
        if (id != null && id != 0) {
            InventarioDto list = Serviceinventario.getInventarioById(id);
            if (list != null) {
                return ResponseEntity.ok(list);
            } else {
                return ResponseEntity.noContent().build();
            }
        }
        return ResponseEntity.notFound().build();
    }
    @GetMapping(path = {"/generarInforme"})
    public ResponseEntity<Resource> InformeHistorial() {
            try {
                InformeHistorial reporte = this.Serviceinventario.GenerarInforme();
                InputStream inputStream = new ByteArrayInputStream(reporte.getStream());
                InputStreamResource streamResource = new InputStreamResource(inputStream);
                MediaType mediaType = MediaType.APPLICATION_PDF;
                return ResponseEntity.ok().header("Content-Disposition", new String[]{"inline; filename=" + reporte.getFileName()}).contentLength(reporte.getLength()).contentType(mediaType).body(streamResource);

            } catch (Exception e) {
                return ResponseEntity.notFound().build();
            }
    }
}
