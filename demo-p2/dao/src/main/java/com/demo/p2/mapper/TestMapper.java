package com.demo.p2.mapper;


import com.demo.model.BankNode;
import com.demo.model.QueryUnionBankRequest;

import java.util.List;

public interface TestMapper {

    List<BankNode> selectAndList(QueryUnionBankRequest request);

}
