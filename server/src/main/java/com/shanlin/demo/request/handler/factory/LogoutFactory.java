package com.shanlin.demo.request.handler.factory;

import com.shanlin.demo.request.entry.LogoutRequestEntry;
import com.shanlin.demo.request.entry.RequestBaseEntry;
import com.shanlin.demo.request.handler.IHttpHandler;
import com.shanlin.demo.request.handler.handlerImpl.LogoutHandler;

/**
 * Created by Administrator on 2016/6/10.
 */
public class LogoutFactory extends AbstractFactory {
    @Override
    protected Class<? extends IHttpHandler> handlerClass() {
        return LogoutHandler.class;
    }

    @Override
    public Class<? extends RequestBaseEntry> parameterClass() {
        return LogoutRequestEntry.class;
    }
}
