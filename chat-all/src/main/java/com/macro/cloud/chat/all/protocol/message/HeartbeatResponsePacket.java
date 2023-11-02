package com.macro.cloud.chat.all.protocol.message;

import com.macro.cloud.chat.all.protocol.message.command.Command;

public class HeartbeatResponsePacket extends Packet {
    @Override
    public Byte getCommand() {
        return Command.HEARTBEAT_RESPONSE;
    }
}
