package com.fusion.center.common.utils;

import cn.hutool.core.date.DatePattern;
import cn.hutool.core.date.LocalDateTimeUtil;
import cn.hutool.core.util.RandomUtil;
import cn.hutool.core.util.StrUtil;

import java.time.LocalDateTime;

public class StringUtils extends StrUtil {

    /**
     * 获取traceId，生成规则时间戳+8位随机数
     * **/
    public static String traceId(){
        return LocalDateTimeUtil.format(LocalDateTime.now(), DatePattern.PURE_DATETIME_MS_PATTERN)+ RandomUtil.randomNumbers(8);
    }
}
