package com.cuentas_movimiento.cuentas_movimiento.model.dto;


import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class ReporteDTO {

    private Long clienteId;
    private String nombreCliente;
    private List<CuentaDTO> cuentas = new ArrayList<>();



    public void addCuenta(CuentaDTO cuenta) {
        this.cuentas.add(cuenta);
    }
}
