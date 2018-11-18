package com.java.rpc.server;



import com.java.rpc.api.RpcRequest;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.Socket;

/**
 * @Project: 3.DistributedProject
 * @description:   完成具体服务的调用逻辑和结果的socket写入
 * @author: sunkang
 * @create: 2018-06-23 12:37
 * @ModificationHistory who      when       What
 **/
public class ProcessHandler  implements  Runnable {

    private Socket socket;
    private Object service;

    public ProcessHandler(Socket socket, Object service) {
        this.socket = socket;
        this.service = service;
    }

    @Override
    public void run() {
        ObjectInputStream ois = null;
        ObjectOutputStream oos = null;
        try {
            ois = new ObjectInputStream(socket.getInputStream());
            RpcRequest request = (RpcRequest) ois.readObject();
            Object obj = invoke(request, service);
            oos = new ObjectOutputStream(socket.getOutputStream());
            oos.writeObject(obj);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (ois != null) {
                try {
                    ois.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (oos != null) {
                try {
                    oos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }
    }


    private Object invoke(RpcRequest request, Object service) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Object[] types = request.getParamters();
        Class<?>[] methodParamters = new Class<?>[types.length];
        for (int i = 0; i < types.length; i++) {
            methodParamters[i] = types[i].getClass();
        }
        Method method = service.getClass().getMethod(request.getMethodName(), methodParamters);
        return method.invoke(service, types);
    }

}
