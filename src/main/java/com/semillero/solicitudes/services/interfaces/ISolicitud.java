package com.semillero.solicitudes.services.interfaces;


import com.semillero.solicitudes.persistence.entities.SolicitudEntity;

import java.time.LocalDate;
import java.util.List;

public interface ISolicitud {

    public SolicitudEntity buscarSolicitudPorId(Integer idSolicitud);
    public List<SolicitudEntity> listarSolicitud();

    public List<SolicitudEntity> buscarSolicitudesPorId(Integer idSolicitud);

    public SolicitudEntity guardarSolicitudo(SolicitudEntity solicitudEntity);

    public boolean cumpleReglasVacaciones(SolicitudEntity solicitudEntity, LocalDate fechaIngreso, LocalDate fechaActual,Integer diasSolicitados,String contrato,LocalDate fechaInicio, LocalDate fechaFin);



    public Integer calcularDiasCausados(LocalDate fechaIngreso, LocalDate fechaActual);

    public long calcularDiasHabiles(LocalDate fechaInicio, LocalDate fechaFin);


    public List<SolicitudEntity> obtenerSolicitudesParaSupervisor(Integer idEmpleado);

    public List<SolicitudEntity> obtenerSolicitudesParaAdminisrador();

}
