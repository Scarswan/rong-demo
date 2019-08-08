package com.demo.model.test;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class Wallet {

    private int WalletId;

    private BigDecimal WalletBalance;

    private BigDecimal WalletConsumption;

    private int UserId;

    private Date CreateTime;

}
