package com.semillero.solicitudes.persistence;

import com.semillero.solicitudes.persistence.entities.Empleado;
import com.semillero.solicitudes.persistence.entities.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface UsuarioReposity extends JpaRepository<Usuario, Integer> {

    @Query("SELECT u FROM Usuario u WHERE u.idEmpleado IN (SELECT e.idEmpleado FROM Empleado e WHERE e.supervisorInmediato = :idSupervisorInmediato)")
    List<Usuario> findBySupervisorInmediato(Integer idSupervisorInmediato);



    @Query("SELECT u FROM Usuario u WHERE u.idEmpleado  = :idEmpleado")
    List<Usuario> findByEmpleadoId(Integer idEmpleado);

}
