package com.semillero.solicitudes.persistence.entities;
import jakarta.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Date;
@Entity
@Table(name = "usuario")
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "nm_id_usuario")
    private Integer idUsuario;



    @Column(name = "nm_id_empleado")
    private Integer idEmpleado;

    @Column(name = "correo")
    private String correo;

    @Column(name = "fe_fecha_creacion", columnDefinition = "DATE DEFAULT CURRENT_DATE")
    private Date fechaCreacion;

    @Column(name = "ds_activo")
    private String activo;

    @Column(name = "ds_contraseña")
    private String contraseña;

    @Column(name = "nm_rol")
    private Integer rol;

    public Integer getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Integer idUsuario) {
        this.idUsuario = idUsuario;
    }

    public Integer getIdEmpleado() {
        return idEmpleado;
    }

    public void setIdEmpleado(Integer idEmpleado) {
        this.idEmpleado = idEmpleado;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public Date getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public String getActivo() {
        return activo;
    }

    public void setActivo(String activo) {
        this.activo = activo;
    }

    public String getContraseña() {
        return contraseña;
    }

    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }

    public Integer getRol() {
        return rol;
    }

    public void setRol(Integer rol) {
        this.rol = rol;
    }

    @Override
    public String toString() {
        return "Usuario{" +
                "idUsuario=" + idUsuario +
                ", idEmpleado=" + idEmpleado +
                ", correo='" + correo + '\'' +
                ", rol=" + rol +
                '}';
    }





}
