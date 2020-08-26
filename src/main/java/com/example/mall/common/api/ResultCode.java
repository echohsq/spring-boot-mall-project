package com.example.mall.common.api;

/**
 * 枚举常用api操作码
 * @author hsqzs
 * date 2020/8/26 10:55
 */
public enum ResultCode implements IErrorCode{

    /**
     *    SUCCESS(200, "操作成功"),
     *    FAILED(500, "操作失败"),
     *    VALIDATE_FAILED(404, "参数检验失败"),
     *    UNAUTHORIZED(401, "暂未登录或token已经过期"),
     *    FORBIDDEN(403, "没有相关权限")
     */
    SUCCESS(200, "操作成功"),
    FAILED(500, "操作失败"),
    VALIDATE_FAILED(404, "参数检验失败"),
    UNAUTHORIZED(401, "暂未登录或token已经过期"),
    FORBIDDEN(403, "没有相关权限");

    private long code;
    private String message;

    ResultCode(long code, String message) {
        this.code = code;
        this.message = message;
    }

    /**
     * 获取状态码
     *
     * @return long
     */
    @Override
    public Long getCode() {
        return code;
    }

    /**
     * 获取信息
     *
     * @return string
     */
    @Override
    public String getMessage() {
        return message;
    }
}
