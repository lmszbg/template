package org.sen.modules.system.service;

import org.sen.common.api.vo.Result;
import org.sen.modules.system.entity.SysRolePermission;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author sen
 * @since 2019-12-04
 */
public interface ISysRolePermissionService extends IService<SysRolePermission> {

    Result addNewRolePermission(String roleId, String permissionId);
}
