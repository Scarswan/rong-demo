package com.demo.model;

import lombok.Data;

import java.util.List;

@Data
public class QueryUnionBankRequest {

    /**
     * 网点联行号
     */
    private String nodeCode;

    /**
     * 网点名称关键字
     */
    private String KeyWord;

    /**
     * 银行行号
     */
    private String BankNo;
    /**
     * 银行名称
     */
    private String BankName;

    private Integer pageNum;

    private Integer pageSize;

    public List<String> splitList;

    public List<String> nodeNameList;

    public List<String> fixList;


}
