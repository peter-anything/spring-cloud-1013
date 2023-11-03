package com.macro.cloud.chat.all.client;

import com.macro.cloud.chat.all.protocol.protobuf.MessageBase;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.EventLoop;
import io.netty.handler.timeout.IdleState;
import io.netty.handler.timeout.IdleStateEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

@Slf4j
public class HeartbeatHandler extends ChannelInboundHandlerAdapter {
    private final ChatClient chatClient;

    public HeartbeatHandler(ChatClient nettyClient) {
        System.out.println("HeartbeatHandler ======");
        this.chatClient = nettyClient;
    }

    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
        System.out.println("userEventTriggered ======");
        if (evt instanceof IdleStateEvent) {
            IdleStateEvent idleStateEvent = (IdleStateEvent) evt;
            if (idleStateEvent.state() == IdleState.WRITER_IDLE) {
                log.info("已经10s没有发送消息给服务端");
                //向服务端送心跳包
                MessageBase.Message heartbeat = MessageBase.Message.newBuilder().setCmd(MessageBase.Message.CommandType.HEARTBEAT_REQUEST)
                        .setRequestId(UUID.randomUUID().toString())
                        .setContent("heartbeat").build();
                //发送心跳消息，并在发送失败时关闭该连接
                ctx.writeAndFlush(heartbeat).addListener(ChannelFutureListener.CLOSE_ON_FAILURE);
            }
        } else {
            super.userEventTriggered(ctx, evt);
        }
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        System.out.println("channel read : " + msg);
        super.channelRead(ctx, msg);
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        //如果运行过程中服务端挂了,执行重连机制
        System.out.println("channelActive ======");
        super.channelActive(ctx);
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        //如果运行过程中服务端挂了,执行重连机制
        System.out.println("channelInactive ======");
        EventLoop eventLoop = ctx.channel().eventLoop();
        eventLoop.schedule(() -> chatClient.start(), 10L, TimeUnit.SECONDS);
        super.channelInactive(ctx);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        log.error("捕获的异常：{}",cause.getMessage());
        ctx.channel().close();
    }
}
