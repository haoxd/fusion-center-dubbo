package com.fusion.center.trip.interfaces.dto;

import com.alibaba.fastjson2.JSONObject;
import lombok.Data;

import java.io.Serializable;

/**
 * 能卡调用入参
 **/
@Data
public class AbilityTransDTO implements Serializable {

    private static final long serialVersionUID = -1771016784021901099L;


    private Long abilityId;

    private JSONObject req;

}
