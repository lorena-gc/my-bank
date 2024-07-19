package me.lgc.my_bank.domain.repository;

import me.lgc.my_bank.domain.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<Client, Long> {

    boolean existsByAccountNumber(String accountNumber);
}
