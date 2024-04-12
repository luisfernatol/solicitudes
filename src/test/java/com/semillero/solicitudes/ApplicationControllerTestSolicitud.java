package com.semillero.solicitudes;

import com.semillero.solicitudes.controllers.ApplicationController;
import com.semillero.solicitudes.persistence.entities.Empleado;
import com.semillero.solicitudes.persistence.entities.SolicitudEntity;
import com.semillero.solicitudes.persistence.entities.Usuario;
import com.semillero.solicitudes.services.EmpleadoService;
import com.semillero.solicitudes.services.SolicitudService;
import com.semillero.solicitudes.services.UsuarioService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class ApplicationControllerTestSolicitud {

    @Mock
    private EmpleadoService empleadoService;

    @Mock
    private SolicitudService solicitudService;

    @Mock
    private UsuarioService usuarioService;

    @InjectMocks
    private ApplicationController applicationController;

    @Test
    public void crearSolicitudes() throws Exception {
        Empleado empleado = new Empleado();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date fechaIngreso = format.parse("2015-05-04");
        Date fechaRetiro = format.parse("2027-05-11");
        empleado.setIdEmpleado(1);
        empleado.setDocumento(5252525);
        empleado.setTipoDocumento("cc");
        empleado.setNombre("Luz Fernanda");
        empleado.setApellido("Martinez Gomez");
        empleado.setTelefono("44654");
        empleado.setDireccion("diagonal 110");
        empleado.setFechaIngreso(fechaIngreso);
        empleado.setFechaRetiro(fechaRetiro);
        empleado.setTipoContrato("vigente");
        empleado.setEstadoEmpleado("activo");
        empleado.setSupervisorInmediato(9);
        empleado.setCargo(9);

        when(empleadoService.buscarEmpleadoPorId(anyInt())).thenReturn(empleado);

        Usuario usuario = new Usuario();
        usuario.setIdUsuario(1);
        usuario.setIdEmpleado(1);
        usuario.setCorreo("u22l@hotmail.com");

        when(usuarioService.buscarUsuarioporIdEmpleado(anyInt())).thenReturn(Arrays.asList(usuario));

        SolicitudEntity solicitudEntity = new SolicitudEntity();
        solicitudEntity.setIdSolicitud(1);
        solicitudEntity.setIdUsuario(1);
        solicitudEntity.setDiasSolicita(10);
        solicitudEntity.setEstado("pendiente");
        solicitudEntity.setObservaciones("ninguna");
        Date fechaCreacion = format.parse("2023-02-15");
        solicitudEntity.setFechaCreacion(fechaCreacion);


        Date fechaInicio = format.parse("2024-05-01");
        Date fechaFin = format.parse("2024-05-14");
        Date fechaRetorna = format.parse("2024-05-15");


        solicitudEntity.setFechaInicio(fechaInicio);
        solicitudEntity.setFechaFin(fechaFin);
        solicitudEntity.setFechaRetorna(fechaRetorna);


        Date date = Date.from(LocalDate.now().atStartOfDay(ZoneId.systemDefault()).toInstant());
        when(empleadoService.obtenerFechaIngresoEmpleado(anyInt())).thenReturn(date);
        when(empleadoService.obtenerTipoContratoEmpleado(anyInt())).thenReturn("vigente");
        when(solicitudService.cumpleReglasVacaciones(any(), any(), any(), any(), any(), any(), any())).thenReturn(true);
        when(solicitudService.guardarSolicitudo(any(SolicitudEntity.class))).thenReturn(solicitudEntity);

        ResponseEntity<SolicitudEntity> response = applicationController.crearSolicitudes(solicitudEntity, 1);

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(1, response.getBody().getIdSolicitud());
    }

    @Test
    public void crearSolicitud_SinValidacion() throws ParseException {
        SolicitudEntity solicitudEntity = new SolicitudEntity();
        solicitudEntity.setIdSolicitud(1);
        solicitudEntity.setIdUsuario(1);
        solicitudEntity.setDiasSolicita(10);
        solicitudEntity.setEstado("pendiente");
        solicitudEntity.setObservaciones("ninguna");
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

        Date fechaInicio = format.parse("2024-05-01");
        Date fechaFin = format.parse("2024-05-14");
        Date fechaRetorna = format.parse("2024-05-15");



        solicitudEntity.setFechaInicio(fechaInicio);
        solicitudEntity.setFechaFin(fechaFin);
        solicitudEntity.setFechaRetorna(fechaRetorna);
        Date fechaCreacion = format.parse("2023-02-15");
        solicitudEntity.setFechaCreacion(fechaCreacion);


        when(solicitudService.guardarSolicitudo(any(SolicitudEntity.class))).thenReturn(solicitudEntity);

        SolicitudEntity result = applicationController.crearSolicitud(solicitudEntity);

        assertEquals(1, result.getIdSolicitud());
        assertEquals(1, result.getIdUsuario());
        assertEquals(10, result.getDiasSolicita());
        assertEquals("pendiente", result.getEstado());
        assertEquals("ninguna", result.getObservaciones());
    }

    @Test
    public void consultarSolicitudes() throws ParseException {
        SolicitudEntity solicitudEntity = new SolicitudEntity();
        solicitudEntity.setIdSolicitud(1);
        solicitudEntity.setIdUsuario(1);
        solicitudEntity.setDiasSolicita(10);
        solicitudEntity.setEstado("pendiente");
        solicitudEntity.setObservaciones("ninguna");
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date fechaInicio = format.parse("2024-05-01");
        Date fechaFin = format.parse("2024-05-14");
        Date fechaRetorna = format.parse("2024-05-15");
        solicitudEntity.setFechaInicio(fechaInicio);
        solicitudEntity.setFechaFin(fechaFin);
        solicitudEntity.setFechaRetorna(fechaRetorna);
        Date fechaCreacion = format.parse("2023-02-15");
        solicitudEntity.setFechaCreacion(fechaCreacion);

        SolicitudEntity solicitudEntity2 = new SolicitudEntity();
        solicitudEntity2.setIdSolicitud(2);
        solicitudEntity2.setIdUsuario(2);
        solicitudEntity2.setDiasSolicita(10);
        solicitudEntity2.setEstado("pendiente");
        solicitudEntity2.setObservaciones("ninguna");
        solicitudEntity2.setFechaInicio(fechaInicio);
        solicitudEntity2.setFechaFin(fechaFin);
        solicitudEntity2.setFechaRetorna(fechaRetorna);
        solicitudEntity2.setFechaCreacion(fechaCreacion);

        List<SolicitudEntity> solicitudes = Arrays.asList(solicitudEntity, solicitudEntity2);

        when(solicitudService.listarSolicitud()).thenReturn(solicitudes);

        List<SolicitudEntity> result = applicationController.consultarSolicitudes();

        assertEquals(2, result.size());
        assertEquals(1, result.get(0).getIdSolicitud());
        assertEquals(2, result.get(1).getIdSolicitud());
    }




}