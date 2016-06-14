package com.shanlin.demo.request.handler;

import com.shanlin.demo.request.handler.factory.LoginFactory;
import com.shanlin.demo.request.handler.factory.LogoutFactory;
import com.shanlin.demo.request.handler.factory.NotSupportFactory;
import com.shanlin.demo.request.handler.factory.RegisterFactory;

import java.util.HashMap;

/**
 * Created by Shanlin on 16/6/6.
 */
public class HttpFactoryManager {

    private static final HashMap<String,IHttpFactory> factoryMaps = new HashMap<>();
    private static final IHttpFactory notSupportFactory = new NotSupportFactory();
    private static final String head = "/shanlin/demo/";

    static{
        put("register.action",new RegisterFactory());
        put("login.action",new LoginFactory());
        put("logout.action",new LogoutFactory());
        put("resetPassword.action",null);
        put("getCode.action",null);
        put("addFriend.action",null);
    }
    /**
     * 初始化
     */
    public static void initHttpFactoryManager(){
        //
    }

    /**
     * 根据URL获取 IHttpFactory
     * @param url
     * @return IHttpFactory
     */
    public static IHttpFactory getIHttpFactory(String url){
        IHttpFactory factory = factoryMaps.get(url);
        return  factory == null ? notSupportFactory : factory ;
    }



    private static void put(String key,IHttpFactory factory){
        factoryMaps.put(head+key, factory);
    }

}
