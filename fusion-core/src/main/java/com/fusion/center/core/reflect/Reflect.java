

package com.fusion.center.core.reflect;

import com.fusion.center.spi.SPI;

import java.io.UnsupportedEncodingException;

/**
 * @author haoxd
 */
@SPI("default")
public interface Reflect {

    /**
     * reflect get parameter
     * @param obj parameter
     * @return format parameter
     * @throws UnsupportedEncodingException see {@link UnsupportedEncodingException}
     */
    Object reflect(Object obj) throws UnsupportedEncodingException;
}
