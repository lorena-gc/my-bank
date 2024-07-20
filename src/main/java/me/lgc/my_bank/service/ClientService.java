package me.lgc.my_bank.service;

import me.lgc.my_bank.domain.model.Client;
import me.lgc.my_bank.record.ClientRecord;

import java.util.List;

public interface ClientService {

    ClientRecord findById(Long id);

    ClientRecord create(ClientRecord clientToCreate);

    List<ClientRecord> findAll();
}
