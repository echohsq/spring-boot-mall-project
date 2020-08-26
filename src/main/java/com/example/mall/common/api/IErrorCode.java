package com.example.mall.common.api;

/**
 * 错误码接口
 * @author hsqzs
 * date 2020/8/26 10:53
 */
public interface IErrorCode {
    /**
     * 获取状态码
     * @return long
     */
    Long getCode();

    /**
     * 获取信息
     * @return string
     */
    String getMessage();
}
