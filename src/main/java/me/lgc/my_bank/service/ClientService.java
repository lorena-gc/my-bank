package me.lgc.my_bank.service;

import me.lgc.my_bank.domain.model.Client;

public interface ClientService {

    Client findById(Long id);

    Client create(Client clientToCreate);
}
