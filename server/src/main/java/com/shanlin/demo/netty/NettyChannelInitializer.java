package com.shanlin.demo.netty;

import com.shanlin.demo.utils.AppUtil;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.SocketChannel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

/**
 * Created by Administrator on 2016/6/5.
 */
@Component
@Qualifier("channelInitializer")
public class NettyChannelInitializer extends ChannelInitializer<SocketChannel> {

    @Autowired
    @Qualifier("nettyChannelHandler")
    private NettyChannelHandler nettyChannelHandler;

    @Override
    protected void initChannel(SocketChannel ch) throws Exception {
        AppUtil.initChannel(ch, nettyChannelHandler);
    }
}
