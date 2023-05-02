package org.bankingProject.jpt.bankingProject.dtos;

import lombok.Data;
import org.bankingProject.jpt.bankingProject.utils.Money;

import java.math.BigDecimal;

@Data
public class MoneyBalanceDTO {
    private BigDecimal amount;
}
