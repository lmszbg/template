package org.sen.modules.dictionary.controller;


import com.alibaba.fastjson.JSONObject;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.sen.common.Enum.ResultEnum;
import org.sen.common.api.vo.Result;
import org.sen.common.constant.CommonConstant;
import org.sen.common.util.JwtUtil;
import org.sen.common.util.PasswordUtil;
import org.sen.common.util.RedisUtil;
import org.sen.modules.dictionary.aop.annotation.RequestAopLog;
import org.sen.modules.system.entity.SysUser;
import org.sen.modules.system.service.ISysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author sen
 * @since 2019-12-04
 */
@RestController
@RequestMapping("/dictionary/test")
@Api("test")
public class TestController {

    @Autowired
    private ISysUserService sysUserService;
    @Autowired
    private RedisUtil redisUtil;

    @PostMapping("login")
    @ApiOperation("登录")
    public Result login(@RequestBody JSONObject jsonObject){
        Result result = new Result();
        String username = jsonObject.getString("username");
        SysUser sysUser = sysUserService.getSysUserByUsername(username);
        if(sysUser == null){
            result.setCode(ResultEnum.FAIL.getCode());
            result.setMessage("没有此用户");
            return result;
        }
        String password = jsonObject.getString("password");
        String encryptPwd = PasswordUtil.encrypt(username, password, sysUser.getSalt());
        if(!sysUser.getPassword().equals(encryptPwd)){
            result.setCode(ResultEnum.FAIL.getCode());
            result.setMessage("密码错误");
            return result;
        }
        String token = JwtUtil.sign(username, encryptPwd);
        // 设置token缓存有效时间
        redisUtil.set(CommonConstant.PREFIX_USER_TOKEN + token, token);
        redisUtil.expire(CommonConstant.PREFIX_USER_TOKEN + token, JwtUtil.EXPIRE_TIME*2 / 1000);
        JSONObject object = new JSONObject();
        object.put("token", token);
        result.setResult(object);
        return  result;
    }

    @GetMapping("test")
    @RequestAopLog
    public String test(){
        return "test";
    }
}
