package org.sen.common.Enum;

public enum ResultEnum {

    //业务流程成功
    OK(2000, "操作成功"),
    //业务流程失败
    FAIL(5000, "操作失败");

    private Integer code;

    private String message;

    ResultEnum (Integer code, String message){
        this.code = code;
        this.message = message;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
