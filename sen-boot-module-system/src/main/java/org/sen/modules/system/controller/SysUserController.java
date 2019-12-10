package org.sen.modules.system.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.sen.common.api.vo.Result;
import org.sen.common.util.OConvertUtils;
import org.sen.common.util.PasswordUtil;
import org.sen.modules.system.entity.SysUser;
import org.sen.modules.system.service.ISysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@Api("后台管理用户")
@RestController
@RequestMapping("/sys/user")
public class SysUserController {

    @Autowired
    private ISysUserService sysUserService;

    /**参数列表
     * username 用户名
     * password 密码
     * @param object
     * @return
     */
    @PostMapping("addNewSysUser")
    @ApiOperation("添加新的后台管理用户")
    public Result addNewSysUser(@RequestBody JSONObject object){
        Result result = new Result();
        if(object != null){
            SysUser sysUser = JSON.parseObject(object.toJSONString(), SysUser.class);
            sysUser.setUsername(sysUser.getUsername());
            String salt = OConvertUtils.randomGen(8);
            sysUser.setSalt(salt);
            String encryptPwd = PasswordUtil.encrypt(sysUser.getUsername(), sysUser.getPassword(), salt);
            sysUser.setPassword(encryptPwd);
            sysUser.setStatus(1);
            sysUser.setCreateBy("admin");
            sysUser.setCreateTime(LocalDateTime.now());
            sysUser.setUpdateBy("admin");
            sysUser.setUpdateTime(LocalDateTime.now());
            result = sysUserService.addNewSysUser(sysUser);
        }
        return result;
    }
}
