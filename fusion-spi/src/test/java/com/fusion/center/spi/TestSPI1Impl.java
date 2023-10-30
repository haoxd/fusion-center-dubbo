
package com.fusion.center.spi;

@Join
public class TestSPI1Impl implements TestSPI {

    @Override
    public void test() {
        System.out.println("test1");
    }
}
