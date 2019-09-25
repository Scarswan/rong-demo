package com.demo.controller;

import com.demo.feign.DemoProviderInterface;
import com.demo.model.BankNode;
import com.demo.model.QueryUnionBankRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/test")
public class TestController {

    @Autowired
    DemoProviderInterface demoProviderInterface;

    @RequestMapping(value = "/hello", method = RequestMethod.POST)
    public String hello(@RequestBody String param) {
        return demoProviderInterface.hello(param);
    }

    @RequestMapping(value = "/query/nos", method = RequestMethod.POST)
    public List<BankNode> queryBankNodeList(@RequestBody QueryUnionBankRequest request) {
        return demoProviderInterface.queryBankNodeList(request);
    }

}
