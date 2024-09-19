package com.cuentas_movimiento.cuentas_movimiento.model.request;


import com.cuentas_movimiento.cuentas_movimiento.model.pojo.Client;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CreateAccountRequest {

    private String numberAccount;

    private String typeAccount;

    private Double initialBalance;

    private boolean state;

    private Client client;





}
