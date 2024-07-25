package me.lgc.my_bank.service.impl;

import me.lgc.my_bank.domain.model.Account;
import me.lgc.my_bank.domain.model.Card;
import me.lgc.my_bank.domain.model.Client;
import me.lgc.my_bank.domain.repository.ClientRepository;
import me.lgc.my_bank.record.ClientInputRecord;
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
    public Long create(ClientInputRecord clientToCreate) {
        if (clientRepository.existsByAccountNumber(clientToCreate.account().number())){
            throw new IllegalArgumentException("This Account Number already exists.");
        }
        var account = Account.builder()
                .number(clientToCreate.account().number())
                .agency(clientToCreate.account().agency())
                .limit(clientToCreate.account().limit())
                .build();
        var cardList = clientToCreate.cardList().stream().toList();

        var cards = cardList.stream().map(card -> Card.builder().number(card.number()).limit(card.limit()).build()).toList();

        var client =  clientRepository.save(Client.builder()
                .name(clientToCreate.name())
                .account(account)
                .featureList(clientToCreate.featureList())
                .cardList(cards)
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
