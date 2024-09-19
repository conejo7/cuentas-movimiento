package com.cuentas_movimiento.cuentas_movimiento.facade;

import com.cuentas_movimiento.cuentas_movimiento.model.pojo.Client;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

@Component
@RequiredArgsConstructor
@Slf4j
public class MovementFacade {

    @Value("${getClient.url}")
    private String getClientUrl;

    private final RestTemplate restTemplate;

    public Client getClientById(String id) {

        try {
            return restTemplate.getForObject(String.format(getClientUrl, id), Client.class);
        } catch (HttpClientErrorException e) {
            log.error("Client Error: {}, Product with ID {}", e.getStatusCode(), id);
            return null;
        }
    }

}
