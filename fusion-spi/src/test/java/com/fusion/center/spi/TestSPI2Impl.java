

package com.fusion.center.spi;


@Join
public class TestSPI2Impl implements TestSPI {

    @Override
    public void test() {
        System.out.println("test2");
    }
}
