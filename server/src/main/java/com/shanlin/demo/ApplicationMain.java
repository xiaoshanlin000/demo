package com.shanlin.demo;

import com.shanlin.demo.netty.NettyChannelInitializer;
import com.shanlin.demo.netty.NettyEpollChannelInitializer;
import com.shanlin.demo.netty.NettyService;
import com.shanlin.demo.request.handler.HttpFactoryManager;
import com.shanlin.demo.utils.AppUtil;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.PooledByteBufAllocator;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.epoll.EpollEventLoopGroup;
import io.netty.channel.epoll.EpollServerSocketChannel;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.*;

import java.net.InetSocketAddress;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;


/**
 * Created by Shanlin on 16/6/4.
 */
@SpringBootApplication
@ComponentScan(basePackages = "com.shanlin.demo")
@EnableAutoConfiguration
@PropertySource(value = "classpath:nettyserver.properties")
public class ApplicationMain {

    @Configuration
    @Profile("production")
    @PropertySource("classpath:nettyserver.properties")
    static class Production {
    }

    @Configuration
    @Profile("local")
    @PropertySource({"classpath:nettyserver.properties"})
    static class Local {
    }

    public static void main(String[] args) throws Exception {
        System.out.printf("Current system os :" + systemOs + "\n");
        ConfigurableApplicationContext ctx = SpringApplication.run(ApplicationMain.class, args);
        AppUtil.setContext(ctx);
        HttpFactoryManager.initHttpFactoryManager();
        ctx.getBean(NettyService.class).start();
    }

    @Value("${tcp.port}")
    private int tcpPort;

    @Value("${boss.thread.count}")
    private int bossCount;

    @Value("${worker.thread.count}")
    private int workerCount;

    @Value("${so.keepalive}")
    private boolean keepAlive;

    @Value("${so.backlog}")
    private int backlog;

    private static final String systemOs = System.getProperty("os.name");

    @SuppressWarnings("unchecked")
    @Bean(name = "serverBootstrap")
    public ServerBootstrap bootstrap() {

        ServerBootstrap b = new ServerBootstrap();
        b.group(bossGroup(), workerGroup());
        if (useEpoll()) {
            b.childHandler(epollChannelInitializer);
            b.channel(EpollServerSocketChannel.class);
        } else {
            b.childHandler(channelInitializer);
            b.channel(NioServerSocketChannel.class);
        }
        b.handler(new LoggingHandler(LogLevel.INFO));
        b.childOption(ChannelOption.ALLOCATOR, PooledByteBufAllocator.DEFAULT);
        b.option(ChannelOption.ALLOCATOR, PooledByteBufAllocator.DEFAULT);
        Map<ChannelOption<?>, Object> tcpChannelOptions = tcpChannelOptions();
        Set<ChannelOption<?>> keySet = tcpChannelOptions.keySet();
        for (@SuppressWarnings("rawtypes") ChannelOption option : keySet) {
            b.option(option, tcpChannelOptions.get(option));
        }
        return b;
    }

    @Autowired
    @Qualifier("channelInitializer")
    private NettyChannelInitializer channelInitializer;

    @Autowired
    @Qualifier("epollChannelInitializer")
    private NettyEpollChannelInitializer epollChannelInitializer;

    @Bean(name = "tcpChannelOptions")
    public Map<ChannelOption<?>, Object> tcpChannelOptions() {
        Map<ChannelOption<?>, Object> options = new HashMap<ChannelOption<?>, Object>();
        options.put(ChannelOption.SO_KEEPALIVE, keepAlive);
        options.put(ChannelOption.SO_BACKLOG, backlog);

        return options;
    }

    @Bean(name = "bossGroup", destroyMethod = "shutdownGracefully")
    public EventLoopGroup bossGroup() {
        if (useEpoll()) {
            return new EpollEventLoopGroup(bossCount);
        } else {
            return new NioEventLoopGroup(bossCount);
        }
    }

    @Bean(name = "workerGroup", destroyMethod = "shutdownGracefully")
    public EventLoopGroup workerGroup() {
        if (useEpoll()) {
            return new EpollEventLoopGroup(bossCount);
        } else {
            return new NioEventLoopGroup(bossCount);
        }
    }

    @Bean(name = "tcpSocketAddress")
    public InetSocketAddress tcpPort() {
        return new InetSocketAddress(tcpPort);
    }

    private boolean useEpoll() {
        return "Linux".equals(systemOs);
    }
}
