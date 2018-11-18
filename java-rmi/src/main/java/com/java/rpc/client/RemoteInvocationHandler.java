package com.java.rpc.client;

import com.java.rpc.api.RpcRequest;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * @Project: 3.DistributedProject
 * @description:   具体的代理实现
 * @author: sunkang
 * @create: 2018-06-23 11:41
 * @ModificationHistory who      when       What
 **/
public class RemoteInvocationHandler implements InvocationHandler {
    private String host;
    private int port;

    public RemoteInvocationHandler(String host, int port) {
        this.host=host;
        this.port=port;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        RpcRequest request =new RpcRequest();
        request.setClassName(method.getDeclaringClass().getName());
        request.setMethodName(method.getName());
        request.setParamters(args);

        TcpTransport  tsp =new TcpTransport(host,port);

        return tsp.send(request);
    }
}
