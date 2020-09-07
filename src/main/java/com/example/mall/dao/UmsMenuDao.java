package com.example.mall.dao;

import com.example.mall.dto.UmsMenu;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 后台用户菜单dao
 * @author hsqzs
 * date 2020/9/7 16:56
 */
@Repository
public interface UmsMenuDao {
    /**
     * 获取用户菜单list
     * @param adminId admin id
     * @return menu list
     */
    List<UmsMenu> getMenuList(Long adminId);
}
