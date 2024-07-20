package me.lgc.my_bank.domain.model;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Builder
@Getter
@Setter
@Entity(name = "tb_client")
public class Client {

    @Id
    @GeneratedValue(strategy =GenerationType.IDENTITY)
    private Long id;
    private String name;

    @OneToOne(cascade = CascadeType.ALL)
    private Account account;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Feature> featureList;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Card> cardList;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<New> newList;

    public Client(Long id, String name, Account account, List<Feature> featureList, List<Card> cardList, List<New> newList) {
        this.id = id;
        this.name = name;
        this.account = account;
        this.featureList = featureList;
        this.cardList = cardList;
        this.newList = newList;
    }

    public Client(){
    }
}
