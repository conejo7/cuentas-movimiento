package com.cuentas_movimiento.cuentas_movimiento.service;

import com.cuentas_movimiento.cuentas_movimiento.data.AccountRepository;
import com.cuentas_movimiento.cuentas_movimiento.data.MovementRepository;
import com.cuentas_movimiento.cuentas_movimiento.exception.GeneralException;
import com.cuentas_movimiento.cuentas_movimiento.model.dto.CuentaDTO;
import com.cuentas_movimiento.cuentas_movimiento.model.dto.MovimientoDTO;
import com.cuentas_movimiento.cuentas_movimiento.model.dto.ReporteDTO;
import com.cuentas_movimiento.cuentas_movimiento.model.pojo.Account;
import com.cuentas_movimiento.cuentas_movimiento.model.pojo.Movement;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

import java.util.Optional;

@Service
public class ReporteService {


    private final AccountRepository cuentaRepository;


    private final MovementRepository movimientoRepository;

    public ReporteService(AccountRepository cuentaRepository, MovementRepository movimientoRepository) {
        this.cuentaRepository = cuentaRepository;
        this.movimientoRepository = movimientoRepository;
    }

    public ReporteDTO generarReporte(String fechaInicio, String fechaFin, Long clienteId, int page, int size) {

        LocalDate inicio = LocalDate.parse(fechaInicio);
        LocalDate fin = LocalDate.parse(fechaFin);


        Optional<Account> cuentas = cuentaRepository.findById(clienteId);

        Account cuenta = cuentas.orElseThrow(() ->
                new GeneralException("No se encontró la cuenta para el cliente con ID: " + clienteId)
        );
        ReporteDTO reporte = new ReporteDTO();
        reporte.setClienteId(clienteId);
        reporte.setNombreCliente(cuentas.get().getCliente().getPerson().getNombre());

        CuentaDTO cuentaDTO = new CuentaDTO();
        cuentaDTO.setNumeroCuenta(cuenta.getNumeroCuenta());
        cuentaDTO.setTipoCuenta(cuenta.getTipoCuenta());
        cuentaDTO.setSaldoActual(cuenta.getSaldoInicial());


        PageRequest pageRequest = PageRequest.of(page, size);
        Page<Movement> movimientos = movimientoRepository.findByCuentaIdAndFechaBetween(
                cuenta.getId(), inicio, fin, pageRequest
        );


        for (Movement movimiento : movimientos) {
            MovimientoDTO movDTO = new MovimientoDTO();
            movDTO.setFecha(LocalDate.from(movimiento.getFecha()));

            movDTO.setMonto(movimiento.getValor());
            movDTO.setTipoMovimiento(movimiento.getTipoMovimiento());
            movDTO.setSaldo(movimiento.getSaldo());

            cuentaDTO.addMovimiento(movDTO);
        }
        cuentaDTO.setTotalMovimientos(movimientos.getTotalElements());
        cuentaDTO.setTotalPaginas(movimientos.getTotalPages());
        cuentaDTO.setPaginaActual(movimientos.getNumber());

        reporte.addCuenta(cuentaDTO);


        return reporte;
    }

}
