package com.demo.consumer.ribbon.controller;

import com.demo.model.BankNode;
import com.demo.model.QueryUnionBankRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
@RequestMapping("/test")
public class TestController {

    @Autowired
    RestTemplate restTemplate;

    @RequestMapping(value = "/hello", method = RequestMethod.POST)
    public String queryNos(@RequestBody String param) {
        HttpHeaders headers = new HttpHeaders();
        HttpEntity<String> formEntity = new HttpEntity<String>(param.toString(), headers);
        return restTemplate.postForObject("http://demo-p2/test/hello", formEntity, String.class);
    }

    @RequestMapping(value = "/query/nos", method = RequestMethod.POST)
    public List<BankNode> queryNos(@RequestBody QueryUnionBankRequest request) {
        HttpHeaders headers = new HttpHeaders();
        HttpEntity<QueryUnionBankRequest> formEntity = new HttpEntity<QueryUnionBankRequest>(request, headers);

        return restTemplate.postForObject("http://demo-p2/test/query/nos", formEntity, List.class);
    }

}
