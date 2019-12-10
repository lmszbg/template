package org.sen.modules.system.service;

import org.sen.common.api.vo.Result;
import org.sen.modules.system.entity.SysUser;
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
public interface ISysUserService extends IService<SysUser> {

    /**
     * 添加新的用户
     * @param sysUser
     * @return
     */
    Result<SysUser> addNewSysUser(SysUser sysUser);

    SysUser getSysUserByUsername(String username);

    Set<String> getUserRoles(String username);

    Set<String> getUserPermission(String username);
}
