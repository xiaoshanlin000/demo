package com.shanlin.demo.request.handler.factory;

import com.shanlin.demo.request.entry.RegisterRequestEntry;
import com.shanlin.demo.request.entry.RequestBaseEntry;
import com.shanlin.demo.request.handler.IHttpHandler;
import com.shanlin.demo.request.handler.handlerImpl.RegisterHandler;
import org.springframework.stereotype.Component;

/**
 * Created by Administrator on 2016/6/7.
 */
@Component
public class RegisterFactory extends AbstractFactory {

    @Override
    protected Class<? extends IHttpHandler> handlerClass() {
        return RegisterHandler.class;
    }

    @Override
    public  Class<? extends  RequestBaseEntry> parameterClass() {
        return RegisterRequestEntry.class;
    }
}
