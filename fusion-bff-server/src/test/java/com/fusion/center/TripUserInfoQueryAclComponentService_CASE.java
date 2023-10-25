package com.fusion.center;

import com.alibaba.fastjson2.JSONObject;
import com.fusion.center.bff.integration.acl.TripUserInfoQueryAclComponentService;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;


public class TripUserInfoQueryAclComponentService_CASE extends BaseTest {

    @Autowired
    private TripUserInfoQueryAclComponentService tripUserInfoQueryAclComponentService;

    @Test
    public void queryUserInfo_test() {

        JSONObject reqJson = tripUserInfoQueryAclComponentService.queryUserInfo("zhangsan");
        Assert.assertNotNull(reqJson);
        log.info(reqJson.toJSONString());
    }

}
