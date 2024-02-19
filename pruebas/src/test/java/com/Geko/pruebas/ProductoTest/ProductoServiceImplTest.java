package com.Geko.pruebas.ProductoTest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import com.Geko.pruebas.Dao.ProductoDao;
import com.Geko.pruebas.Dto.EmpresaDto;
import com.Geko.pruebas.Dto.ProductoDto;
import com.Geko.pruebas.Mapper.GenericModelMapper;
import com.Geko.pruebas.Models.Empresa;
import com.Geko.pruebas.Models.Producto;
import com.Geko.pruebas.Service.ServiceImpl.ProductoServiceImple;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mapstruct.Mapper;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import javax.persistence.EntityManager;
import javax.persistence.Query;

@RunWith(MockitoJUnitRunner.class)
public class ProductoServiceImplTest {

    @Mock
    private ProductoDao productoDaoMock;


    @Mock
    private EntityManager entityManagerMock;

    @InjectMocks
    private ProductoServiceImple productoService;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    private GenericModelMapper mapper = GenericModelMapper.singleModelMapper();
    @Test
    public void testCreateProducto_WithValidProducto_ReturnsProductoDto() {
        // Arrange
        Query mockQuery = Mockito.mock(Query.class);
        when(entityManagerMock.createNativeQuery("SELECT MAX(code) FROM product;")).thenReturn(mockQuery);
        when(mockQuery.getResultList()).thenReturn(Collections.singletonList("CO0001"));

        when(productoDaoMock.findAll()).thenReturn(new ArrayList<>());

        ProductoDto productoDto = new ProductoDto();
        productoDto.setNombre("Producto de prueba");
        productoDto.setPrecio(BigDecimal.TEN.doubleValue());

        Producto producto = new Producto();
        producto.setId(1L);
        producto.setNombre("Producto de prueba");
        producto.setPrecio(BigDecimal.TEN.doubleValue());

        when(productoDaoMock.save(Mockito.any(Producto.class))).thenReturn(producto);

        // Act
        ProductoDto createdProductoDto = productoService.CreateProducto(productoDto);

        // Assert
        assertEquals(producto.getId(), createdProductoDto.getId());
        assertEquals(producto.getNombre(), createdProductoDto.getNombre());
        assertEquals(producto.getPrecio(), createdProductoDto.getPrecio());
}
    @Test
    public void testGeneradorCodigo_ReturnsValidCodigo() {

        Query mockQuery = Mockito.mock(Query.class);
        when(entityManagerMock.createNativeQuery("SELECT MAX(code) FROM product;")).thenReturn(mockQuery);
        when(mockQuery.getResultList()).thenReturn(Collections.singletonList("CO0001"));

        String codigo = productoService.GeneradorCodigo();

        assertEquals("CO0002", codigo);
    }

    @Test
    public void testUpdateProducto_WithValidProducto_ReturnsUpdatedProductoDto() {
        // Arrange
        ProductoDto productoDto = new ProductoDto();
        Empresa empresa = new Empresa();
        empresa.setId(1L);
        productoDto.setId(1L);
        productoDto.setNombre("Producto Actualizado");
        productoDto.setPrecio(BigDecimal.valueOf(20).doubleValue());
        productoDto.setEmpresa(mapper.Mapper(empresa, EmpresaDto.class));

        Producto producto = new Producto();
        producto.setId(1L);
        producto.setNombre("Producto Original");
        producto.setPrecio(BigDecimal.TEN.doubleValue());
        producto.setEmpresa(mapper.Mapper(empresa, Empresa.class));

        when(productoDaoMock.getProductoById(productoDto.getId())).thenReturn(producto);
        when(productoDaoMock.save(producto)).thenReturn(producto);

        // Act
        ProductoDto updatedProductoDto = productoService.UpdateProducto(productoDto);

        // Assert
        assertEquals(productoDto.getId(), updatedProductoDto.getId());
        assertEquals(productoDto.getNombre(), updatedProductoDto.getNombre());
        assertEquals(productoDto.getPrecio(), updatedProductoDto.getPrecio());
    }
    @Test
    public void testListaProductos_ReturnsListOfProductoDto() {
        // Arrange
        List<Producto> productos = new ArrayList<>();
        productos.add(new Producto(1L, "Producto 1", BigDecimal.TEN.doubleValue()));
        productos.add(new Producto(2L, "Producto 2", BigDecimal.valueOf(20).doubleValue()));

        when(productoDaoMock.findAll()).thenReturn(productos);


        List<ProductoDto> productoDtos = productoService.ListaProductos();

        assertEquals(productos.size(), productoDtos.size());

    }

}
