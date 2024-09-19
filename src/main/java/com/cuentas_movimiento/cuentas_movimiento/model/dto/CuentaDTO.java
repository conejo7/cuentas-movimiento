package com.cuentas_movimiento.cuentas_movimiento.model.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
@Getter
@Setter
public class CuentaDTO {
    private String numeroCuenta;
    private String tipoCuenta;
    private Double saldoActual;
    private List<MovimientoDTO> movimientos = new ArrayList<>();

    private int paginaActual;
    private int totalPaginas;
    private long totalMovimientos;

    public void addMovimiento(MovimientoDTO movimiento) {
        this.movimientos.add(movimiento);
    }
}
