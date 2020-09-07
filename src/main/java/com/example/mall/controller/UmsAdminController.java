package com.example.mall.controller;

import cn.hutool.core.collection.CollUtil;
import com.example.mall.common.api.CommonResult;
import com.example.mall.dto.UmsAdminLoginParam;
import com.example.mall.mbg.model.UmsAdmin;
import com.example.mall.mbg.model.UmsPermission;
import com.example.mall.mbg.model.UmsRole;
import com.example.mall.service.UmsAdminService;
import com.example.mall.service.UmsRoleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author hsqzs
 * date 2020/9/2 16:30
 */
@Api(tags = {"UmsAdminController"}, description = "后台用户管理")
@Controller
@RequestMapping(value = "/admin")
public class UmsAdminController {
    @Autowired
    private UmsAdminService adminService;
    @Autowired
    private UmsRoleService umsRoleService;
    @Value("${jwt.tokenHeader}")
    private String tokenHeader;
    @Value("${jwt.tokenHead}")
    private String tokenHead;

    @ApiOperation(value = "用户注册")
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult<UmsAdmin> register(@RequestBody UmsAdmin umsAdminParam, BindingResult result) {
        UmsAdmin umsAdmin = adminService.register(umsAdminParam);
        if (umsAdmin == null) {
            CommonResult.failed();
        }
        return CommonResult.success(umsAdmin);
    }

    @ApiOperation(value = "登录以后返回token")
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult login(@RequestBody UmsAdminLoginParam umsAdminLoginParam, BindingResult result) {
        String token = adminService.login(umsAdminLoginParam.getUsername(), umsAdminLoginParam.getPassword());
        if (token == null) {
            return CommonResult.validateFailed("用户名或密码错误");
        }
        Map<String, String> tokenMap = new HashMap<>(16);
        tokenMap.put("token", token);
        tokenMap.put("tokenHead", tokenHead);
        return CommonResult.success(tokenMap);
    }

    @ApiOperation("获取用户所有权限（包括+-权限）")
    @RequestMapping(value = "/permission/{adminId}", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<List<UmsPermission>> getPermissionList(@PathVariable Long adminId) {
        List<UmsPermission> permissionList = adminService.getPermissions(adminId);
        return CommonResult.success(permissionList);
    }

    @ApiOperation("获取登录用户的全部信息")
    @RequestMapping(value = "/info", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult info(Principal principal) {
        if (principal == null) {
            return CommonResult.unauthorized(null);
        }
        Map<String, Object> data = new HashMap<>(16);
        String username = principal.getName();
        UmsAdmin admin = adminService.getAdminByUserName(username);
        data.put("username", admin.getUsername());
        data.put("menus", umsRoleService.getMenuList(admin.getId()));
        data.put("icon", admin.getIcon());
        List<UmsRole> roleList = adminService.getRoleList(admin.getId());
        if(CollUtil.isNotEmpty(roleList)){
            List<String> roles = roleList.stream().map(UmsRole::getName).collect(Collectors.toList());
            data.put("roles",roles);
        }
        return CommonResult.success(data);
    }

    @ApiOperation("登出")
    @RequestMapping(value = "/logout", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult logout() {
        return CommonResult.success(null);
    }
}
