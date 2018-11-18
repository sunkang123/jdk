package com.java.rpc.client;


import com.java.rpc.api.IHello;

import java.rmi.RemoteException;

/**
 * @Project: 3.DistributedProject
 * @description:   具体的客户端的调用者
 * @author: sunkang
 * @create: 2018-06-23 11:39
 * @ModificationHistory who      when       What
 **/
public class ClentDemo {

    public static void main(String[] args) throws RemoteException {

        RpcProxyClient proxyClient = new RpcProxyClient();
        IHello hello =(IHello) proxyClient.createProxy(IHello.class,"localhost",8099);
        System.out.println(hello.sayHello("sunkang you are very cool"));
    }
}
