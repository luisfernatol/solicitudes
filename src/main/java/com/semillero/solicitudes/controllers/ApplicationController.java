package com.semillero.solicitudes.controllers;

import com.semillero.solicitudes.excepcion.ErrorRespuesta;
import com.semillero.solicitudes.excepcion.RecursoNoEncontradoExcepcion;
import com.semillero.solicitudes.persistence.entities.Empleado;
import com.semillero.solicitudes.persistence.entities.SolicitudEntity;
import com.semillero.solicitudes.persistence.entities.Usuario;
import com.semillero.solicitudes.services.EmpleadoService;
import com.semillero.solicitudes.services.SolicitudService;
import com.semillero.solicitudes.services.UsuarioService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.*;
import java.util.Date;
import java.util.List;


@RestController
//http://localhost:8080/solicitudes-app
@RequestMapping("solicitudes-app")

@CrossOrigin(value = "http://localhost:4200")
public class ApplicationController {

    private static final Logger logger= LoggerFactory.getLogger(ApplicationController.class);

    @Autowired
    private EmpleadoService empleadoService;

    @Autowired
    private SolicitudService solicitudService;

    @Autowired
    private UsuarioService usuarioService;

    // 1 Crear un nuevo empleado
    @PostMapping("/empleados")
    public Empleado crearEmpleado(@RequestBody Empleado empleado){
        logger.info("Empleado A crear: "+ empleado);
        return this.empleadoService.guardarEmpleado(empleado);
    }

    // 2 Consultar listado de empleados
    //http://localhost:8080/solicitudes-app/empleados             ruta para obtener todos los empleados
    @GetMapping("/empleados")
    public List<Empleado> consultarEmpleados(){
        List<Empleado> empleados = this.empleadoService.listarEmpleados();
        logger.info("Empleados obtenidos:");
        //se mando a imprimir en consola todos los empleados con toString
        empleados.forEach((empleado -> logger.info(empleado.toString())));
        return empleados;
    }

    // 3. Editar la información de un empleado
    @PutMapping("/empleados/{id}")
    public ResponseEntity<Empleado> editarEmpleado(
            @PathVariable int id,
            @RequestBody Empleado empleadoRecibido){
        Empleado empleado = this.empleadoService.buscarEmpleadoPorId(id);
        if (empleado==null)
            throw new RecursoNoEncontradoExcepcion("No se pudo encontrar el id" + id);


        empleado.setDocumento(empleadoRecibido.getDocumento());
        empleado.setTipoDocumento(empleadoRecibido.getTipoDocumento());
        empleado.setNombre(empleadoRecibido.getNombre());
        empleado.setApellido(empleadoRecibido.getApellido());
        empleado.setTelefono(empleadoRecibido.getTelefono());
        empleado.setDireccion(empleadoRecibido.getDireccion());
        empleado.setFechaIngreso(empleadoRecibido.getFechaIngreso());
        empleado.setFechaRetiro(empleadoRecibido.getFechaRetiro());
        empleado.setTipoContrato(empleadoRecibido.getTipoContrato());
        empleado.setEstadoEmpleado(empleadoRecibido.getEstadoEmpleado());
        empleado.setSupervisorInmediato(empleadoRecibido.getSupervisorInmediato());
        empleado.setCargo(empleadoRecibido.getCargo());
        this.empleadoService.guardarEmpleado(empleado);
        return ResponseEntity.ok(empleado);
    }

    // 4 Crear una solicitud de vacaciones y 5  Validar las reglas de una solicitud de vacaciones y determinar si cumple o no dichas reglas para ser aprobada
    //http://localhost:8080/solicitudes-app/solicitudes/solicitar/aquiVaElIdDelEmpleado
    @PostMapping("/solicitudes/solicitar/{id}")
    public ResponseEntity<SolicitudEntity> crearSolicitudes(
            @RequestBody SolicitudEntity solicitudEntity,
            @PathVariable int id){

        Empleado empleado = empleadoService.buscarEmpleadoPorId(id);
        if (empleado == null) {
            throw new RecursoNoEncontradoExcepcion("Empleado no encontrado con ID: " + id);
        }


        //buscar el idUsuario con el idEmpleado
        List<Usuario> usuarioOptional = this.usuarioService.buscarUsuarioporIdEmpleado(id);
        Usuario usuario = usuarioOptional.get(0);
        Integer idUsuario = usuario.getIdUsuario();
        logger.info("Probando:"+idUsuario);
       if (!idUsuario.equals(solicitudEntity.getIdUsuario())) {
            logger.info(" El id de Usuario proporcionado es diferente al id del Usuario del empleado"+", IdUsuario dado:"+ solicitudEntity.getIdUsuario()+", IdUusario del Empleado:"+idUsuario);
            throw  new RecursoNoEncontradoExcepcion(" El id de Usuario proporcionado es diferente al id del Usuario del emplaedo"+", IdUsuario dado:"+ solicitudEntity.getIdUsuario()+", IdUusario del Empleado:"+idUsuario);

        }

        LocalDate fechaActual = LocalDate.now();

        Date date = this.empleadoService.obtenerFechaIngresoEmpleado(id);
        Date fechaInicialSolicitud = solicitudEntity.getFechaInicio();
        Date fechaFinallSolicitud = solicitudEntity.getFechaFin();
        Date fechaRegresoSolicitud = solicitudEntity.getFechaRetorna();

        LocalDate fechaIngreso = date.toInstant().atZone(ZoneId.of("UTC")).toLocalDate();
        LocalDate fechaIni = fechaInicialSolicitud.toInstant().atZone(ZoneId.of("UTC")).toLocalDate();
        LocalDate fechafn = fechaFinallSolicitud.toInstant().atZone(ZoneId.of("UTC")).toLocalDate();
        LocalDate fechaRE = fechaRegresoSolicitud.toInstant().atZone(ZoneId.of("UTC")).toLocalDate();

        Integer diasSolicitados = solicitudEntity.getDiasSolicita();
        String contrato = this.empleadoService.obtenerTipoContratoEmpleado(id);
        // Validación de reglas antes de crear la solicitud
        // se guarda con un dia de atraso para solucionar esto voy a gyUrdar otra vez con set
        Date fechaInicioDate = Date.from(fechaIni.atStartOfDay(ZoneId.systemDefault()).toInstant()); // Convertir LocalDate a Date
        Date fechaFinalDate = Date.from(fechafn.atStartOfDay(ZoneId.systemDefault()).toInstant()); // Convertir LocalDate a Date
        Date fechaRelDate = Date.from(fechaRE.atStartOfDay(ZoneId.systemDefault()).toInstant()); // Convertir LocalDate a Date
        solicitudEntity.setFechaInicio(fechaInicioDate); solicitudEntity.setFechaFin(fechaFinalDate); solicitudEntity.setFechaRetorna(fechaRelDate);
        if (!this.solicitudService.cumpleReglasVacaciones(solicitudEntity,fechaIngreso,fechaActual,diasSolicitados,contrato,fechaIni,fechafn)) {
            throw  new RecursoNoEncontradoExcepcion("La Solicitud no cumple los requisitos del empleado con id :"+id);
        }else{

            logger.info("Solicitud A crear: "+ solicitudEntity);

            return ResponseEntity.ok(this.solicitudService.guardarSolicitudo(solicitudEntity));

        }

    }

      // 6. Consultar el estado de solicitudes enviadas por empleado y el estado en el que se encuentran en orden de fecha más reciente
    //http://localhost:8080/solicitudes-app/Usuario/administarSolicitudes/aquiVaElIdDeUsario
    //

    @GetMapping("/Usuario/administarSolicitudes/{id}")
    public List<SolicitudEntity> consultarSolicitudesPorCargo(
            @PathVariable int id){
        Usuario usuario = usuarioService.buscarUsuarioPorId(id);
        if (usuario == null) {
            throw new RecursoNoEncontradoExcepcion("Usuario no encontrado con ID: " + id);
        }

        List<SolicitudEntity> solicitudes;
        //busco mi id de empleado
        Integer miIdEmpleado = usuario.getIdEmpleado();
          //1: Rol empleado, 2:Rol administrador, 3:Rol Supervisor, 4:Rol maestro Empleados
        switch (usuario.getRol()) {

            case 2:
                //Si el rol de usuario es 2 mostrara todas las solicitudes existentes organizadas por fecha
                solicitudes = this.solicitudService.obtenerSolicitudesParaAdminisrador();

                break;
            case 3:
                //Si el rol usuario es 3, mostrara solo las solictudes en la que el usuario sea el supervisor del empleado
                solicitudes = this.solicitudService.obtenerSolicitudesParaSupervisor(miIdEmpleado);

                break;

            default:
                throw new RecursoNoEncontradoExcepcion("Rol de usuario denegado: " + usuario.getRol());

        }
        return solicitudes;
    }

    //http://localhost:8080/solicitudes-app/solicitudes             ruta para obtener todos las solicitudes
    @GetMapping("/solicitudes")
    public List<SolicitudEntity> consultarSolicitudes(){
        List<SolicitudEntity> solicitudEntitys = this.solicitudService.listarSolicitud();
        logger.info("Solicitudes obtenidas:");
        //se mando a imprimir en consola todos las solicitudes con toString
        solicitudEntitys.forEach((solicitudEntity -> logger.info(solicitudEntity.toString())));
        return solicitudEntitys;
    }

    //Crear una solicitud de vacaciones SIMPLE SIN VALIDACION
    @PostMapping("/solicitudes")
    public SolicitudEntity crearSolicitud(@RequestBody SolicitudEntity solicitudEntity){
        logger.info("Solicitud A crear: "+ solicitudEntity);
        return this.solicitudService.guardarSolicitudo(solicitudEntity);
    }

    //http://localhost:8080/solicitudes-app/empleados/aquiVaElIdDelEmpleado
    @GetMapping("/empleados/{id}")
    public ResponseEntity<Empleado> obtenerEmpleadoPorId(
            @PathVariable int id){
        Empleado empleado = this.empleadoService.buscarEmpleadoPorId(id);
        if (empleado !=null)
            return ResponseEntity.ok(empleado);
        else
            throw  new RecursoNoEncontradoExcepcion("El ID especificado no fue encontrado:"+id);
    }

    //http://localhost:8080/solicitudes-app/solicitudes/aquiVaElIdDelUsuario
    @GetMapping("/solicitudes/{id}")
    public List<SolicitudEntity> obtenerSolicitudesPorId(
            @PathVariable int id){
        List<SolicitudEntity> solicitudEntity = this.solicitudService.buscarSolicitudesPorId(id);
        Usuario usuario = usuarioService.buscarUsuarioPorId(id);
        if (usuario == null) {
            throw new RecursoNoEncontradoExcepcion("Usuario no se encontrado con ID: " + id);
        }


        //se mando a imprimir en consola todos las solicitudes con toString
        solicitudEntity.forEach((solicitudEntitys -> logger.info(solicitudEntity.toString())));
        return solicitudEntity;
    }

    // Manejador de la excepción personalizada
    @ExceptionHandler(RecursoNoEncontradoExcepcion.class)
    public ResponseEntity<?> manejarRecursoNoEncontradoException(RecursoNoEncontradoExcepcion ex) {
        ErrorRespuesta errorRespuesta = new ErrorRespuesta(HttpStatus.NOT_FOUND, ex.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorRespuesta);
    }
    //http://localhost:8080/solicitudes-app/usuarios             ruta para obtener todos los empleados
    @GetMapping("/usuarios")
    public List<Usuario> consultarUsuario(){
        List<Usuario> usuarios = this.usuarioService.listarUsuarios();
        logger.info("Empleados obtenidos:");
        //se mando a imprimir en consola todos los empleados con toString
        usuarios.forEach((usuario -> logger.info(usuario.toString())));
        return usuarios;
    }
    //http://localhost:8080/solicitudes-app/Usuario/aprovacionSolicitudes/aquiVaElIdDeSolicitud

    @PutMapping("/Usuario/aprovacionSolicitudes/{id}")
    public ResponseEntity<SolicitudEntity> aprobarSolicitudes(
            @PathVariable int id,
            @RequestBody SolicitudEntity solicitudRecibido){
        SolicitudEntity solicitudEntity = this.solicitudService.buscarSolicitudPorId(id);
        if (solicitudEntity==null)
            throw new RecursoNoEncontradoExcepcion("No se pudo encontrar el id" + id);

        solicitudEntity.setEstado(solicitudRecibido.getEstado());

        this.solicitudService.guardarSolicitudo(solicitudEntity);
        return ResponseEntity.ok(solicitudEntity);
    }

}
