package com.cuentas_movimiento.cuentas_movimiento.controller;

import com.cuentas_movimiento.cuentas_movimiento.model.dto.ReporteDTO;

import com.cuentas_movimiento.cuentas_movimiento.service.ReporteService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/reportes")
public class ReporteController {

    private final ReporteService reporteService;

    @GetMapping
    public ResponseEntity<ReporteDTO> obtenerEstadoCuenta(
            @RequestParam String fechaInicio,
            @RequestParam String fechaFin,
            @RequestParam Long clienteId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {

        ReporteDTO reporte = reporteService.generarReporte(fechaInicio, fechaFin, clienteId, page, size);

        return ResponseEntity.ok(reporte);
    }
}
