package com.java.reflect.classes;

import com.sun.istack.internal.NotNull;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.io.Serializable;
import java.util.HashMap;

/**
 * @Project: jdk
 * @description:
 * @author: sunkang
 * @create: 2018-09-27 10:01
 * @ModificationHistory who      when       What
 **/
@Resource
public final  class GenericBean<String>  extends HashMap implements Serializable,Cloneable {



}
