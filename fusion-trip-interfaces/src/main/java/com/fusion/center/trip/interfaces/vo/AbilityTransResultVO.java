package com.fusion.center.trip.interfaces.vo;

import com.alibaba.fastjson2.JSONObject;
import lombok.Data;

import java.io.Serializable;

/**
 * 能卡调用结果返回
 **/
@Data
public class AbilityTransResultVO implements Serializable {

    private String transIdo;

    private JSONObject resp;

}
