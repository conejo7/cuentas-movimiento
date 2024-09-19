package com.cuentas_movimiento.cuentas_movimiento.service;

import com.cuentas_movimiento.cuentas_movimiento.data.AccountRepository;
import com.cuentas_movimiento.cuentas_movimiento.data.MovementRepository;
import com.cuentas_movimiento.cuentas_movimiento.exception.GeneralException;
import com.cuentas_movimiento.cuentas_movimiento.model.pojo.Account;
import com.cuentas_movimiento.cuentas_movimiento.model.pojo.Movement;
import com.cuentas_movimiento.cuentas_movimiento.model.request.MovementRequest;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;

@Service
public class MovementServiceImpl implements MovementService {


    private final MovementRepository movementRepository;

    private final AccountRepository accountRepository;



    public MovementServiceImpl(MovementRepository movementRepository, AccountRepository accountRepository) {
        this.movementRepository = movementRepository;
        this.accountRepository = accountRepository;

    }

    @Override
    public List<Movement> getMovements(){
        List<Movement> movements = movementRepository.findAll();
        return movements.isEmpty() ? null : movements;
    }

    @Override
    public Movement getMovement(String movementId) {
        return movementRepository.findById(Long.valueOf(movementId)).orElse(null);
    }

    @Override
    public Boolean removeMovement(String movementId) {

        Movement account = movementRepository.findById(Long.valueOf(movementId)).orElse(null);

        if (account != null) {
            movementRepository.delete(account);
            return Boolean.TRUE;
        } else {
            return Boolean.FALSE;
        }
    }


    @Override
    public Movement updateMovement(String movementId, MovementRequest movementRequest) {
        Movement movement = movementRepository.findById(Long.valueOf(movementId)).orElse(null);
        if (movement !=null){
            movement.setFecha(movementRequest.getDateTime());
            movement.setTipoMovimiento(movementRequest.getTypeMovement());
            movement.setValor(movementRequest.getValue());
            movement.setSaldo(movementRequest.getBalance());

            movementRepository.save(movement);
        }
        return movement;
    }



    @Override
    public Movement createMovement(MovementRequest request) {

        Optional<Account> optionalAccount = accountRepository.findByNumeroCuenta(request.getAccountRequest().getNumberAccount());
        Account account;
        if (optionalAccount.isPresent()) {
            account = optionalAccount.get();
        } else {
            throw new GeneralException("Cuenta no encontrada o inactiva");
        }

        if (request.getTypeMovement().equalsIgnoreCase("retiro")) {
            if (account.getSaldoInicial() < request.getValue()) {
                throw new GeneralException("Saldo insuficiente para realizar el retiro");
            }
            account.setSaldoInicial(account.getSaldoInicial()-request.getValue());
        } else if (request.getTypeMovement().equalsIgnoreCase("deposito")) {
            account.setSaldoInicial(account.getSaldoInicial()+request.getValue());

        }
        accountRepository.save(account);
        Movement movement = Movement.builder().fecha(request.getDateTime()).tipoMovimiento(request.getTypeMovement())
                .valor(request.getValue()).saldo(account.getSaldoInicial()).account(account).build();

        return movementRepository.save(movement);

    }

}
