package org.sen.modules.system.controller;


import com.alibaba.fastjson.JSONObject;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.sen.common.api.vo.Result;
import org.sen.modules.system.entity.SysPermission;
import org.sen.modules.system.entity.SysRole;
import org.sen.modules.system.service.ISysPermissionService;
import org.sen.modules.system.service.ISysRolePermissionService;
import org.sen.modules.system.service.ISysRoleService;
import org.sen.modules.system.service.ISysUserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api("角色权限管理")
@RestController
@RequestMapping("/sys/role")
public class SysRolePermissionController {

    @Autowired
    @Lazy
    private ISysRoleService sysRoleService;
    @Autowired
    @Lazy
    private ISysPermissionService sysPermissionService;
    @Autowired
    @Lazy
    private ISysRolePermissionService rolePermissionService;
    @Autowired
    private ISysUserRoleService userRoleService;

    @PostMapping("addNewRole")
    @ApiOperation("添加角色")
    public Result addNewRole(@RequestBody SysRole sysRole){
        if(sysRole.getRoleName() == null || "".equals(sysRole.getRoleName()) || sysRole.getRoleCode() == null || "".equals(sysRole.getRoleCode())){
            return Result.fail5000("数据不齐全");
        }
        Result result = sysRoleService.addNewRole(sysRole);
        return result;
    }

    @PostMapping("addNewPermission")
    @ApiOperation("添加权限")
    public Result addNewPermission(@RequestBody SysPermission sysPermission){
        if(sysPermission.getUrl() == null || "".equals(sysPermission.getUrl()) || sysPermission.getName() == null || "".equals(sysPermission.getName())){
            return Result.fail5000("数据不齐全");
        }
        Result result = sysPermissionService.addNewPermission(sysPermission);
        return result;
    }

    @PostMapping("addRolePermission")
    @ApiOperation("添加角色权限关系")
    public Result addRolePermission(@RequestBody JSONObject jsonObject){
        String roleId = jsonObject.getString("roleId");
        String permissionId = jsonObject.getString("permissionId");
        if(roleId == null || permissionId == null){
            return Result.fail5000("参数不齐全");
        }
        Result result = rolePermissionService.addNewRolePermission(roleId, permissionId);
        return result;
    }

    @PostMapping("addUserRole")
    @ApiOperation("添加用户角色关系")
    public Result addUserRole(@RequestBody JSONObject jsonObject){
        String userId = jsonObject.getString("userId");
        String roleId = jsonObject.getString("roleId");
        if(userId == null || roleId == null){
            return Result.fail5000("参数不齐全");
        }
        Result result = userRoleService.addNewUserRole(userId, roleId);
        return result;
    }
}
