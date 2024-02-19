package com.Geko.pruebas.InventarioTest;

import com.Geko.pruebas.Dao.InventarioDao;
import com.Geko.pruebas.Dto.InventarioDto;
import com.Geko.pruebas.Dto.ProductoDto;
import com.Geko.pruebas.Mapper.GenericModelMapper;
import com.Geko.pruebas.Models.Inventario;
import com.Geko.pruebas.Models.Producto;
import com.Geko.pruebas.Service.ServiceImpl.InventarioServiceImple;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

@RunWith(MockitoJUnitRunner.class)
public class InventarioServiceImplTest {

    @Mock
    private InventarioDao inventarioDaoMock;

    @InjectMocks
    private InventarioServiceImple inventarioService;

    private GenericModelMapper mapper = GenericModelMapper.singleModelMapper();
    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testCreateInventario_WithValidInventario_ReturnsInventarioDto() {
        // Arrange
        Producto producto = new Producto();
        producto.setId(1L);
        InventarioDto inventarioDto = new InventarioDto();
        inventarioDto.setProducto(mapper.Mapper(producto, ProductoDto.class));
        inventarioDto.setCantidad(10);


        Inventario inventario = new Inventario();
        inventario.setId(1L);
        inventario.setProducto(producto);
        inventario.setCantidad(10);

        Mockito.when(inventarioDaoMock.save(Mockito.any(Inventario.class))).thenReturn(inventario);

        // Act
        InventarioDto createdInventarioDto = inventarioService.CreateInventario(inventarioDto);

        // Assert
        Assertions.assertEquals(inventario.getId(), createdInventarioDto.getId());
        Assertions.assertEquals(inventario.getProducto().getId(), createdInventarioDto.getProducto().getId());
        Assertions.assertEquals(inventario.getCantidad(), createdInventarioDto.getCantidad());
    }

    @Test
    public void testGetAllInventario_ReturnsListOfInventarioDto() {
        // Arrange
        List<Inventario> inventarios = new ArrayList<>();
        Producto producto = new Producto();
        producto.setId(1L);
        inventarios.add(new Inventario(1L, producto, "ENTRANCE", 10));
        inventarios.add(new Inventario(2L, producto, "EXIT", 20));

        Mockito.when(inventarioDaoMock.findAll()).thenReturn(inventarios);

        // Act
        List<InventarioDto> inventarioDtos = inventarioService.getAllInventario();

        // Assert
        Assertions.assertEquals(inventarios.size(), inventarioDtos.size());
        // Add more assertions if needed
    }

    @Test
    public void testGetInventarioById_WithValidId_ReturnsInventarioDto() {
        // Arrange
        long idInventario = 1L;
        Inventario inventario = new Inventario();
        Producto producto = new Producto();
        producto.setId(1l);
        inventario.setId(1L);
        inventario.setProducto(producto);
        inventario.setCantidad(10);

        Mockito.when(inventarioDaoMock.getReferenceById(idInventario)).thenReturn(inventario);

        // Act
        InventarioDto inventarioDto = inventarioService.getInventarioById(idInventario);

        // Assert
        Assertions.assertEquals(inventario.getId(), inventarioDto.getId());
        Assertions.assertEquals(inventario.getProducto().getId(), inventarioDto.getProducto().getId());
        Assertions.assertEquals(inventario.getCantidad(), inventarioDto.getCantidad());
    }
}