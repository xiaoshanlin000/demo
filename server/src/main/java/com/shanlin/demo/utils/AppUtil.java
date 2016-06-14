package com.shanlin.demo.utils;

import io.netty.buffer.UnpooledByteBufAllocator;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelPipeline;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;
import org.springframework.context.ConfigurableApplicationContext;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Timestamp;

/**
 * Created by Administrator on 2016/6/7.
 */
public class AppUtil {

    private static ConfigurableApplicationContext context;

    public static void setContext(ConfigurableApplicationContext context) {
        AppUtil.context = context;
    }

    public static <T> T getBean(Class<T> clazz) {
        if (context == null) return null;
        return context.getBean(clazz);
    }

    public static String getToken(String username) {
        return MD5(username + System.currentTimeMillis());
    }

    public final static String MD5(String s) {
        char hexDigits[] = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};
        byte[] btInput = s.getBytes();
        // 获得MD5摘要算法的 MessageDigest 对象
        MessageDigest mdInst = null;
        try {
            mdInst = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        // 使用指定的字节更新摘要
        mdInst.update(btInput);
        // 获得密文
        byte[] md = mdInst.digest();
        // 把密文转换成十六进制的字符串形式
        int j = md.length;
        char str[] = new char[j * 2];
        int k = 0;
        for (int i = 0; i < j; i++) {
            byte byte0 = md[i];
            str[k++] = hexDigits[byte0 >>> 4 & 0xf];
            str[k++] = hexDigits[byte0 & 0xf];
        }
        return new String(str);

    }

    /**
     * 初始化channel
     *
     * @param ch
     * @param channelHandler
     */
    public static void initChannel(Channel ch, ChannelHandler channelHandler) {
        ChannelPipeline pipeline = ch.pipeline();
        ch.config().setAllocator(UnpooledByteBufAllocator.DEFAULT);
        pipeline.addLast(new HttpServerCodec());
        pipeline.addLast(new HttpObjectAggregator(1048576));// post 使用的.
        pipeline.addLast(channelHandler);
    }

    public static Timestamp currentTimeMillis() {
        return new Timestamp(System.currentTimeMillis());
    }

}
