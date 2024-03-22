package com.semillero.solicitudes.services;

import com.semillero.solicitudes.persistence.EmpleadoRepository;
import com.semillero.solicitudes.persistence.entities.Empleado;
import com.semillero.solicitudes.services.interfaces.IEmpleadoServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.List;

@Service
public class EmpleadoService implements IEmpleadoServicio {
    @Autowired
    private EmpleadoRepository empleadoRepository;

    @Override
    public List<Empleado> listarEmpleados() {
        return   this.empleadoRepository.findAll();
    }

    @Override
    public Empleado buscarEmpleadoPorId(Integer idEmpleado) {

        return this.empleadoRepository.findById(idEmpleado).orElse(null);
    }
    @Override
    public Date obtenerFechaIngresoEmpleado(Integer idEmpleado) {
        Empleado empleado = this.buscarEmpleadoPorId(idEmpleado);
        return empleado.getFechaIngreso();
    }

    @Override
    public String obtenerTipoContratoEmpleado(Integer idEmpleado) {
        Empleado empleado = this.buscarEmpleadoPorId(idEmpleado);
        return empleado.getTipoContrato();
    }

    @Override
    public boolean contratoLaboralVigenteo(String contrato) {
        // Comparar el estado del contrato con el valor "vigente"
        return "vigente".equals(contrato);
    }


    @Override
    public boolean cumpleAnioLaborado(LocalDate fechaIngreso, LocalDate fechaActual) {
        return ChronoUnit.DAYS.between(fechaIngreso, fechaActual) >= 365;
    }

    @Override
    public boolean cumplePeriodoPrueba(LocalDate fechaIngreso, LocalDate fechaActual) {
        //periodo de prueba es de 2 meses posteriores al ingreso
        return ChronoUnit.MONTHS.between(fechaIngreso, fechaActual) >= 2;
    }



    @Override
    public Empleado guardarEmpleado(Empleado empleado) {
        return this.empleadoRepository.save(empleado);
    }


    @Override
    public void eliminarEmpleadoId(Integer idEmpleado) {
          this.empleadoRepository.deleteById(idEmpleado);
    }




}
