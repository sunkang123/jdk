package com.java.rpc.api;

import java.rmi.RemoteException;

/**
 * @Project: 3.DistributedProject
 * @description:   IHello 调用的接口
 * @author: sunkang
 * @create: 2018-06-23 11:30
 * @ModificationHistory who      when       What
 **/
public interface IHello {
    String sayHello(String msg) throws RemoteException;
}
