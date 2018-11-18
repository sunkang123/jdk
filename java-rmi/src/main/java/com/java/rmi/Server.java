package com.java.rmi;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;

/**
 * @Project: jdk
 * @description: rmi的服务端
 * @author: sunkang
 * @create: 2018-11-17 17:24
 * @ModificationHistory who      when       What
 **/
public class Server {

    public static void main(String[] args) {

        try {
            //这里会创建代理对象，并会启动ServerSocket进行监听
            IHelloService helloService = new HelloServiceImpl();
            //启动注册中心,也会创建代理对象，并会启动ServerSocket进行监听
            LocateRegistry.createRegistry(1099);
            //在注册中心绑定服务名与对应的服务
            Naming.rebind("rmi://127.0.0.1/Hello",helloService); //注冊中心
            System.out.println("服务启动成功");
        } catch (RemoteException e) {
            e.printStackTrace();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }
}
