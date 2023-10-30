
package com.fusion.center.spi;

/**
 * 拓展工厂
 * @author haoxd
 */
@SPI("spi")
public interface ExtensionFactory {

    /**
     * 获取扩展
     * @param key key
     * @param clazz Class
     * @param <T> type
     * @return extension
     */
    <T> T getExtension(String key, Class<T> clazz);
}
