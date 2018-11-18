package com.java.rpc.client;

import java.lang.reflect.Proxy;

/**
 * @Project: 3.DistributedProject
 * @description:  对接口的代理类
 * @author: sunkang
 * @create: 2018-06-23 11:38
 * @ModificationHistory who      when       What
 **/
public class RpcProxyClient {

    public Object createProxy(Class<?> clazz,String host,int port){
        return Proxy.newProxyInstance(clazz.getClassLoader(),new Class[]{clazz},new RemoteInvocationHandler(host,port));
    }
}
