

package com.fusion.center.spi;

import org.junit.Test;


public class SpiExtensionFactoryTest {

    @Test
    public void getExtensionTest() {
        TestSPI testSPI = ExtensionLoader.getExtensionLoader(TestSPI.class).getJoin("testSPI2");
        testSPI.test();
    }
}
