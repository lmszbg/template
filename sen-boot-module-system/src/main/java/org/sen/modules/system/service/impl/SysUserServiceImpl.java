package org.sen.modules.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import lombok.extern.slf4j.Slf4j;
import org.sen.common.Enum.ResultEnum;
import org.sen.common.api.vo.Result;
import org.sen.modules.system.entity.*;
import org.sen.modules.system.mapper.*;
import org.sen.modules.system.service.ISysPermissionService;
import org.sen.modules.system.service.ISysUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author sen
 * @since 2019-12-04
 */
@Service
@Slf4j
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser> implements ISysUserService {

    @Autowired
    private SysUserMapper sysUserMapper;
    @Autowired
    private SysUserRoleMapper sysUserRoleMapper;
    @Autowired
    private SysRoleMapper sysRoleMapper;
    @Autowired
    private SysPermissionMapper sysPermissionMapper;
    @Autowired
    private SysRolePermissionMapper sysRolePermissionMapper;
    @Autowired
    private ISysPermissionService sysPermissionService;

    @Override
    public Result<SysUser> addNewSysUser(SysUser sysUser){
        log.info("============>添加新用户");
        log.info("新用户信息："+sysUser.toString());
        Result result = new Result();
        SysUser oldUser = sysUserMapper.selectOne(new LambdaQueryWrapper<SysUser>().eq(SysUser::getUsername, sysUser.getUsername()));
        if(oldUser != null){
            result.setMessage("已存在此用户");
            log.info("已存在此用户");
            log.info("用户信息："+oldUser.toString());
            result.setCode(ResultEnum.FAIL.getCode());
            return result;
        }
        sysUserMapper.insert(sysUser);
        result.setResult(sysUser);
        log.info("===========>添加新用户成功");
        return result;
    }

    @Override
    public SysUser getSysUserByUsername(String username){
        return sysUserMapper.selectOne(new LambdaQueryWrapper<SysUser>().eq(SysUser::getUsername, username));
    }

    @Override
    public Set<String> getUserRoles(String username){
        SysUser sysUser = getSysUserByUsername(username);
        List<SysUserRole> sysUserRoleList = sysUserRoleMapper.selectList(new LambdaQueryWrapper<SysUserRole>().eq(SysUserRole::getUserId, sysUser.getId()));
        Set<String> roleSet = new HashSet<>();
        sysUserRoleList.stream().forEach(sysUserRole -> {
            SysRole sysRole = sysRoleMapper.selectById(sysUserRole.getRoleId());
            roleSet.add(sysRole.getRoleCode());
        });
        return roleSet;
    }

    @Override
    public Set<String> getUserPermission(String username){
        SysUser sysUser = getSysUserByUsername(username);
        List<SysUserRole> sysUserRoleList = sysUserRoleMapper.selectList(new LambdaQueryWrapper<SysUserRole>().eq(SysUserRole::getUserId, sysUser.getId()));
        List<SysRole> roleList = new ArrayList<>();
        sysUserRoleList.stream().forEach(sysUserRole -> {
            SysRole sysRole = sysRoleMapper.selectById(sysUserRole.getRoleId());
            roleList.add(sysRole);
        });
        Set<String> sysPermission = new HashSet<>();
        roleList.stream().forEach(sysRole -> {
            List<SysRolePermission> sysRolePermissions = sysRolePermissionMapper.selectList(new LambdaQueryWrapper<SysRolePermission>().eq(SysRolePermission::getRoleId, sysRole.getId()));
            sysRolePermissions.stream().forEach(sysRolePermission -> {
                SysPermission permission = sysPermissionMapper.selectById(sysRolePermission.getPermissionId());
                sysPermission.add(permission.getUrl());
            });
        });
        return sysPermission;
    }
}
