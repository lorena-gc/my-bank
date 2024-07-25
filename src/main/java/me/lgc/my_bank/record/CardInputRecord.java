package me.lgc.my_bank.record;

import java.math.BigDecimal;

public record CardInputRecord(String number, BigDecimal limit) {
}
