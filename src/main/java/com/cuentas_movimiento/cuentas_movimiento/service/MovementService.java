package com.cuentas_movimiento.cuentas_movimiento.service;


import com.cuentas_movimiento.cuentas_movimiento.model.pojo.Movement;
import com.cuentas_movimiento.cuentas_movimiento.model.request.MovementRequest;

import java.util.List;

public interface MovementService {


    List<Movement> getMovements();

    Movement getMovement(String accountId);

    Boolean removeMovement(String accountId);

    Movement createMovement(MovementRequest request);

    Movement updateMovement(String clientId, MovementRequest movementRequest);



}
