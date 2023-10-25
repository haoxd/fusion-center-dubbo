package com.fusion.center.trip.interfaces.rpc;

import cn.hutool.core.date.DatePattern;
import cn.hutool.core.date.DateUnit;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.date.LocalDateTimeUtil;
import cn.hutool.core.util.RandomUtil;
import com.fusion.center.common.model.result.Result;
import com.fusion.center.trip.interfaces.TripAbilityTransCommonDubboRpcService;
import com.fusion.center.trip.interfaces.dto.AbilityTransDTO;
import com.fusion.center.trip.interfaces.vo.AbilityTransResultVO;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.time.DateUtils;
import org.apache.dubbo.config.annotation.DubboService;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@DubboService(interfaceClass = TripAbilityTransCommonDubboRpcService.class,
        group = "${dubbo.provider.group}",
        version = "${dubbo.provider.version}",timeout = 6000)
@Slf4j
public class TripAbilityTransCommonDubboRpcServiceImpl implements TripAbilityTransCommonDubboRpcService{

    @Override
    public Result<AbilityTransResultVO> trans(AbilityTransDTO transDTO) {
        AbilityTransResultVO abilityTransResultVO = new AbilityTransResultVO();
        abilityTransResultVO.setTransIdo(LocalDateTimeUtil.format(LocalDateTime.now(), DatePattern.PURE_DATETIME_MS_PATTERN)+RandomUtil.randomNumbers(12));
        abilityTransResultVO.setResp(transDTO.getReq());
        log.info("进入trip-server服务");
        return Result.buildSuc(abilityTransResultVO);
    }
}
