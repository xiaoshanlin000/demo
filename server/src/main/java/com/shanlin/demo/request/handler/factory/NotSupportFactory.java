package com.shanlin.demo.request.handler.factory;

import com.shanlin.demo.request.entry.RequestBaseEntry;
import com.shanlin.demo.request.handler.IHttpHandler;
import com.shanlin.demo.request.handler.handlerImpl.NotSupportHandler;
import org.springframework.stereotype.Component;

/**
 * Created by Shanlin on 16/6/6.
 */
@Component
public class NotSupportFactory extends AbstractFactory {

    @Override
    protected Class<? extends IHttpHandler> handlerClass() {
        return NotSupportHandler.class;
    }

    @Override
    public Class<? extends RequestBaseEntry> parameterClass() {
        return RequestBaseEntry.class;
    }

}
