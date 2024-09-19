package com.cuentas_movimiento.cuentas_movimiento.service;


import com.cuentas_movimiento.cuentas_movimiento.model.pojo.Account;
import com.cuentas_movimiento.cuentas_movimiento.model.request.CreateAccountRequest;

import java.util.List;

public interface AccountService {


    List<Account> getAccounts();

    Account getAccount(String accountId);

    Boolean removeAccount(String accountId);

    Account createAccount(CreateAccountRequest request);

    Account updateAccount(String clientId, CreateAccountRequest cuentaRequest);
}
