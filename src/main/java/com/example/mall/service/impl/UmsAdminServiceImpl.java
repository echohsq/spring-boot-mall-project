package com.example.mall.service.impl;

import com.example.mall.common.untils.JwtTokenUntil;
import com.example.mall.dao.UmsAdminRoleRelationDao;
import com.example.mall.mbg.mapper.UmsAdminMapper;
import com.example.mall.mbg.model.UmsAdmin;
import com.example.mall.mbg.model.UmsAdminExample;
import com.example.mall.mbg.model.UmsPermission;
import com.example.mall.mbg.model.UmsRole;
import com.example.mall.service.UmsAdminService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * 后台登陆管理
 * @author hsqzs
 * date 2020/9/2 11:24
 */
@Service
public class UmsAdminServiceImpl implements UmsAdminService {
    private static final Logger LOGGER = LoggerFactory.getLogger(UmsAdminServiceImpl.class);
    @Autowired
    UmsAdminMapper umsAdminMapper;
    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    JwtTokenUntil jwtTokenUntil;
    @Autowired
    UmsAdminService umsAdminService;
    @Autowired
    UserDetailsService userDetailsService;
    @Autowired
    UmsAdminRoleRelationDao umsAdminRoleRelationDao;

    /**
     * 通过登录名获取后台管理员
     *
     * @param username 用户名
     * @return UmsAdmin
     */
    @Override
    public UmsAdmin getAdminByUserName(String username) {
        UmsAdminExample umsAdminExample = new UmsAdminExample();
        umsAdminExample.createCriteria().andUsernameEqualTo(username);
        List<UmsAdmin> umsAdmins = umsAdminMapper.selectByExample(umsAdminExample);
        if (umsAdmins != null && umsAdmins.size() > 0) {
            return umsAdmins.get(0);
        }
        return null;
    }

    /**
     * 注册账号
     *
     * @param umsAdminParam 用户参数
     * @return UmsAdmin
     */
    @Override
    public UmsAdmin register(UmsAdmin umsAdminParam) {
        UmsAdmin umsAdmin = new UmsAdmin();
        BeanUtils.copyProperties(umsAdminParam, umsAdmin);
        umsAdmin.setCreateTime(new Date());
        umsAdmin.setStatus(1);
        //查询是否有相同用户名的用户
        UmsAdminExample example = new UmsAdminExample();
        example.createCriteria().andUsernameEqualTo(umsAdmin.getUsername());
        List<UmsAdmin> umsAdminList = umsAdminMapper.selectByExample(example);
        if (umsAdminList.size() > 0) {
            return null;
        }
        //将密码进行加密操作
        String encodePassword = passwordEncoder.encode(umsAdmin.getPassword());
        umsAdmin.setPassword(encodePassword);
        umsAdminMapper.insert(umsAdmin);
        return umsAdmin;
    }

    /**
     * 登录功能生成 token
     *
     * @param username 用户名
     * @param password 密码
     * @return jwt token
     */
    @Override
    public String login(String username, String password) {
        String token = null;
        try {
            UserDetails userDetails = userDetailsService.loadUserByUsername(username);
            if (! passwordEncoder.matches(password, userDetails.getPassword())) {
                throw new BadCredentialsException("密码不正确");
            }
            UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(authentication);
            token = jwtTokenUntil.generateToken(userDetails);
        } catch (AuthenticationException e) {
            LOGGER.warn("登录异常: {}", e.getMessage());
        }
        return token;
    }

    /**
     * 获取用户权限
     *
     * @param adminId 管理用户id
     * @return 权限list
     */
    @Override
    public List<UmsPermission> getPermissions(Long adminId) {
        return umsAdminRoleRelationDao.getPermissions(adminId);
    }

    /**
     * 获取用户角色
     *
     * @param adminId 管理用户id
     * @return 角色list
     */
    @Override
    public List<UmsRole> getRoleList(Long adminId) {
        return umsAdminRoleRelationDao.getRoleList(adminId);
    }
}
