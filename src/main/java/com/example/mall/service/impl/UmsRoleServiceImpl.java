package com.example.mall.service.impl;

import com.example.mall.dao.UmsMenuDao;
import com.example.mall.dto.UmsMenu;
import com.example.mall.service.UmsRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author hsqzs
 * date 2020/9/7 16:41
 */
@Service
public class UmsRoleServiceImpl implements UmsRoleService {
    @Autowired
    private UmsMenuDao umsMenuDao;
    @Override
    public List<UmsMenu> getMenuList(Long adminId) {
        return umsMenuDao.getMenuList(adminId);
    }
}
