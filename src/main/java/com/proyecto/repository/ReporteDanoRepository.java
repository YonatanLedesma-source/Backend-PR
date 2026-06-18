package com.proyecto.repository;

import com.proyecto.model.ReporteDano;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

@Repository
public interface ReporteDanoRepository extends JpaRepository<ReporteDano, Long> {
    @Query("SELECT r FROM ReporteDano r WHERE r.medidor.id_med = :idMedidor")
    List<ReporteDano> findByMedidorId(@Param("idMedidor") Long idMedidor);
}
