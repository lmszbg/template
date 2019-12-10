package org.sen.modules.system.service.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import lombok.extern.slf4j.Slf4j;
import org.sen.common.api.vo.Result;
import org.sen.modules.system.entity.SysUser;
import org.sen.modules.system.entity.SysUserRole;
import org.sen.modules.system.mapper.SysUserMapper;
import org.sen.modules.system.mapper.SysUserRoleMapper;
import org.sen.modules.system.service.ISysUserRoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author sen
 * @since 2019-12-04
 */
@Service
@DS("master")
@Slf4j
public class SysUserRoleServiceImpl extends ServiceImpl<SysUserRoleMapper, SysUserRole> implements ISysUserRoleService {

    @Autowired
    private SysUserRoleMapper sysUserRoleMapper;

    @Override
    public Result addNewUserRole(String userId, String roleId){
        log.info("==============>>>添加新的用户角色关系");
        log.info("userId: {}, roleId: {}", userId, roleId);
        Integer flag = sysUserRoleMapper.selectCount(new LambdaQueryWrapper<SysUserRole>()
                .eq(SysUserRole::getUserId, userId)
                .eq(SysUserRole::getRoleId, roleId));
        if(flag != 0){
            return Result.fail5000("已经存在该用户角色关系");
        }
        SysUserRole sysUserRole = new SysUserRole();
        sysUserRole.setUserId(userId);
        sysUserRole.setRoleId(roleId);
        sysUserRoleMapper.insert(sysUserRole);
        log.info("==================>>>>添加完成");
        return Result.ok(sysUserRole);
    }

}