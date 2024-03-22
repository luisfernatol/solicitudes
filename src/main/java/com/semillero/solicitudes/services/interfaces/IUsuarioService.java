package com.semillero.solicitudes.services.interfaces;


import com.semillero.solicitudes.persistence.entities.Usuario;

import java.util.List;


public interface IUsuarioService {


    public List<Usuario> listarUsuarios();
    public Usuario buscarUsuarioPorId(Integer idUsuario);
    public List<Usuario> buscarUsuarioporIdEmpleado(Integer idEmpleado);

}
