package com.leslia.util.enums;

public enum EnumCode {

    SUCCESS(200,"成功"),
    ERROR(500,"失败"),
    FAIL_1(501,"失败1"),
    FAIL_2(501,"失败2"),
    REDIS_LOCK_OVERTIME(10001,"获取redis锁超时"),
    REDIS_LOCK_REFAIL(100002,"redis解锁失败");


    private Integer code;

    private String message;

    private EnumCode(Integer code, String message){
        this.code=code;
        this.message=message;
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
