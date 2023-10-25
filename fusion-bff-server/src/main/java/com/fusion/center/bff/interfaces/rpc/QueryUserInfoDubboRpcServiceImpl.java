package com.fusion.center.bff.interfaces.rpc;

import com.fusion.center.bff.application.aggregate.UserQueryApplicationAggregateService;
import com.fusion.center.bff.domain.query.UserQuery;
import com.fusion.center.bff.interfaces.QueryUserInfoDubboRpcService;
import com.fusion.center.bff.interfaces.convert.UserInfoConverter;
import com.fusion.center.bff.interfaces.dto.UserInfoDTO;
import com.fusion.center.bff.interfaces.vo.UserInfoVO;
import com.fusion.center.trip.interfaces.TripAbilityTransCommonDubboRpcService;
import lombok.AllArgsConstructor;
import org.apache.dubbo.config.annotation.DubboService;

@AllArgsConstructor
@DubboService(interfaceClass = QueryUserInfoDubboRpcService.class,
        group = "${dubbo.provider.group}",
        version = "${dubbo.provider.version}",timeout = 6000)
public class QueryUserInfoDubboRpcServiceImpl implements QueryUserInfoDubboRpcService {

    private final UserQueryApplicationAggregateService userQueryApplicationAggregateService;

    @Override
    public UserInfoVO query(UserInfoDTO userInfoDTO) {
        UserQuery userQuery = UserInfoConverter.UserInfoDTOToUserQuery(userInfoDTO);
        return userQueryApplicationAggregateService.queryUserInfo(userQuery);
    }
}
