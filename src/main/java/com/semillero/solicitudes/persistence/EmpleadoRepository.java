package com.semillero.solicitudes.persistence;

import com.semillero.solicitudes.persistence.entities.Empleado;
import com.semillero.solicitudes.persistence.entities.SolicitudEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface EmpleadoRepository extends JpaRepository<Empleado, Integer> {


}
