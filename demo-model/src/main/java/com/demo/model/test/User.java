package com.demo.model.test;

import lombok.Data;

import java.util.Date;

@Data
public class User {

    private int UserId;

    private String UserName;

    private String UserPwd;

    private int UserState;

    private int WalletId;

    private String UserRemarks;

    private int IsDelete;

    private Date CreateTime;

}
