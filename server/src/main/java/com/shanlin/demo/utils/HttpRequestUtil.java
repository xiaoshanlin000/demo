package com.shanlin.demo.utils;

import io.netty.handler.codec.http.HttpMethod;
import io.netty.handler.codec.http.HttpRequest;
import io.netty.handler.codec.http.QueryStringDecoder;
import io.netty.handler.codec.http.multipart.Attribute;
import io.netty.handler.codec.http.multipart.HttpPostRequestDecoder;
import io.netty.handler.codec.http.multipart.InterfaceHttpData;
import io.netty.util.CharsetUtil;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Shanlin on 16/6/6.
 */
public class HttpRequestUtil {
    /**
     * 解析请求参数
     *
     * @return 包含所有请求参数的键值对, 如果没有参数, 则返回空Map
     */
    public static HashMap<String, String> parseHttpRequest(HttpRequest request) throws IOException {
        HttpMethod method = request.method();

        HashMap<String, String> parmMap = new HashMap<>();

        if (HttpMethod.GET == method) {
            // 是GET请求
            QueryStringDecoder decoder = new QueryStringDecoder(request.uri());
            decoder.parameters().entrySet().forEach(entry -> {
                // entry.getValue()是一个List, 只取第一个元素
                parmMap.put(entry.getKey(), entry.getValue().get(0));
            });
        } else if (HttpMethod.POST == method) {
            // 是POST请求
            HttpPostRequestDecoder decoder = new HttpPostRequestDecoder(request);
            List<InterfaceHttpData> postList = decoder.getBodyHttpDatas();
            // 读取从客户端传过来的参数
            for (InterfaceHttpData data : postList) {
                String name = data.getName();
                String value = null;
                if (InterfaceHttpData.HttpDataType.Attribute == data.getHttpDataType()) {
                    Attribute attribute = (Attribute) data;
                    attribute.setCharset(CharsetUtil.UTF_8);
                    value = attribute.getValue();
                    parmMap.put(name, value);
                }
            }
        }

        return parmMap;
    }
}
