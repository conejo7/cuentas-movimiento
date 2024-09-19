package com.cuentas_movimiento.cuentas_movimiento.data;

import com.cuentas_movimiento.cuentas_movimiento.model.pojo.Movement;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;

public interface MovementRepository extends JpaRepository<Movement,Long> {

    Page<Movement> findByCuentaIdAndFechaBetween(Long cuentaId, LocalDate fechaInicio, LocalDate fechaFin, Pageable pageable);
}
