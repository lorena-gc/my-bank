package me.lgc.my_bank.record;

import me.lgc.my_bank.domain.model.Account;
import me.lgc.my_bank.domain.model.Card;
import me.lgc.my_bank.domain.model.Feature;
import me.lgc.my_bank.domain.model.New;

import java.util.List;

public record ClientRecord(Long id, String name, Account account, List<Feature> featureList, List<Card> cardList, List<New> newList) {
}
