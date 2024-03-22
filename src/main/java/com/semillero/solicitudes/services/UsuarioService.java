package com.semillero.solicitudes.services;


import com.semillero.solicitudes.persistence.UsuarioReposity;


import com.semillero.solicitudes.persistence.entities.Usuario;
import com.semillero.solicitudes.services.interfaces.IUsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class UsuarioService implements IUsuarioService {

   @Autowired
   private UsuarioReposity usuarioReposity;



    @Override
    public List<Usuario> listarUsuarios() {
        return   this.usuarioReposity.findAll();
    }
    @Override
    public Usuario buscarUsuarioPorId(Integer idUsuario) {
        return this.usuarioReposity.findById(idUsuario).orElse(null);
    }

    public List<Usuario> buscarUsuarioporIdEmpleado(Integer idEmpleado){


        return this.usuarioReposity.findByEmpleadoId(idEmpleado);

    }
}
