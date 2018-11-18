package com.java.rpc.server;


import com.java.rpc.api.IHello;

import java.rmi.RemoteException;

/**
 * @Project: 3.DistributedProject
 * @description:  具体的服务实现者
 * @author: sunkang
 * @create: 2018-06-23 11:33
 * @ModificationHistory who      when       What
 **/
public class HelloImpl implements IHello {
    @Override
    public String sayHello(String msg) throws RemoteException {
        return "hello world "+ msg;
    }
}
