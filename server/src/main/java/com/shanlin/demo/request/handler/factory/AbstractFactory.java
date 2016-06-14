package com.shanlin.demo.request.handler.factory;

import com.shanlin.demo.request.entry.RequestBaseEntry;
import com.shanlin.demo.request.handler.IHttpFactory;
import com.shanlin.demo.request.handler.IHttpHandler;
import com.shanlin.demo.utils.AppUtil;
import org.springframework.stereotype.Component;

/**
 * Created by Administrator on 2016/6/7.
 */
@Component
public abstract class AbstractFactory implements IHttpFactory{

    @Override
    public IHttpHandler httpHandler()  {
        return  AppUtil.getBean(handlerClass());
    }

    protected abstract  Class<? extends  IHttpHandler>  handlerClass();
}
