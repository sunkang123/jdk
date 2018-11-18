package com.java.rpc.server;

import com.java.rpc.api.IHello;
/**
 * @Project: 3.DistributedProject
 * @description:   服务端的具体实现
 * @author: sunkang
 * @create: 2018-06-23 11:59
 * @ModificationHistory who      when       What
 **/
public class ServerDemo {

    public static void main(String[] args) {
        IHello hello = new HelloImpl();

        RpcServer rpcServer = new RpcServer();
        rpcServer.publish(hello,8099);
        System.out.println("rmi服务端已经启动");
    }
}
