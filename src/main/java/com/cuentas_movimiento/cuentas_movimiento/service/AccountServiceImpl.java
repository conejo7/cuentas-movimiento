package com.cuentas_movimiento.cuentas_movimiento.service;


import com.cuentas_movimiento.cuentas_movimiento.data.AccountRepository;
import com.cuentas_movimiento.cuentas_movimiento.facade.MovementFacade;
import com.cuentas_movimiento.cuentas_movimiento.model.pojo.Account;
import com.cuentas_movimiento.cuentas_movimiento.model.pojo.Client;
import com.cuentas_movimiento.cuentas_movimiento.model.request.CreateAccountRequest;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

@Service
public class AccountServiceImpl implements AccountService {


    private final AccountRepository accountRepository;

    private final MovementFacade movementFacade;


    public AccountServiceImpl(AccountRepository accountRepository, MovementFacade movementFacade) {
        this.accountRepository = accountRepository;

        this.movementFacade = movementFacade;
    }


    @Override
    public List<Account> getAccounts(){
        List<Account> accounts = accountRepository.findAll();
        return accounts.isEmpty() ? null : accounts;
    }

    @Override
    public Account getAccount(String accountId) {

        return accountRepository.findById(Long.valueOf(accountId)).orElse(null);
    }

    @Override
    public Boolean removeAccount(String accountId) {

        Account account = accountRepository.findById(Long.valueOf(accountId)).orElse(null);

        if (account != null) {
            accountRepository.delete(account);
            return Boolean.TRUE;
        } else {
            return Boolean.FALSE;
        }
    }

    @Override
    public Account createAccount(CreateAccountRequest request) {

        if (request != null && StringUtils.hasLength(request.getNumberAccount().trim())
                && StringUtils.hasLength(request.getTypeAccount().trim())
                && StringUtils.hasLength(String.valueOf(request.getInitialBalance()))) {

            Client cliente = movementFacade.getClientById(String.valueOf(request.getClient().getClienteid()));

            if (cliente == null ) {
                throw new IllegalArgumentException("Cliente no encontrado ");
            }

            Account account = Account.builder().numeroCuenta(request.getNumberAccount()).tipoCuenta(request.getTypeAccount())
                    .saldoInicial(request.getInitialBalance()).estado(request.isState()).cliente(cliente).build();

            return accountRepository.save(account);
        } else {
            return null;
        }
    }

    @Override
    public Account updateAccount(String accountId, CreateAccountRequest clientRequest) {
        Account account = accountRepository.findById(Long.valueOf(accountId)).orElse(null);
        if (account !=null){
            account.setNumeroCuenta(clientRequest.getNumberAccount());
            account.setTipoCuenta(clientRequest.getTypeAccount());
            account.setSaldoInicial(clientRequest.getInitialBalance());
            account.setEstado(clientRequest.isState());

            accountRepository.save(account);
        }
        return account;
    }


}
