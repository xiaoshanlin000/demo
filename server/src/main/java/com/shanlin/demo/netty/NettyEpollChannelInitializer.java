package com.shanlin.demo.netty;

import com.shanlin.demo.utils.AppUtil;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.epoll.EpollSocketChannel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

/**
 * Created by Shanlin on 16/6/8.
 */

@Component
@Qualifier("epollChannelInitializer")
public class NettyEpollChannelInitializer extends ChannelInitializer<EpollSocketChannel> {

    @Autowired
    @Qualifier("nettyChannelHandler")
    private NettyChannelHandler nettyChannelHandler;

    @Override
    protected void initChannel(EpollSocketChannel ch) throws Exception {
        AppUtil.initChannel(ch, nettyChannelHandler);
    }
}
