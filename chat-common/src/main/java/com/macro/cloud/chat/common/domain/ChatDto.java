package com.macro.cloud.chat.common.domain;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class ChatDto {
    /**
     * 客户端ID 唯一
     */
    private String clientId;

    /**
     * 发送的消息
     */
    private String msg;
}
