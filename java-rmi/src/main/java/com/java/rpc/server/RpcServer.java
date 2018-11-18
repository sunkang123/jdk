package com.java.rpc.server;


import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @Project: 3.DistributedProject
 * @description:   模拟服务发布，发起ServerSocket的监听
 * @author: sunkang
 * @create: 2018-06-23 11:59
 * @ModificationHistory who      when       What
 **/
public class RpcServer {

    private static  final ExecutorService cashedThreadPool = Executors.newCachedThreadPool();

    public void  publish(final Object service, int port){
        try {
            ServerSocket serverSocket  = new ServerSocket(port);
            while(true){
                 Socket socket =  serverSocket.accept();
                cashedThreadPool.execute(new ProcessHandler(socket,service));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
