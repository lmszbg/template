package org.sen.modules.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import lombok.extern.slf4j.Slf4j;
import org.sen.common.api.vo.Result;
import org.sen.modules.system.entity.SysPermission;
import org.sen.modules.system.mapper.SysPermissionMapper;
import org.sen.modules.system.service.ISysPermissionService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
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
public class SysPermissionServiceImpl extends ServiceImpl<SysPermissionMapper, SysPermission> implements ISysPermissionService {

    @Autowired
    private SysPermissionMapper sysPermissionMapper;

    @Override
    public Result addNewPermission(SysPermission sysPermission){
        log.info("============>>>>>添加新的权限");
        log.info("permission:"+sysPermission.toString());
        Integer flag = sysPermissionMapper.selectCount(new LambdaQueryWrapper<SysPermission>().eq(SysPermission::getUrl, sysPermission.getUrl()));
        if(flag != 0){
            log.info("===========>>>添加权限失败，原因：已经存在此路径");
            return Result.fail5000("添加失败，已经存在此路径");
        }
        sysPermissionMapper.insert(sysPermission);
        return Result.ok(sysPermission);
    }
}
