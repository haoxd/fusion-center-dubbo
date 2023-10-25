package com.fusion.center.restful;

import com.fusion.center.bff.interfaces.QueryUserInfoDubboRpcService;
import com.fusion.center.bff.interfaces.dto.UserInfoDTO;
import com.fusion.center.bff.interfaces.vo.UserInfoVO;
import com.fusion.center.common.annotation.DubboReferenceAutowired;
import com.fusion.center.common.model.result.Result;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/user/info")
@RestController
public class QueryUserInfoController {

    @DubboReferenceAutowired
    private QueryUserInfoDubboRpcService queryUserInfoDubboRpcService;

    @PostMapping(value = "/query")
    public Result<UserInfoVO> query(@RequestBody @Validated UserInfoDTO userInfoDTO) {
        UserInfoVO userInfoVO = queryUserInfoDubboRpcService.query(userInfoDTO);
        return Result.buildSuc(userInfoVO);
    }

}
