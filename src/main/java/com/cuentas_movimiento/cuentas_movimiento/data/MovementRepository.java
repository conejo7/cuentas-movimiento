package com.cuentas_movimiento.cuentas_movimiento.data;

import com.cuentas_movimiento.cuentas_movimiento.model.pojo.Movement;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MovementRepository extends JpaRepository<Movement,Long> {


}
