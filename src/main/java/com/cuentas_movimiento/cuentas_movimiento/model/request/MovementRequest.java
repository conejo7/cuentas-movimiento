package com.cuentas_movimiento.cuentas_movimiento.model.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MovementRequest {


    private Long accountId;

    private LocalDateTime dateTime;

    private String typeMovement;

    private Double value;

    private Double balance;

    private CreateAccountRequest accountRequest;

}
