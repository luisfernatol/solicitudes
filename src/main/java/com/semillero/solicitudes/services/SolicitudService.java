package com.semillero.solicitudes.services;

import com.semillero.solicitudes.controllers.ApplicationController;
import com.semillero.solicitudes.excepcion.RecursoNoEncontradoExcepcion;

import com.semillero.solicitudes.persistence.SolicitudRepository;
import com.semillero.solicitudes.persistence.UsuarioReposity;
import com.semillero.solicitudes.persistence.entities.SolicitudEntity;
import com.semillero.solicitudes.persistence.entities.Usuario;
import com.semillero.solicitudes.services.interfaces.ISolicitud;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.time.temporal.ChronoUnit;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class SolicitudService implements ISolicitud {

    private static final Logger logger= LoggerFactory.getLogger(ApplicationController.class);

    @Autowired
    private SolicitudRepository solicitudRepository;


    @Autowired
    private UsuarioReposity usuarioReposity;

    @Autowired
    private EmpleadoService empleadoService;

    @Override
    public List<SolicitudEntity> obtenerSolicitudesParaSupervisor(Integer idEmpleado) {

        // Obtener usuarios asociados a empleados con el supervisor inmediato específico
        List<Usuario> usuarios = this.usuarioReposity.findBySupervisorInmediato(idEmpleado);


        // Obtener las solicitudes asociadas a estos usuarios
        List<Integer> idsUsuarios = usuarios.stream().map(Usuario::getIdUsuario).collect(Collectors.toList());
        return solicitudRepository.findByIdUsuarioIn(idsUsuarios);



    }

    @Override
    public List<SolicitudEntity> obtenerSolicitudesParaAdminisrador() {

        return this.solicitudRepository.findAllByOrderByFechaCreacionDesc();
    }

    @Override
    public SolicitudEntity buscarSolicitudPorId(Integer idSolicitud) {
        return this.solicitudRepository.findById(idSolicitud).orElse(null);
    }

    @Override
    public List<SolicitudEntity> listarSolicitud() {
        return   this.solicitudRepository.findAll();
    }

    @Override
    public List<SolicitudEntity> buscarSolicitudesPorId(Integer idUsuariod) {
        return this.solicitudRepository.findByUsuarioId(idUsuariod);
    }


    public Integer calcularDiasCausados(LocalDate fechaIngreso, LocalDate fechaActual) {
        // Calcular el número total de días hábiles entre la fecha de ingreso y la fecha actual
        long diasLaborados = ChronoUnit.DAYS.between(fechaIngreso, fechaActual);

        // Calcular la proporción del año laborado en el año actual
        int diasPorAnio = 365; // Suponiendo un año de 365 días
        double proporcionAnio = (double) diasLaborados / diasPorAnio;

        // Calcular los días de vacaciones causados hasta la fecha actual basados en la proporción del año laborado
        int diasVacacionesAnio = 15; //


        return (int) Math.round(diasVacacionesAnio * proporcionAnio);
    }

    @Override
    public long calcularDiasHabiles(LocalDate fechaInicio, LocalDate fechaFin) {
        long diasHabiles = 0;

        LocalDate fechaActual = fechaInicio;
        while (!fechaActual.isAfter(fechaFin)) {
            if (fechaActual.getDayOfWeek() != DayOfWeek.SATURDAY && fechaActual.getDayOfWeek() != DayOfWeek.SUNDAY) {
                diasHabiles++;
            }
            fechaActual = fechaActual.plusDays(1);
        }

        return diasHabiles;
    }


    @Override
    public SolicitudEntity guardarSolicitudo(SolicitudEntity solicitudEntity) {
        return this.solicitudRepository.save(solicitudEntity);
    }

    @Override
    public boolean cumpleReglasVacaciones(SolicitudEntity solicitudEntity, LocalDate fechaIngreso,LocalDate fechaActual,Integer diasSolicitados,String contrato,LocalDate fechaInicio, LocalDate fechaFin) {

        if (!empleadoService.cumpleAnioLaborado(fechaIngreso, fechaActual)) {
            logger.info("El empleado no cumple la regla de haber trabajado al menos un año.");
            return false;
        }
        // 2. Se pueden anticipar vacaciones calculando la porción del año para definir los días causados entre la fecha de ingreso y el año laboral.

        int diasCausados = calcularDiasCausados(fechaIngreso, fechaActual);
        if (diasCausados < diasSolicitados ) {
            logger.info("El empleado no tiene suficientes días causados para la cantidad solicitada.");
            return false;
        }


        // 4. Se deben solicitar mínimo con 15 días de anticipación

        LocalDate fechaMinimaSolicitud = fechaActual.plusDays(15);
        if (fechaInicio.isBefore(fechaMinimaSolicitud)) {
            logger.info("La solicitud no se ha realizado con al menos 15 días de anticipación.");
            logger.info("1: "+fechaMinimaSolicitud);
            logger.info("2: "+fechaInicio);
            return false;
        }
        // 5. Solo para empleados con contrato laboral vigente (no prestador de servicios)
        if (!empleadoService.contratoLaboralVigenteo(contrato)) {
            logger.info("El empleado no tiene un contrato laboral vigente.");
            return false;
        }
        long diasHabiles = calcularDiasHabiles(fechaInicio, fechaFin);
        if (diasHabiles < 6){
            logger.info("El periodo de vacaciones que elegiste entre las 2 fechas no cumple con el mínimo de 6 días hábiles consecutivos."+diasHabiles);
            return false;
        }
       if (diasSolicitados< 6 ){
           logger.info("Dias Solicitados:El periodo de vacaciones no cumple con el mínimo de 6 días hábiles consecutivos.."+diasSolicitados);
           return false;

       }
       if(diasSolicitados !=diasHabiles){
           logger.info(" Los (dias solicitados)  y el periodo de vacaciones que elegiste entre el inicio y fin de las vacaciones no son iguales: "+ "Dias Solicitados: " +diasSolicitados+" y Periodo entre fechas solicitados:"+diasHabiles);
           throw  new RecursoNoEncontradoExcepcion(" Los (dias solicitados)  y el periodo de vacaciones que elegiste entre el inicio y fin de las vacaciones no son iguales: "+ "Dias Solicitados: " +diasSolicitados+" y Periodo entre fechas solicitados:"+diasHabiles);


       }

        return true; // Todas las reglas se cumplen
    }


}
