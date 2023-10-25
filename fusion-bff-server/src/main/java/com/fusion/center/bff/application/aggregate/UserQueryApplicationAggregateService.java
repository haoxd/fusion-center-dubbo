package com.fusion.center.bff.application.aggregate;

import com.alibaba.fastjson2.JSONObject;
import com.fusion.center.bff.application.query.UserQueryApplicationService;
import com.fusion.center.bff.domain.model.entity.UserPO;
import com.fusion.center.bff.domain.query.UserQuery;
import com.fusion.center.bff.integration.acl.TripUserInfoQueryAclComponentService;
import com.fusion.center.bff.interfaces.vo.UserInfoVO;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserQueryApplicationAggregateService {

    private final UserQueryApplicationService userQueryApplicationService;

    private final TripUserInfoQueryAclComponentService tripUserInfoQueryAclComponentService;


    public UserInfoVO queryUserInfo(UserQuery userQuery) {
        UserPO userPO = userQueryApplicationService.queryUserInfo(userQuery);
        JSONObject jsonObject = tripUserInfoQueryAclComponentService.queryUserInfo(userQuery.getUserName());
        UserInfoVO userInfoVO = new UserInfoVO();
        userInfoVO.setUserInfo(jsonObject);
        userInfoVO.setUserName(userPO.getUserName());
        return userInfoVO;
    }

}
