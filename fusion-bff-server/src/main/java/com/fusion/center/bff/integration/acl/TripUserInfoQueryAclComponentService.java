package com.fusion.center.bff.integration.acl;

import com.alibaba.fastjson2.JSONObject;
import com.fusion.center.common.annotation.DubboReferenceAutowired;
import com.fusion.center.common.model.result.Result;
import com.fusion.center.trip.interfaces.TripAbilityTransCommonDubboRpcService;
import com.fusion.center.trip.interfaces.dto.AbilityTransDTO;
import com.fusion.center.trip.interfaces.vo.AbilityTransResultVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TripUserInfoQueryAclComponentService {

    @DubboReferenceAutowired
    private  TripAbilityTransCommonDubboRpcService tripAbilityTransCommonDubboRpcService;

    public JSONObject queryUserInfo(String userName){
        AbilityTransDTO transDTO = new AbilityTransDTO();
        transDTO.setAbilityId(1L);
        JSONObject req = new JSONObject();
        req.put("userName",userName);
        transDTO.setReq(req);

        Result<AbilityTransResultVO> result = tripAbilityTransCommonDubboRpcService.trans(transDTO);
        if(result.successful()){
            AbilityTransResultVO data = result.getData();
            return data.getResp();
        }
        return new JSONObject();
    }

}
