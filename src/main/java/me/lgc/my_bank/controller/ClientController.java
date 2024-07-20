package me.lgc.my_bank.controller;

import me.lgc.my_bank.record.ClientRecord;
import me.lgc.my_bank.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/client")
public class ClientController {

    @Autowired
    ClientService clientService;
    @PostMapping
    public ResponseEntity<String> create(@RequestBody ClientRecord newClient) {
        var clientId = clientService.create(newClient);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(clientId)
                .toUri();
        return ResponseEntity.created(location).body("ClientID: " + clientId);
    }


    @GetMapping("/{id}")
    public ResponseEntity<ClientRecord> findById(@PathVariable Long id){
        var client = clientService.findById(id);
        return ResponseEntity.ok(client);
    }
    @GetMapping
    public ResponseEntity<List<ClientRecord>> findAllClients(){
        List<ClientRecord> clients = clientService.findAll();
        return ResponseEntity.ok(clients);
    }
}
