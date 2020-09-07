package com.example.mall.service;

import com.example.mall.dto.UmsMenu;

import java.util.List;

/**
 * 后台角色管理service
 * @author hsqzs
 * date 2020/9/7 16:19
 */
public interface UmsRoleService {
    /**
     * 获得菜单列表
     * @param adminId id
     * @return 菜单列表
     */
    List<UmsMenu> getMenuList(Long adminId);
}
