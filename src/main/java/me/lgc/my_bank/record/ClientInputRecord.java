package me.lgc.my_bank.record;

import me.lgc.my_bank.domain.model.Feature;
import me.lgc.my_bank.domain.model.New;

import java.util.List;

public record ClientInputRecord (String name, AccountInputRecord account, List<Feature> featureList, List<CardInputRecord> cardList, List<New> newList) {
}
