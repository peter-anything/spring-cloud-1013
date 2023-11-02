package com.macro.cloud.chat.all.protocol.message;

import lombok.Data;

import static com.macro.cloud.chat.all.protocol.message.command.Command.HEARTBEAT_REQUEST;

@Data
public class HeartbeatRequestPacket extends Packet {

    @Override
    public Byte getCommand() {
        return HEARTBEAT_REQUEST;
    }
}

