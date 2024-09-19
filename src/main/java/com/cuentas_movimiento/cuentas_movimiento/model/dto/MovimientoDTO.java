package com.cuentas_movimiento.cuentas_movimiento.model.dto;


import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class MovimientoDTO {
    private LocalDate fecha;
    private String descripcion;
    private Double monto;
    private String tipoMovimiento;
    private Double saldo;
}
