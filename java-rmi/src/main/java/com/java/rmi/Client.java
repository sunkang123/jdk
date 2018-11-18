package com.java.rmi;

import com.sun.xml.internal.ws.message.source.PayloadSourceMessage;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

/**
 * @Project: jdk
 * @description: rmi的客户端
 * @author: sunkang
 * @create: 2018-11-17 17:27
 * @ModificationHistory who      when       What
 **/
public class Client {
    public static void main(String[] args) throws RemoteException, NotBoundException, MalformedURLException {
        //往注册中心查找服务的的stub对象（HelloServiceImpl_stub）
        IHelloService helloService = (IHelloService)Naming.lookup("rmi://127.0.0.1/Hello");
        //进行调用
        System.out.println(helloService.sayHello("sunkang"));
    }
}
