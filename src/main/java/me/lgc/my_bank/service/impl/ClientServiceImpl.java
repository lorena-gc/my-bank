package me.lgc.my_bank.service.impl;

import me.lgc.my_bank.domain.model.Client;
import me.lgc.my_bank.domain.repository.ClientRepository;
import me.lgc.my_bank.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
public class ClientServiceImpl implements ClientService {

    @Autowired
    ClientRepository clientRepository;
    @Override
    public Client findById(Long id) {
        return clientRepository.findById(id).orElseThrow(NoSuchElementException::new);
    }

    @Override
    public Client create(Client clientToCreate) {
        if(clientToCreate.getId() != null && clientRepository.existsById(clientToCreate.getId())){
            throw new IllegalArgumentException("This client ID already exists.");
        }
        if (clientRepository.existsByAccountNumber(clientToCreate.getAccount().getNumber())){
            throw new IllegalArgumentException("This Account Number already exists.");
        }
        return clientRepository.save(clientToCreate);
    }
}
