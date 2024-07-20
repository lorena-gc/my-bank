package me.lgc.my_bank.service.impl;

import me.lgc.my_bank.domain.model.Client;
import me.lgc.my_bank.domain.repository.ClientRepository;
import me.lgc.my_bank.record.ClientRecord;
import me.lgc.my_bank.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
public class ClientServiceImpl implements ClientService {

    @Autowired
    ClientRepository clientRepository;
    @Override
    public ClientRecord findById(Long id) {
        return clientRepository.findById(id)
                .map(client -> new ClientRecord(
                    client.getId(),
                    client.getName(),
                    client.getAccount(),
                    client.getFeatureList(),
                    client.getCardList(),
                    client.getNewList()))
                .orElseThrow(NoSuchElementException::new);
    }

    @Override
    public Long create(ClientRecord clientToCreate) {
        if (clientRepository.existsByAccountNumber(clientToCreate.account().getNumber())){
            throw new IllegalArgumentException("This Account Number already exists.");
        }
        var client =  clientRepository.save(Client.builder()
                .name(clientToCreate.name())
                .account(clientToCreate.account())
                .featureList(clientToCreate.featureList())
                .cardList(clientToCreate.cardList())
                .newList(clientToCreate.newList())
                .build());

        return client.getId();
    }

    @Override
    public List<ClientRecord> findAll() {
        return clientRepository.findAll()
                .stream()
                .map(client -> new ClientRecord(
                        client.getId(),
                        client.getName(),
                        client.getAccount(),
                        client.getFeatureList(),
                        client.getCardList(),
                        client.getNewList()))
                .collect(Collectors.toList());
    }
}
