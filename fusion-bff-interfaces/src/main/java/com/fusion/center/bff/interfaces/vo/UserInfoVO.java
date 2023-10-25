package com.fusion.center.bff.interfaces.vo;

import com.alibaba.fastjson2.JSONObject;
import lombok.Data;

import java.io.Serializable;

@Data
public class UserInfoVO implements Serializable {

    private String userName;

    private JSONObject userInfo;
}
