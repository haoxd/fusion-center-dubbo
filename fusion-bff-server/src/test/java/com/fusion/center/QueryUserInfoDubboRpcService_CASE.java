package com.fusion.center;

import com.alibaba.fastjson2.JSONObject;
import com.fusion.center.bff.interfaces.QueryUserInfoDubboRpcService;
import com.fusion.center.bff.interfaces.dto.UserInfoDTO;
import com.fusion.center.bff.interfaces.vo.UserInfoVO;
import org.apache.dubbo.config.annotation.DubboReference;
import org.junit.Assert;
import org.junit.Test;

public class QueryUserInfoDubboRpcService_CASE extends BaseTest {

    @DubboReference
    private QueryUserInfoDubboRpcService queryUserInfoDubboRpcService;

    @Test
    public void query_test(){

        UserInfoDTO userInfoDTO = new UserInfoDTO();
        userInfoDTO.setSearchKey("zhangsan");
        UserInfoVO query = queryUserInfoDubboRpcService.query(userInfoDTO);
        Assert.assertNotNull(query);
        log.info(JSONObject.toJSONString(query));
    }

}
