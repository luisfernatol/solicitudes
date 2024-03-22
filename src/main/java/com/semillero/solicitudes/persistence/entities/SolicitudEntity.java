package com.semillero.solicitudes.persistence.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;


import java.time.LocalDate;
import java.util.Date;

@Entity
@Table(name = "solicitud_vaciones")
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class SolicitudEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "nm_id_solicitud")
    private Integer idSolicitud;

    @Column(name = "nm_id_usuario")
    private Integer idUsuario;

    @Column(name = "nm_dias_solicita")
    private Integer diasSolicita;

    @Column(name = "fe_fecha_inicio")
    private Date fechaInicio;

    @Column(name = "fe_fecha_fin")
    private Date fechaFin;

    @Column(name = "fe_fecha_retorna")
    private Date fechaRetorna;

    @Column(name = "ds_estado")
    private String estado;

    @Column(name = "ds_observaciones")
    private String observaciones;

    @Column(name = "fe_fecha_creacion")
    private Date fechaCreacion;

    public Integer getIdSolicitud() {
        return idSolicitud;
    }

    public void setIdSolicitud(Integer idSolicitud) {
        this.idSolicitud = idSolicitud;
    }

    public Integer getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Integer idUsuario) {
        this.idUsuario = idUsuario;
    }

    public Integer getDiasSolicita() {
        return diasSolicita;
    }

    public void setDiasSolicita(Integer diasSolicita) {
        this.diasSolicita = diasSolicita;
    }

    public Date getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public Date getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(Date fechaFin) {
        this.fechaFin = fechaFin;
    }

    public Date getFechaRetorna() {
        return fechaRetorna;
    }

    public void setFechaRetorna(Date fechaRetorna) {
        this.fechaRetorna = fechaRetorna;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public Date getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }
    @Override
    public String toString() {
        return "SolicitudEntity{" +
                "idSolicitud=" + idSolicitud +
                ", idUsuario=" + idUsuario +
                ", diasSolicita=" + diasSolicita +
                ", fechaInicio=" + fechaInicio +
                ", fechaFin=" + fechaFin +
                ", fechaRetorna=" + fechaRetorna +
                ", estado='" + estado + '\'' +
                ", observaciones='" + observaciones + '\'' +
                ", fechaCreacion=" + fechaCreacion +
                '}';
    }



}
