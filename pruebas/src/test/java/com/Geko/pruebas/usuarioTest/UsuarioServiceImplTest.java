package com.Geko.pruebas.usuarioTest;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.util.Optional;

import com.Geko.pruebas.Dao.UsuarioDao;
import com.Geko.pruebas.Dto.UsuarioDto;
import com.Geko.pruebas.Models.Usuario;
import com.Geko.pruebas.Service.ServiceImpl.usuarioServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class UsuarioServiceImplTest {

    @Mock
    private UsuarioDao usuarioDaoMock;

    @InjectMocks
    private usuarioServiceImpl usuarioService;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGetUsuario_WithValidId_ReturnsUsuarioDto() {
        Long idUsuario = 1L;
        Usuario usuario = new Usuario();
        usuario.setId(idUsuario);
        usuario.setNombre("Usuario de prueba");

        when(usuarioDaoMock.findById(idUsuario)).thenReturn(Optional.of(usuario));

        UsuarioDto usuarioDto = usuarioService.getUsuario(idUsuario);

        assertEquals(usuario.getId(), usuarioDto.getId());
        assertEquals(usuario.getNombre(), usuarioDto.getNombre());
    }

    @Test
    public void testGetUsuario_WithInvalidId_ReturnsNull() {

        Long idUsuario = 1L;

        when(usuarioDaoMock.findById(idUsuario)).thenReturn(Optional.empty());

        UsuarioDto usuarioDto = usuarioService.getUsuario(idUsuario);

        assertEquals(null, usuarioDto);
    }
}