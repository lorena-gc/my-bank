package me.lgc.my_bank.service;

import me.lgc.my_bank.record.ClientInputRecord;
import me.lgc.my_bank.record.ClientRecord;

import java.util.List;

public interface ClientService {

    ClientRecord findById(Long id);

    Long create(ClientInputRecord clientToCreate);

    List<ClientRecord> findAll();
}
