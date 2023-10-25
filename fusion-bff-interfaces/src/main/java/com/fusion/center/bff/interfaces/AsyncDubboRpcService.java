package com.fusion.center.bff.interfaces;

import java.util.concurrent.CompletableFuture;

public interface AsyncDubboRpcService {

    String sayHello(String name);

    CompletableFuture<String> sayBey(String name);

}
