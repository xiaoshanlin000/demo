package com.shanlin.demo.request.handler;

import com.shanlin.demo.exceptions.ResponseException;
import com.shanlin.demo.request.entry.RequestBaseEntry;
import com.shanlin.demo.response.entry.ResponseBaseEntry;

/**
 * Created by Shanlin on 16/6/6.
 */
public interface IHttpHandler<E extends  RequestBaseEntry> {


    /**
     * 检查请求合法性
     * @param entry
     * @return
     */
    public boolean checkRequest(E entry) throws  ResponseException;
    /**
     * 获取一个请求对象并作出响应
     * @param entry
     * @return
     */
    public ResponseBaseEntry handleMessage(E entry) throws ResponseException;
}
