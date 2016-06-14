package com.shanlin.demo.request.handler.factory;

import com.shanlin.demo.request.entry.LoginRequestEntry;
import com.shanlin.demo.request.entry.RequestBaseEntry;
import com.shanlin.demo.request.handler.IHttpHandler;
import com.shanlin.demo.request.handler.handlerImpl.LoginHandler;

/**
 * Created by Administrator on 2016/6/10.
 */
public class LoginFactory extends AbstractFactory {
    @Override
    protected Class<? extends IHttpHandler> handlerClass() {
        return LoginHandler.class;
    }

    @Override
    public Class<? extends RequestBaseEntry> parameterClass() {
        return LoginRequestEntry.class;
    }
}
