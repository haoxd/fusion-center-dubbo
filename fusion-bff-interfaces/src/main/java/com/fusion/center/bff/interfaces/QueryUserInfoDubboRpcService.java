package com.fusion.center.bff.interfaces;

import com.fusion.center.bff.interfaces.dto.UserInfoDTO;
import com.fusion.center.bff.interfaces.vo.UserInfoVO;

public interface QueryUserInfoDubboRpcService {

    UserInfoVO query(UserInfoDTO userInfoDTO);
}
