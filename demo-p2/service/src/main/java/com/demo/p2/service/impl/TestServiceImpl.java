package com.demo.p2.service.impl;

import com.demo.p2.mapper.TestMapper;
import com.demo.model.BankNode;
import com.demo.model.QueryUnionBankRequest;
import com.demo.p2.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TestServiceImpl implements TestService {

    private final TestMapper testMapper;

    @Autowired
    public TestServiceImpl(TestMapper testMapper) {
        this.testMapper = testMapper;
    }

    public List<BankNode> queryBankNodeList(QueryUnionBankRequest request) {

        List<BankNode> list = testMapper.selectAndList(request);

        return list;
    }

}
