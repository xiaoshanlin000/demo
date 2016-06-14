package com.shanlin.demo.netty;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.Channel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import javax.annotation.PreDestroy;
import java.net.InetSocketAddress;

/**
 * Created by Administrator on 2016/6/4.
 */
@Component
public class NettyService {

    @Autowired
    @Qualifier("serverBootstrap")
    private ServerBootstrap serverBootstrap;

    @Autowired
    @Qualifier("tcpSocketAddress")
    private InetSocketAddress address;

    private Channel serverChannel;

    public void start() throws Exception {
        serverChannel = serverBootstrap.bind(address).sync().channel().closeFuture().sync().channel();
    }

    @PreDestroy
    public void destroy() throws Exception {
        stop();
    }

    public void stop() throws Exception {
        serverChannel.close();
        serverChannel.parent().close();
    }

    public ServerBootstrap getServerBootstrap() {
        return serverBootstrap;
    }

    public void setServerBootstrap(ServerBootstrap serverBootstrap) {
        this.serverBootstrap = serverBootstrap;
    }

    public InetSocketAddress getAddress() {
        return address;
    }

    public void setAddress(InetSocketAddress address) {
        this.address = address;
    }
}
