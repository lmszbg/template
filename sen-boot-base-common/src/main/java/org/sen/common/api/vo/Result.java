package org.sen.common.api.vo;

import lombok.Data;
import org.sen.common.Enum.ResultEnum;

import java.io.Serializable;

@Data
public class Result<T> implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 成功标志
     */
    private boolean success = true;

    /**
     * 返回处理消息
     */
    private String message = "操作成功！";

    /**
     * 返回代码
     */
    private Integer code = 2000;

    private T result;

    public Result() {

    }

    public Result<T> success(ResultEnum resultEnum){
        this.code = resultEnum.getCode();
        this.message = resultEnum.getMessage();
        return this;
    }

    public Result<T> fail(ResultEnum resultEnum){
        this.code = resultEnum.getCode();
        this.message = resultEnum.getMessage();
        this.success = false;
        return this;
    }

    public Result<T> success(String message) {
        this.message = message;
        this.code = ResultEnum.OK.getCode();
        this.success = true;
        return this;
    }


    public static Result<Object> ok() {
        Result<Object> r = new Result<Object>();
        r.setSuccess(true);
        r.setCode(ResultEnum.OK.getCode());
        r.setMessage("成功");
        return r;
    }

    public static Result<Object> ok(String msg) {
        Result<Object> r = new Result<Object>();
        r.setSuccess(true);
        r.setCode(ResultEnum.OK.getCode());
        r.setMessage(msg);
        return r;
    }

    public static Result<Object> ok(Object data) {
        Result<Object> r = new Result<Object>();
        r.setSuccess(true);
        r.setCode(ResultEnum.OK.getCode());
        r.setResult(data);
        return r;
    }

    public static Result<Object> error(String msg) {
        return error(ResultEnum.FAIL.getCode(), msg);
    }

    public static Result<Object> error(int code, String msg) {
        Result<Object> r = new Result<Object>();
        r.setCode(code);
        r.setMessage(msg);
        r.setSuccess(false);
        return r;
    }

    public static Result<Object> fail5000(String message) {
        Result<Object> r = new Result<Object>();
        r.setCode(ResultEnum.FAIL.getCode());
        r.setMessage(message);
        r.setSuccess(false);
        return r;
    }
}
