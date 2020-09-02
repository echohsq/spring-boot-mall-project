package com.example.mall.service;

import com.example.mall.mbg.model.UmsAdmin;
import com.example.mall.mbg.model.UmsPermission;

import java.util.List;


/**
 * 后台登陆管理service
 * @author hsqzs
 * date 2020/9/2 11:09
 */
public interface UmsAdminService {
    /**
     * 通过登录名获取后台管理员
     * @param name 用户名
     * @return UmsAdmin
     */
    UmsAdmin getAdminByUserName(String name);

    /**
     * 注册账号
     * @param umsAdminParam 用户参数
     * @return UmsAdmin
     */
    UmsAdmin register(UmsAdmin umsAdminParam);

    /**
     * 登录功能生成 token
     * @param username 用户名
     * @param password 密码
     * @return jwt token
     */
    String login(String username, String password);

    /**
     * 获取用户权限
     * @param adminId 管理用户id
     * @return 权限list
     */
    List<UmsPermission> getPermissions(Long adminId);
}
