package com.semillero.solicitudes;

import com.semillero.solicitudes.controllers.ApplicationController;
import com.semillero.solicitudes.persistence.entities.Usuario;
import com.semillero.solicitudes.services.UsuarioService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ApplicationControllerTestUsuario {

    @Mock
    private UsuarioService usuarioService;

    @InjectMocks
    private ApplicationController applicationController;

    @Test
    public void consultarUsuarioTest() {

        Usuario usuario1 = new Usuario();
        usuario1.setIdUsuario(1);
        usuario1.setIdEmpleado(1);
        usuario1.setCorreo("u22l@hotmail.com");
        usuario1.setRol(1);

        Usuario usuario2 = new Usuario();
        usuario2.setIdUsuario(2);
        usuario2.setIdEmpleado(2);
        usuario2.setCorreo("xD@hotmail.com");
        usuario2.setRol(3);

        List<Usuario> usuariosMock = Arrays.asList(usuario1, usuario2);

        when(usuarioService.listarUsuarios()).thenReturn(usuariosMock);

        List<Usuario> usuarios = applicationController.consultarUsuario();

        assertEquals(2, usuarios.size());

    }
}