package com.java.rmi;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * @Project: jdk
 * @description:  接口需要继承remote的 实现接口
 * @author: sunkang
 * @create: 2018-11-17 17:20
 * @ModificationHistory who      when       What
 **/
public interface IHelloService extends Remote {
    String sayHello(String msg) throws RemoteException;

}
