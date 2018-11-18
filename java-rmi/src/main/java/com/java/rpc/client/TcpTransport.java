package com.java.rpc.client;


import com.java.rpc.api.RpcRequest;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

/**
 * @Project: 3.DistributedProject
 * @description:   模拟tcp层 发起socket连接
 * @author: sunkang
 * @create: 2018-06-23 11:45
 * @ModificationHistory who      when       What
 **/
public class TcpTransport {
    private String host;
    private int port;

    public TcpTransport(String host, int port) {
        this.host=host;
        this.port=port;
    }
    private Socket newSocket(){
        try {
            Socket socket = new Socket(host,port);
            return socket;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Object send(RpcRequest request) {
        Socket socket = newSocket();
        ObjectOutputStream oos = null ;
        ObjectInputStream ois =null;
        try {
             oos = new ObjectOutputStream(socket.getOutputStream());
             oos.writeObject(request);
             ois = new ObjectInputStream(socket.getInputStream());
            Object obj =  ois.readObject();
            return obj;
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
             if(socket!=null){
                 try {
                     socket.close();
                 } catch (IOException e) {
                     e.printStackTrace();
                 }
             }
             if(oos != null){
                 try {
                     oos.close();
                 } catch (IOException e) {
                     e.printStackTrace();
                 }
             }
             if(ois !=null){
                 try {
                     ois.close();
                 } catch (IOException e) {
                     e.printStackTrace();
                 }
             }
        }

        return null;
    }

}
