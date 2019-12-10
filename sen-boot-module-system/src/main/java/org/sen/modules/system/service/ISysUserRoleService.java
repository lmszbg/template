package org.sen.modules.system.service;

import org.sen.common.api.vo.Result;
import org.sen.modules.system.entity.SysUser;
import org.sen.modules.system.entity.SysUserRole;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author sen
 * @since 2019-12-04
 */
public interface ISysUserRoleService extends IService<SysUserRole> {


    Result addNewUserRole(String userId, String roleId);
}
