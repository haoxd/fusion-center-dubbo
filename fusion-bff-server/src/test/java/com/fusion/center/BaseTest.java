package com.fusion.center;

import lombok.extern.slf4j.Slf4j;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest(classes = BffAppApplication.class) //获取启动类，加载配置，寻找主配置启动类 （被 @SpringBootApplication 注解的）
@RunWith(SpringRunner.class) //让JUnit运行Spring的测试环境,获得Spring环境的上下文的支持
@AutoConfigureMockMvc
public class BaseTest {

    protected  final Logger log = LoggerFactory.getLogger(getClass());
}
