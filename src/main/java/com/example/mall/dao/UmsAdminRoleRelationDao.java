package com.example.mall.dao;

import com.example.mall.mbg.model.UmsPermission;
import com.example.mall.mbg.model.UmsRole;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 后台用户与角色管理自定义Dao
 * @author hsqzs
 * date 2020/9/2 15:53
 */
@Repository
public interface UmsAdminRoleRelationDao {
    /**
     * 获取用户所有权限
     * @param adminId id
     * @return 权限表
     */
    List<UmsPermission> getPermissions(@Param("adminId") Long adminId);

    /**
     * 获取用户所有角色
     * @param adminId id
     * @return 角色列表
     */
    List<UmsRole> getRoleList(@Param("adminId") Long adminId);
}
