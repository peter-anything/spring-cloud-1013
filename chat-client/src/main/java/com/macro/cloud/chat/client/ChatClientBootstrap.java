package com.macro.cloud.chat.client;

import com.alibaba.fastjson2.JSON;
import com.macro.cloud.chat.common.domain.ChatDto;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.serialization.ClassResolvers;
import io.netty.handler.codec.serialization.ObjectDecoder;
import io.netty.handler.codec.serialization.ObjectEncoder;
import io.netty.handler.timeout.IdleStateHandler;
import io.netty.util.concurrent.DefaultEventExecutorGroup;
import io.netty.util.concurrent.EventExecutorGroup;

public class ChatClientBootstrap {
    private int port;
    private String host;
    public SocketChannel socketChannel;
    private static final EventExecutorGroup group = new DefaultEventExecutorGroup(20);

    public ChatClientBootstrap(int port, String host) throws InterruptedException {
        this.port = port;
        this.host = host;
        start();
    }

    private void start() throws InterruptedException {
        EventLoopGroup eventLoopGroup = new NioEventLoopGroup();
        Bootstrap bootstrap = new Bootstrap();
        try {
            bootstrap.group(eventLoopGroup)
                    .channel(NioSocketChannel.class)
                    .option(ChannelOption.SO_KEEPALIVE, true)
                    .remoteAddress(host, port)
                    .handler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel socketChannel) throws Exception {
                            /**
                             *  0 表示禁用
                             * readerIdleTime读空闲超时时间设定，如果channelRead()方法超过readerIdleTime时间未被调用则会触发超时事件调用userEventTrigger()方法；
                             *
                             * writerIdleTime写空闲超时时间设定，如果write()方法超过writerIdleTime时间未被调用则会触发超时事件调用userEventTrigger()方法；
                             *
                             * allIdleTime所有类型的空闲超时时间设定，包括读空闲和写空闲；
                             */
                            socketChannel.pipeline().addLast(new IdleStateHandler(20, 10, 0));
                            socketChannel.pipeline().addLast(new ObjectEncoder());
                            socketChannel.pipeline().addLast(new ObjectDecoder(ClassResolvers.cacheDisabled(null)));
                            socketChannel.pipeline().addLast(new ChatClientHandler());
                        }
                    });
            ChannelFuture future = bootstrap.connect(host, port).sync();
            if (future.isSuccess()) {
                socketChannel = (SocketChannel) future.channel();
                System.out.println("connect server  成功---------");
            }
            ChatDto dto=new ChatDto();
            dto.setClientId("123123").setMsg("test hello");
            /**
             * json字符串发送xx
             */
            this.socketChannel.writeAndFlush(JSON.toJSONString(dto));
            //给关闭通道进行监听
            future.channel().closeFuture().sync();
        } finally {
            eventLoopGroup.shutdownGracefully();
        }
    }
}
