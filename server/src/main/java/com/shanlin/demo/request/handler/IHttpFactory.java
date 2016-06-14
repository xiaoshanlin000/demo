package com.shanlin.demo.request.handler;

import com.shanlin.demo.request.entry.RequestBaseEntry;

/**
 * Created by Shanlin on 16/6/6.
 */
public interface IHttpFactory {


    /**
     * 获取一个httphandler 对象
     * @return
     */
    public IHttpHandler httpHandler();

    /**
     * 解析参数的 class
     * @return
     */
    public Class<? extends  RequestBaseEntry> parameterClass();

}
