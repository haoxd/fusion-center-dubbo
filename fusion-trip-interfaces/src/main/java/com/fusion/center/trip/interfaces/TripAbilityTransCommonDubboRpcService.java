package com.fusion.center.trip.interfaces;

import com.fusion.center.common.model.result.Result;
import com.fusion.center.trip.interfaces.dto.AbilityTransDTO;
import com.fusion.center.trip.interfaces.vo.AbilityTransResultVO;

public interface TripAbilityTransCommonDubboRpcService {

    /**
     * @param transDTO 请求入参
     * @return {@link Result} 返回参数
     * */
    Result<AbilityTransResultVO> trans(AbilityTransDTO transDTO);

}
