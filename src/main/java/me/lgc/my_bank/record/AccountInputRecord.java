package me.lgc.my_bank.record;

import java.math.BigDecimal;

public record AccountInputRecord(String number, String agency, BigDecimal limit) {
}
