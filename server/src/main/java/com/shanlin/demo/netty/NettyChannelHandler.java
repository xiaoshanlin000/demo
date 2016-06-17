package com.shanlin.demo.netty;

import com.alibaba.fastjson.JSON;
import com.shanlin.demo.exceptions.ResponseException;
import com.shanlin.demo.request.entry.RequestBaseEntry;
import com.shanlin.demo.request.handler.HttpFactoryManager;
import com.shanlin.demo.request.handler.IHttpFactory;
import com.shanlin.demo.response.entry.ResponseBaseEntry;
import com.shanlin.demo.utils.HttpRequestUtil;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.*;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.HashMap;

import static com.shanlin.demo.constans.ErrorCode.PARAMETER_ERROR;
import static com.shanlin.demo.constans.ErrorCode.SERVICE_ERROR;
import static io.netty.handler.codec.http.HttpHeaderNames.*;
import static io.netty.handler.codec.http.HttpHeaderValues.KEEP_ALIVE;
import static io.netty.handler.codec.http.HttpResponseStatus.CONTINUE;
import static io.netty.handler.codec.http.HttpResponseStatus.OK;
import static io.netty.handler.codec.http.HttpVersion.HTTP_1_1;

/**
 * Created by Administrator on 2016/6/5.
 */
@Component
@Qualifier("nettyChannelHandler")
@ChannelHandler.Sharable
public class NettyChannelHandler extends SimpleChannelInboundHandler {


    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) {
        ctx.flush();
    }

    @Override
    protected void messageReceived(ChannelHandlerContext ctx, Object msg) throws Exception {
        if (msg instanceof HttpRequest) { // http 请求 有参数
            HttpRequest req = (HttpRequest) msg;
            ResponseBaseEntry responseBaseEntry = null;
            long useTime = System.currentTimeMillis();
            try {
                if (HttpHeaderUtil.is100ContinueExpected(req)) {
                    ctx.write(new DefaultFullHttpResponse(HTTP_1_1, CONTINUE));
                }
                QueryStringDecoder uri = new QueryStringDecoder(req.uri());
                String url = uri.path();
                if ("/favicon.ico".equals(url)) {
                    return;
                }
                HashMap<String, String> parameters = HttpRequestUtil.parseHttpRequest(req); // 参数
                String json = parameters.get("req");
                if (json == null || json.length() == 0 || !json.startsWith("{")) {
                    json = "{}";
                }
                IHttpFactory factory = HttpFactoryManager.getIHttpFactory(url);
                RequestBaseEntry entry = JSON.parseObject(json, factory.parameterClass());
                if (factory.httpHandler().checkRequest(entry)) { // 验证请求参数
                    responseBaseEntry = factory.httpHandler().handleMessage(entry);
                    responseBaseEntry.setUseTime(System.currentTimeMillis() - useTime);
                } else {
                    throw new ResponseException(PARAMETER_ERROR);
                }
            } catch (ResponseException e) {
                responseBaseEntry = e.responseBaseEntry();
                responseBaseEntry.setUseTime(System.currentTimeMillis() - useTime);
            } catch (Exception e) {
                responseBaseEntry = SERVICE_ERROR.responseBaseEntry();
                responseBaseEntry.setUseTime(System.currentTimeMillis() - useTime);
                e.printStackTrace();
            } finally {
                if (responseBaseEntry != null) {
                    boolean keepAlive = HttpHeaderUtil.isKeepAlive(req);
                    String res = JSON.toJSONString(responseBaseEntry);
                    FullHttpResponse response = new DefaultFullHttpResponse(HTTP_1_1, OK, Unpooled.wrappedBuffer(res.getBytes("UTF-8")));
                    response.headers().set(CONTENT_TYPE, "text/plain;charset=UTF-8");
                    response.headers().setInt(CONTENT_LENGTH, response.content().readableBytes());
                    if (!keepAlive) {
                        ctx.write(response).addListener(ChannelFutureListener.CLOSE);
                    } else {
                        response.headers().set(CONNECTION, KEEP_ALIVE);
                        ctx.write(response);
                    }
                }
            }

        }
    }


    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        cause.printStackTrace();
        ctx.close();
    }
}
