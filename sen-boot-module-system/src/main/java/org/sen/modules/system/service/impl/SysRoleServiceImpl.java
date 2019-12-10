package org.sen.modules.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import lombok.extern.slf4j.Slf4j;
import org.sen.common.api.vo.Result;
import org.sen.modules.system.entity.SysRole;
import org.sen.modules.system.mapper.SysRoleMapper;
import org.sen.modules.system.service.ISysRoleService;
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
public class SysRoleServiceImpl extends ServiceImpl<SysRoleMapper, SysRole> implements ISysRoleService {

    @Autowired
    private SysRoleMapper sysRoleMapper;

    @Override
    public Result addNewRole(SysRole sysRole){
        log.info("=======>>>添加新的角色");
        log.info("role:"+sysRole.toString());
        Integer flag = sysRoleMapper.selectCount(new LambdaQueryWrapper<SysRole>().eq(SysRole::getRoleCode, sysRole.getRoleCode()));
        if(flag != 0){
            log.info("======>>>添加失败，原因：已经存在该角色");
            return Result.fail5000("已经存在改角色");
        }
        sysRoleMapper.insert(sysRole);
        return Result.ok(sysRole);
    }
}
