package com.fusion.center.bff.interfaces.rpc;

import com.fusion.center.bff.interfaces.AsyncDubboRpcService;
import org.apache.dubbo.config.annotation.DubboService;
import org.apache.dubbo.rpc.AsyncContext;
import org.apache.dubbo.rpc.RpcContext;

import java.util.concurrent.CompletableFuture;


@DubboService(interfaceClass = AsyncDubboRpcService.class,
        group = "${dubbo.provider.group}",
        version = "${dubbo.provider.version}", timeout = 6000)
public class AsyncDubboRpcServiceImpl implements AsyncDubboRpcService {

    @Override
    public String sayHello(String name) {
        final AsyncContext asyncContext = RpcContext.startAsync();
        new Thread(() -> {
            // 如果要使用上下文，则必须要放在第一句执行
            asyncContext.signalContextSwitch();
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            // 写回响应
            asyncContext.write("Hello " + name + ", response from provider. AsyncContext");
        }).start();
        return null;
    }

    @Override
    public CompletableFuture<String> sayBey(String name) {
        return CompletableFuture.supplyAsync(() -> {
            System.out.println(name);
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "CompletableFuture async response from provider."+name;
        });
    }
}
