package org.sen.modules.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import lombok.extern.slf4j.Slf4j;
import org.sen.common.api.vo.Result;
import org.sen.modules.system.entity.SysRolePermission;
import org.sen.modules.system.mapper.SysRolePermissionMapper;
import org.sen.modules.system.service.ISysRolePermissionService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
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
@Slf4j
public class SysRolePermissionServiceImpl extends ServiceImpl<SysRolePermissionMapper, SysRolePermission> implements ISysRolePermissionService {

    @Autowired
    private SysRolePermissionMapper sysRolePermissionMapper;

    @Override
    public Result addNewRolePermission(String roleId, String permissionId){
        log.info("==============>>>添加新的角色权限关系");
        log.info("roleId: {}, permission: {}", roleId, permissionId);
        Integer flag = sysRolePermissionMapper.selectCount(new LambdaQueryWrapper<SysRolePermission>()
                .eq(SysRolePermission::getPermissionId, permissionId)
                .eq(SysRolePermission::getRoleId, roleId));
        if(flag != 0){
            return Result.fail5000("已经存在该角色权限关系");
        }
        SysRolePermission sysRolePermission = new SysRolePermission();
        sysRolePermission.setRoleId(roleId);
        sysRolePermission.setPermissionId(permissionId);
        sysRolePermissionMapper.insert(sysRolePermission);
        log.info("==================>>>>添加完成");
        return Result.ok(sysRolePermission);
    }

}
