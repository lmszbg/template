package org.sen.modules.system.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Param;
import org.sen.common.Enum.ResultEnum;
import org.sen.common.api.vo.Result;
import org.sen.common.constant.CommonConstant;
import org.sen.common.util.JwtUtil;
import org.sen.common.util.OConvertUtils;
import org.sen.common.util.PasswordUtil;
import org.sen.common.util.RedisUtil;
import org.sen.modules.dictionary.aop.annotation.RequestAopLog;
import org.sen.modules.system.entity.SysUser;
import org.sen.modules.system.service.ISysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@Api("后台管理用户")
@RestController
@RequestMapping("/sys/user")
@CrossOrigin
@Slf4j
public class SysUserController {

    @Autowired
    private ISysUserService sysUserService;
    @Autowired
    private RedisUtil redisUtil;

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
        redisUtil.set(CommonConstant.PREFIX_USER_TOKEN+token, token);
        redisUtil.expire(CommonConstant.PREFIX_USER_TOKEN+token, JwtUtil.EXPIRE_TIME*2/1000);
        JSONObject object = new JSONObject();
        object.put("token", token);
        result.setResult(object);
        return result;

    }

    @GetMapping("test")
    @ApiOperation("测试")
    @RequestAopLog
    public String test(){
        return "test";
    }

    @GetMapping("test1")
    @ApiOperation("测试1")
    @RequestAopLog
    public Result test1(@RequestParam("param1") String param1, @RequestParam("param2") String param2){
        Result<JSONObject> result = new Result<JSONObject>();
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("param1", param1);
        jsonObject.put("param2", param2);
        result.setResult(jsonObject);
        log.info(result.toString());
        return result;
    }

    @PostMapping("test2")
    @ApiOperation("测试2")
    @RequestAopLog
    public Result test2(@RequestBody JSONObject jsonObject){
        Result<JSONObject> result = new Result<JSONObject>();
        result.setResult(jsonObject);
        log.info(result.toString());
        return result;
    }
}
