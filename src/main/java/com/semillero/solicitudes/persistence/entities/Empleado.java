package com.semillero.solicitudes.persistence.entities;

import jakarta.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Date;

@Entity
@Table(name = "empleado")
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Empleado {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "nm_id_empleado")
    private Integer idEmpleado;

    @Column(name = "nm_documento")
    private int documento;

    @Column(name = "ds_tipo_documento")
    private String tipoDocumento;

    @Column(name = "ds_nombre")
    private String nombre;

    @Column(name = "ds_apellido")
    private String apellido;

    @Column(name = "ds_telefono")
    private String telefono;

    @Column(name = "ds_direccion")
    private String direccion;

    @Column(name = "fe_fecha_ingreso")
    private Date fechaIngreso;

    @Column(name = "fe_fecha_retiro")
    private Date fechaRetiro;

    @Column(name = "ds_tipo_contrato")
    private String tipoContrato;

    @Column(name = "ds_estado_empleado")
    private String estadoEmpleado;

    @Column(name = "nm_supervisor_inmediato")
    private int supervisorInmediato;

    @Column(name = "nm_cargo")
    private int cargo;



    @Override
    public String toString() {
        return "Empleado{" +
                "idEmpleado=" + idEmpleado +
                ", documento=" + documento +
                ", tipoDocumento='" + tipoDocumento + '\'' +
                ", nombre='" + nombre + '\'' +
                ", apellido='" + apellido + '\'' +
                ", telefono='" + telefono + '\'' +
                ", direccion='" + direccion + '\'' +
                ", fechaIngreso=" + fechaIngreso +
                ", fechaRetiro=" + fechaRetiro +
                ", tipoContrato='" + tipoContrato + '\'' +
                ", estadoEmpleado='" + estadoEmpleado + '\'' +
                ", supervisorInmediato=" + supervisorInmediato +
                ", cargo=" + cargo +

                '}';
    }

    public Integer getIdEmpleado() {
        return idEmpleado;
    }

    public void setIdEmpleado(Integer idEmpleado) {
        this.idEmpleado = idEmpleado;
    }

    public int getDocumento() {
        return documento;
    }

    public void setDocumento(int documento) {
        this.documento = documento;
    }

    public String getTipoDocumento() {
        return tipoDocumento;
    }

    public void setTipoDocumento(String tipoDocumento) {
        this.tipoDocumento = tipoDocumento;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public Date getFechaIngreso() {
        return fechaIngreso;
    }

    public void setFechaIngreso(Date fechaIngreso) {
        this.fechaIngreso = fechaIngreso;
    }

    public Date getFechaRetiro() {
        return fechaRetiro;
    }

    public void setFechaRetiro(Date fechaRetiro) {
        this.fechaRetiro = fechaRetiro;
    }

    public String getTipoContrato() {
        return tipoContrato;
    }

    public void setTipoContrato(String tipoContrato) {
        this.tipoContrato = tipoContrato;
    }

    public String getEstadoEmpleado() {
        return estadoEmpleado;
    }

    public void setEstadoEmpleado(String estadoEmpleado) {
        this.estadoEmpleado = estadoEmpleado;
    }

    public int getSupervisorInmediato() {
        return supervisorInmediato;
    }

    public void setSupervisorInmediato(int supervisorInmediato) {
        this.supervisorInmediato = supervisorInmediato;
    }

    public int getCargo() {
        return cargo;
    }

    public void setCargo(int cargo) {
        this.cargo = cargo;
    }



}
