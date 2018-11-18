package com.sunkang.jdk;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * @Project: jdk
 * @description:
 * @author: sunkang
 * @create: 2018-09-05 11:38
 * @ModificationHistory who      when       What
 **/
public class MapTest {

    public static void main(String[] args) {
        Map<String,String> map = new HashMap<>();
        Map<String,String> newMap = new HashMap<>();

        newMap.put("12","231312");

        for(int i =0 ;i<5;i++){
            String  str  = String.valueOf(i);
            map.put(str,str);
            newMap.put(str,str);
        }

        Collection<String> list = newMap.values();

       boolean tag =  list.removeAll(map.values());

       if(tag){
           System.out.println(list.toString());
           System.out.println(list.size());
       }

    }

}
