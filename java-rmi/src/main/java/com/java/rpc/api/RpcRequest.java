package com.java.rpc.api;

import java.io.Serializable;

/**
 * @Project: 3.DistributedProject
 * @description:  封装的请求实体
 * @author: sunkang
 * @create: 2018-06-23 11:33
 * @ModificationHistory who      when       What
 **/
public class RpcRequest implements Serializable {

    private String className;

    private String methodName;

    private Object[] paramters;

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    public Object[] getParamters() {
        return paramters;
    }

    public void setParamters(Object[] paramters) {
        this.paramters = paramters;
    }
}
