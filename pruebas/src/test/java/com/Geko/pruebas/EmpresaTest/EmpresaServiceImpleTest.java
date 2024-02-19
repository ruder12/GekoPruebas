package com.Geko.pruebas.EmpresaTest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import com.Geko.pruebas.Dao.EmpresaDao;
import com.Geko.pruebas.Dto.EmpresaDto;
import com.Geko.pruebas.Models.Empresa;
import com.Geko.pruebas.Service.ServiceImpl.EmpresaServiceImple;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import javax.persistence.EntityManager;

@RunWith(MockitoJUnitRunner.class)
public class EmpresaServiceImpleTest {

    // Mocks necesarios para simular el comportamiento del DAO y del EntityManager
    @Mock
    private EmpresaDao empresaDaoMock;

    @Mock
    private EntityManager entityManagerMock;

    // Objeto bajo prueba (SUT): el servicio de la empresa que se está probando
    @InjectMocks
    private EmpresaServiceImple empresaService;

    // Método de configuración que se ejecuta antes de cada prueba
    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this); // Inicialización de los mocks
    }

    @Test
    public void testCreateEmpresa_WithValidEmpresa_ReturnsEmpresaDto() {
        // Arrange
        EmpresaDto empresaDto = new EmpresaDto();
        empresaDto.setNit("123456789");
        empresaDto.setNombre("Empresa de Prueba");
        empresaDto.setAddress("Calle Principal");
        empresaDto.setTelefono("1234567890");

        Empresa empresa = new Empresa();
        empresa.setId(1L);
        empresa.setNit("123456789");
        empresa.setNombre("Empresa de Prueba");
        empresa.setAddress("Calle Principal");
        empresa.setTelefono("1234567890");

        when(empresaDaoMock.save(Mockito.any(Empresa.class))).thenReturn(empresa);

        // Act
        EmpresaDto createdEmpresaDto = empresaService.CreateEmpresa(empresaDto);

        // Assert
        assertEquals(empresa.getId(), createdEmpresaDto.getId());
        assertEquals(empresa.getNit(), createdEmpresaDto.getNit());
        assertEquals(empresa.getNombre(), createdEmpresaDto.getNombre());
        assertEquals(empresa.getAddress(), createdEmpresaDto.getAddress());
        assertEquals(empresa.getTelefono(), createdEmpresaDto.getTelefono());
    }
    @Test
    public void testGetEmpresaById_WithValidId_ReturnsEmpresaDto() {
        // Arrange
        long id = 1L;
        Empresa empresa = new Empresa();
        empresa.setId(id);
        empresa.setNit("123456789");
        empresa.setNombre("Empresa de Prueba");
        empresa.setAddress("Calle Principal");
        empresa.setTelefono("1234567890");

        when(empresaDaoMock.getEmpresaById(id)).thenReturn(empresa);

        // Act
        EmpresaDto retrievedEmpresaDto = empresaService.getEmpresaById(id);

        // Assert
        assertEquals(empresa.getId(), retrievedEmpresaDto.getId());
        assertEquals(empresa.getNit(), retrievedEmpresaDto.getNit());
        assertEquals(empresa.getNombre(), retrievedEmpresaDto.getNombre());
        assertEquals(empresa.getAddress(), retrievedEmpresaDto.getAddress());
        assertEquals(empresa.getTelefono(), retrievedEmpresaDto.getTelefono());
    }
    @Test
    public void testUpdateEmpresa_WithValidEmpresa_ReturnsUpdatedEmpresaDto() {
        // Arrange
        EmpresaDto empresaDto = new EmpresaDto();
        empresaDto.setId(1L);
        empresaDto.setNit("123456789");
        empresaDto.setNombre("Empresa de Prueba Modificada");
        empresaDto.setAddress("Calle Modificada");
        empresaDto.setTelefono("9876543210");

        Empresa empresa = new Empresa();
        empresa.setId(empresaDto.getId());
        empresa.setNit(empresaDto.getNit());
        empresa.setNombre(empresaDto.getNombre());
        empresa.setAddress(empresaDto.getAddress());
        empresa.setTelefono(empresaDto.getTelefono());

        when(empresaDaoMock.getEmpresaById(empresaDto.getId())).thenReturn(empresa);
        when(empresaDaoMock.save(empresa)).thenReturn(empresa);

        // Act
        EmpresaDto updatedEmpresaDto = empresaService.UpdateEmpresa(empresaDto);

        // Assert
        assertEquals(empresaDto.getId(), updatedEmpresaDto.getId());
        assertEquals(empresaDto.getNit(), updatedEmpresaDto.getNit());
        assertEquals(empresaDto.getNombre(), updatedEmpresaDto.getNombre());
        assertEquals(empresaDto.getAddress(), updatedEmpresaDto.getAddress());
        assertEquals(empresaDto.getTelefono(), updatedEmpresaDto.getTelefono());
    }

    @Test
    public void testListaEmpresa_ReturnsListOfEmpresaDto() {
        // Arrange
        List<Empresa> empresas = new ArrayList<>();
        empresas.add(new Empresa(1L, "123456789", "Empresa 1", "Calle 1", "1234567890"));
        empresas.add(new Empresa(2L, "987654321", "Empresa 2", "Calle 2", "9876543210"));

        when(empresaDaoMock.findAll()).thenReturn(empresas);

        // Act
        List<EmpresaDto> listaEmpresaDto = empresaService.ListaEmpresa();

        // Assert
        assertEquals(empresas.size(), listaEmpresaDto.size());
        for (int i = 0; i < empresas.size(); i++) {
            assertEquals(empresas.get(i).getId(), listaEmpresaDto.get(i).getId());
            assertEquals(empresas.get(i).getNit(), listaEmpresaDto.get(i).getNit());
            assertEquals(empresas.get(i).getNombre(), listaEmpresaDto.get(i).getNombre());
            assertEquals(empresas.get(i).getAddress(), listaEmpresaDto.get(i).getAddress());
            assertEquals(empresas.get(i).getTelefono(), listaEmpresaDto.get(i).getTelefono());
        }
    }

    @Test
    public void testDeleteEmpresa_WithValidId_ReturnsTrue() {
        // Arrange
        long id = 1L;

        // Act
        boolean result = empresaService.DeleteEmpresa(id);

        // Assert
        assertTrue(result);
    }
}