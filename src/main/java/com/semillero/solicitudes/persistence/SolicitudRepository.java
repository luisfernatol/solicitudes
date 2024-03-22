package com.semillero.solicitudes.persistence;

import com.semillero.solicitudes.persistence.entities.Empleado;
import com.semillero.solicitudes.persistence.entities.SolicitudEntity;
import com.semillero.solicitudes.persistence.entities.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.persistence.OrderBy;
import java.util.List;

public interface SolicitudRepository extends JpaRepository<SolicitudEntity, Integer> {

    @OrderBy("fechaCreacion DESC") // Ordena las solicitudes por fechaCreacion en orden descendente
    List<SolicitudEntity> findByIdUsuario(Integer idUsuario);

    @OrderBy("fechaCreacion DESC")
    List<SolicitudEntity> findByIdUsuarioIn(List<Integer> idUsuarios);

    List<SolicitudEntity> findAllByOrderByFechaCreacionDesc();

    @OrderBy("fechaCreacion DESC")
    @Query("SELECT u FROM SolicitudEntity u WHERE u.idUsuario = :idUsuario")
    List<SolicitudEntity> findByUsuarioId(Integer idUsuario);



}
