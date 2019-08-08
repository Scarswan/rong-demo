package com.demo.model.test.VO;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class UserWalletVO {

    private int UserId;

    private int WalletId;

    private String UserName;

    private String UserPwd;

    private int UserState;

    private String UserRemarks;

    private int IsDelete;

    private BigDecimal WalletBalance;

    private BigDecimal WalletConsumption;

    private Date UserCreateTime;

    private Date WalletCreateTime;

}
