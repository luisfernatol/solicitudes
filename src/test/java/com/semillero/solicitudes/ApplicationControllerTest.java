package com.semillero.solicitudes;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.semillero.solicitudes.controllers.ApplicationController;
import com.semillero.solicitudes.persistence.EmpleadoRepository;
import com.semillero.solicitudes.persistence.entities.Empleado;
import com.semillero.solicitudes.services.EmpleadoService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
public class ApplicationControllerTest {

    @Mock
    private EmpleadoRepository empleadoRepository;

    @InjectMocks
    private EmpleadoService empleadoService;

    @Test
    public void guardarEmpleadoTest() throws Exception {

        String empleadoJson = "{ \"documento\": 5252525, \"tipoDocumento\": \"cc\", \"nombre\": \"Luz Fernanda\", \"apellido\": \"Martinez Gomez\", \"telefono\": \"44654\", \"direccion\": \"diagonal 110\", \"fechaIngreso\": \"2015-05-04\", \"fechaRetiro\": \"2027-05-11\", \"tipoContrato\": \"vigente\", \"estadoEmpleado\": \"activo\", \"supervisorInmediato\": 9, \"cargo\": 9 }";

        ObjectMapper objectMapper = new ObjectMapper();
        Empleado empleado = objectMapper.readValue(empleadoJson, Empleado.class);


        when(empleadoRepository.save(any(Empleado.class))).thenReturn(empleado);


        Empleado empleadoGuardado = empleadoService.guardarEmpleado(empleado);

        assert empleadoGuardado != null;
        assert empleadoGuardado.getNombre().equals("Luz Fernanda");
        assert empleadoGuardado.getApellido().equals("Martinez Gomez");
        assert empleadoGuardado.getDocumento() == 5252525;



    }
    @Mock
    private EmpleadoService eempleadoService;
    @InjectMocks
    private ApplicationController applicationController;
    @Test
    public void consultarEmpleadosTest() throws ParseException {

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date fechaIngreso = format.parse("2015-05-04");
        Date fechaRetiro = format.parse("2027-05-11");

        Empleado empleado1 = new Empleado();
        empleado1.setIdEmpleado(1);
        empleado1.setDocumento(5252525);
        empleado1.setTipoDocumento("cc");
        empleado1.setNombre("Luz Fernanda");
        empleado1.setApellido("Martinez Gomez");
        empleado1.setTelefono("44654");
        empleado1.setDireccion("diagonal 110");
        empleado1.setFechaIngreso(fechaIngreso);
        empleado1.setFechaRetiro(fechaRetiro);
        empleado1.setTipoContrato("vigente");
        empleado1.setEstadoEmpleado("activo");
        empleado1.setSupervisorInmediato(9);
        empleado1.setCargo(9);
        Empleado empleado2 = new Empleado();


        empleado2.setIdEmpleado(2);
        empleado2.setDocumento(99999);
        empleado2.setTipoDocumento("cc");
        empleado2.setNombre("Roberto");
        empleado2.setApellido("Lopez");
        empleado2.setTelefono("755661");
        empleado2.setDireccion("diagonal 45");
        empleado2.setFechaIngreso(fechaIngreso);
        empleado2.setFechaRetiro(fechaRetiro);
        empleado2.setTipoContrato("vigente");
        empleado2.setEstadoEmpleado("activo");
        empleado2.setSupervisorInmediato(9);
        empleado2.setCargo(9);

        List<Empleado> empleadosMock = Arrays.asList(empleado1, empleado2);

        when(eempleadoService.listarEmpleados()).thenReturn(empleadosMock);

        List<Empleado> empleados = applicationController.consultarEmpleados();

        assertEquals(2, empleados.size());
        assertEquals("Luz Fernanda", empleados.get(0).getNombre());
        assertEquals("Roberto", empleados.get(1).getNombre());
    }










}

