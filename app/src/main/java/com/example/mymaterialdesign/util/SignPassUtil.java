package com.example.mymaterialdesign.util;

import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;

/**
 * Created by dave on 15/12/7.
 */
public class SignPassUtil {
    private static Map<String, Object> paramMap = null;
    private static String timestamp;
    private static String token;
    /**
     * 初始化参数列表
     */
    public static void init(){
        paramMap = new TreeMap<String, Object>();
    }
    /**
     * 添加参数
     * @param name 	参数名
     * @param param	参数值
     */
    public static void addParam(String name, Object param){
        paramMap.put(name, param);
    }
    /**
     * 获取签名
     * @param objects 排序后的参数列表
     * @return  signature
     */
    public static String getSignature(Object[] objects){
        String str = "";
        for (Object s : objects) {
            str += "/" + s;
        }
        str += "/" + timestamp + "/" +token;
        System.out.println("str:"+str);
        String signatureCalculated = MD5Utils.MD532(str);
        return signatureCalculated;
    }
    /**
     * 参数列表排序
     * @return
     */
    public static Object[] getParams(){
        Object [] params = new Object[paramMap.size()];
        int j = 0;
        for (Iterator<String> it = paramMap.keySet().iterator(); it.hasNext();){
            params[j] = paramMap.get( it.next() );
           // params[j]= it.next();
            System.out.println( j+":"+ params[j]);
            j++;
        }

        return params;

    }

    public static  void setTimestamp(String timestamp) {
        SignPassUtil.timestamp = timestamp;
    }

    public static void setToken(String token) {
        SignPassUtil.token = token;
    }
}
