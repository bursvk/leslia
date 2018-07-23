package com.leslia.util.enums;

public enum EnumCode {

    SUCCESS(200,"成功"),
    ERROR(500,"失败"),
    FAIL_1(501,"失败1");


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
