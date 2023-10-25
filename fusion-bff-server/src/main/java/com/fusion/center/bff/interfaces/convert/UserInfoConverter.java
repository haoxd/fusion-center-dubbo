package com.fusion.center.bff.interfaces.convert;

import com.fusion.center.bff.domain.query.UserQuery;
import com.fusion.center.bff.interfaces.dto.UserInfoDTO;

public class UserInfoConverter {

    public static UserQuery UserInfoDTOToUserQuery(UserInfoDTO userInfoDTO) {
        UserQuery userQuery = new UserQuery();
        userQuery.setUserName(userInfoDTO.getSearchKey());
        return userQuery;
    }

}
