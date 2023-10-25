package com.fusion.center;

import com.alibaba.fastjson2.JSONObject;
import com.fusion.center.common.model.result.Result;
import com.fusion.center.trip.interfaces.TripAbilityTransCommonDubboRpcService;
import com.fusion.center.trip.interfaces.dto.AbilityTransDTO;
import com.fusion.center.trip.interfaces.vo.AbilityTransResultVO;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.DubboReference;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest(classes = TripAppApplication.class) //获取启动类，加载配置，寻找主配置启动类 （被 @SpringBootApplication 注解的）
@RunWith(SpringRunner.class) //让JUnit运行Spring的测试环境,获得Spring环境的上下文的支持
@AutoConfigureMockMvc
@Slf4j
public class TripAbilityTransCommonDubboRpcService_CASE {

    @DubboReference
    private TripAbilityTransCommonDubboRpcService tripAbilityTransCommonDubboRpcService;

    @Test
    public void trans_test() {

        AbilityTransDTO transDTO = new AbilityTransDTO();
        transDTO.setAbilityId(1L);
        transDTO.setReq(new JSONObject());
        Result<AbilityTransResultVO> trans = tripAbilityTransCommonDubboRpcService.trans(transDTO);
        log.info(JSONObject.toJSONString(trans));
        Assert.assertTrue(trans.successful());
    }


}
