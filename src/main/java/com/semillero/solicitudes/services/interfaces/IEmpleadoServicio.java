package com.semillero.solicitudes.services.interfaces;

import com.semillero.solicitudes.persistence.entities.Empleado;


import java.time.LocalDate;
import java.util.Date;
import java.util.List;

public interface IEmpleadoServicio {

    public List<Empleado> listarEmpleados();

    public Empleado buscarEmpleadoPorId(Integer idEmpleado);

    public Empleado guardarEmpleado(Empleado empleado);

    public void eliminarEmpleadoId(Integer idEmpleado);

    public Date obtenerFechaIngresoEmpleado(Integer idEmpleado);



    public String obtenerTipoContratoEmpleado(Integer idEmpleado);

    public boolean contratoLaboralVigenteo(String contrato);

    public boolean cumpleAnioLaborado(LocalDate fechaIngreso, LocalDate fechaActual);

    public boolean cumplePeriodoPrueba(LocalDate fechaIngreso, LocalDate fechaActual);




}
