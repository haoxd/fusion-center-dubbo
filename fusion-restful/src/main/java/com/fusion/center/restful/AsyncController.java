package com.fusion.center.restful;

import com.fusion.center.bff.interfaces.AsyncDubboRpcService;
import com.fusion.center.common.annotation.DubboReferenceAutowired;
import com.fusion.center.common.model.result.Result;
import org.apache.dubbo.rpc.RpcContext;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.CompletableFuture;

@RequestMapping("/async")
@RestController
public class AsyncController {

    @DubboReferenceAutowired
    private AsyncDubboRpcService asyncDubboRpcService;

    @GetMapping(value = "/sayHello/{str}")
    public Result<String> sayHello(@PathVariable(name = "str") String str) {
        String s = asyncDubboRpcService.sayHello(str);

        CompletableFuture<String> future = RpcContext.getServiceContext().getCompletableFuture();

        // 为Future添加回调
        future.whenComplete((retValue, exception) -> {
            if (exception == null) {
                System.out.println(retValue);
            } else {
                exception.printStackTrace();
            }
        });

        Result<String> result = Result.buildSuc();
        try {
            result.setData(future.get());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return result;
    }

    @GetMapping(value = "/sayBey/{str}")
    public Result<String> sayBey(@PathVariable(name = "str") String str) {

        // 调用直接返回CompletableFuture
        CompletableFuture<String> future = asyncDubboRpcService.sayBey(str);
        // 增加回调
        future.whenComplete((v, t) -> {
            if (t != null) {
                t.printStackTrace();
            } else {
                System.out.println("Response: " + v);
            }
        });
        // 早于结果输出
        System.out.println("Executed before response return."+str);
        Result<String> result = Result.buildSuc();
        try {
            result.setData(future.get());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return result;
    }

}
