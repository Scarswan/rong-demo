package com.demo.p2.service;

import com.demo.model.BankNode;
import com.demo.model.QueryUnionBankRequest;

import java.util.List;

public interface TestService {

    List<BankNode> queryBankNodeList(QueryUnionBankRequest request);

}
