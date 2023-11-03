package com.macro.cloud.chat.all.client;

import com.macro.cloud.chat.all.protocol.protobuf.MessageBase;
import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.handler.codec.protobuf.ProtobufDecoder;
import io.netty.handler.codec.protobuf.ProtobufEncoder;
import io.netty.handler.codec.protobuf.ProtobufVarint32FrameDecoder;
import io.netty.handler.codec.protobuf.ProtobufVarint32LengthFieldPrepender;

public class ClientHandlerInitializer extends ChannelInitializer<Channel> {
    private ChatClient chatClient;

    public ClientHandlerInitializer(ChatClient chatClient) {
        this.chatClient = chatClient;
    }

    @Override
    protected void initChannel(Channel ch) throws Exception {
        HeartbeatHandler heartbeatHandler = new HeartbeatHandler(this.chatClient);
        ch.pipeline()
                .addLast(new ClientIdleStateHandler())
                .addLast(new ProtobufVarint32FrameDecoder())
                .addLast(new ProtobufDecoder(MessageBase.Message.getDefaultInstance()))
                .addLast(new ProtobufVarint32LengthFieldPrepender())
                .addLast(new ProtobufEncoder())
                .addLast(heartbeatHandler)
                .addLast(new ChatClientHandler());
    }
}
