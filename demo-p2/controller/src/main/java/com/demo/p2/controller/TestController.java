package com.demo.p2.controller;

import com.alibaba.fastjson.JSONObject;
import com.demo.model.BankNode;
import com.demo.model.QueryUnionBankRequest;
import com.demo.p2.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/test")
public class TestController {

    @Autowired(required = true)
    private TestService testService;

    @RequestMapping(value = "/hello", method = RequestMethod.POST)
    public String hello(@RequestBody String param) {
        JSONObject jb = JSONObject.parseObject(param);
        return "hello world!" + jb.getString("param");
    }

    /**
     * 查询银行联合号
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "/query/nos", method = RequestMethod.POST)
    public List<BankNode> queryNos(@RequestBody QueryUnionBankRequest request) {

        List<BankNode> result = testService.queryBankNodeList(request);

        return result;
    }

}
