package com.demo.feign;

import com.demo.model.BankNode;
import com.demo.model.JsonResult;
import com.demo.model.QueryUnionBankRequest;
import com.demo.model.test.User;
import com.demo.model.test.VO.UserWalletVO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Component
@FeignClient(value = "demo-p2")
public interface DemoProviderInterface {

    @RequestMapping(value = "/test/hello", method = RequestMethod.POST)
    public String hello(@RequestBody String param);

    @RequestMapping(value = "/test/query/nos", method = RequestMethod.POST)
    public List<BankNode> queryBankNodeList(@RequestBody QueryUnionBankRequest request);

    @RequestMapping(value = "/user/query/all", method = RequestMethod.POST)
    public List<User> queryAllUser();

    @RequestMapping(value = "/user/delete/appoint", method = RequestMethod.POST)
    public JsonResult deleteAppointUser(@RequestBody String json);

    @RequestMapping(value = "/user/query/uaw", method = RequestMethod.POST)
    public UserWalletVO queryUAndW(@RequestBody Integer userId);

}
