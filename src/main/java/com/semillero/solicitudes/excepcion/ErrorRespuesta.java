package com.semillero.solicitudes.excepcion;

import org.springframework.http.HttpStatus;

public class ErrorRespuesta {
    private HttpStatus estado;
    private String mensaje;

    public ErrorRespuesta(HttpStatus estado, String mensaje) {
        this.estado = estado;
        this.mensaje = mensaje;
    }

    public HttpStatus getEstado() {
        return estado;
    }

    public void setEstado(HttpStatus estado) {
        this.estado = estado;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }
}
