package org.sen.modules.system.service;

import org.sen.common.api.vo.Result;
import org.sen.modules.system.entity.SysPermission;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Set;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author sen
 * @since 2019-12-04
 */
public interface ISysPermissionService extends IService<SysPermission> {

    Result addNewPermission(SysPermission sysPermission);
}
