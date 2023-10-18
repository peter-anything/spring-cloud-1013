package com.macro.cloud.common.domain;

//import io.swagger.annotations.ApiModel;
//import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 统一Controller返回的json数据格式
 * @author baobao
 */
@Data
@AllArgsConstructor
@Accessors(chain = true)
//@ApiModel("请求响应对象")
public class Response<T> {
//    @ApiModelProperty(value = "是否成功")
    private Boolean success;

//    @ApiModelProperty(value = "返回消息")
    private String message;

//    @ApiModelProperty(value = "返回数据")
    private T data;

    /**
     * 把构造方法私有
     */
    private Response() {}

    /**
     * 返回成功消息
     * @param message  返回内容
     * @param data 数据对象
     * @return 成功消息
     */
    public static <T> Response<T> success(String message, T data) {
        return new Response<>(true, message, data);
    }

    /**
     * 返回成功消息
     * @param message 返回内容
     * @return 成功消息
     */
    public static Response<Void> success(String message) {
        return Response.success(message, null);
    }

    /**
     * 返回成功消息
     * @param data 数据
     * @return 成功消息
     */
    public static <T> Response<T> success(T data) {
        return Response.success("操作成功", data);
    }

    /**
     * 返回成功消息
     * @return 成功消息
     */
    public static Response<Void> success() {
        return Response.success("操作成功");
    }

    /**
     * 返回错误消息
     * @param message  返回内容
     * @param data 数据
     * @return 错误消息
     */
    public static <T> Response<T> error(String message, T data) {
        return new Response<>(false, message, data);
    }

    /**
     * 返回错误消息
     * @param message 消息内容
     * @return 错误消息
     */
    public static Response<Void> error(String message) {
        return Response.error(message, null);
    }

    /**
     * 返回错误消息
     * @return 错误消息
     */
    public static Response<Void> error() {
        return Response.error("操作失败");
    }
}
