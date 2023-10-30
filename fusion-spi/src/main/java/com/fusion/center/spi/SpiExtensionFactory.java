

package com.fusion.center.spi;

/**
 * spi 获取工厂
 * @author haoxd
 */
@Join
public class SpiExtensionFactory implements ExtensionFactory {

    @Override
    public <T> T getExtension(String key, Class<T> clazz) {
        if (clazz.isAnnotationPresent(SPI.class) && clazz.isInterface()) {
            ExtensionLoader<T> extensionLoader = ExtensionLoader.getExtensionLoader(clazz);
            return extensionLoader.getDefaultJoin();
        }
        return null;
    }
}
