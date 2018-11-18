package com.java.rmi;

import com.java.rmi.IHelloService;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

/**
 * @Project: jdk
 * @description:  具体的实现需要继承UnicastRemoteObject远程对象
 * @author: sunkang
 * @create: 2018-11-17 17:22
 * @ModificationHistory who      when       What
 **/
    public class HelloServiceImpl extends UnicastRemoteObject implements IHelloService {
    protected HelloServiceImpl() throws RemoteException {
        super();
    }

    @Override
    public String sayHello(String msg) throws RemoteException {
        return "hello,"+ msg;
    }
}
