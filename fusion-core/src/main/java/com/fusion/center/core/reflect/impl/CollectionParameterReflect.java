
package com.fusion.center.core.reflect.impl;


import com.fusion.center.core.reflect.Reflect;
import com.fusion.center.spi.Join;
import lombok.extern.slf4j.Slf4j;

import java.io.UnsupportedEncodingException;

/**
 * @author Redick01
 *  2022/3/22 19:43
 */
@Join
@Slf4j
public class CollectionParameterReflect implements Reflect {

    @Override
    public Object reflect(Object obj) throws UnsupportedEncodingException {
        if (obj == null) {
            return null;
        }
        // 脱敏后的参数
        try {
            return SensitiveDataConverter.invokeMsg(obj.toString());
        } catch (Exception e) {
            log.error(LogUtil.exceptionMarker(), e.getMessage(), e);
        }
        return "parameter is null!";
    }
}
