package com.leslia.util.exception;

import com.leslia.util.enums.EnumCode;

public class BaseException extends RuntimeException {


    private Integer errorCode;

    private String errorMessage;


    public BaseException(){
        super();
    }

    public BaseException(Exception e){
        super(e);
    }

    public BaseException(String message){
        super(message);
    }

    public BaseException(Integer errorCode,String errorMessage){
        super("errorCode:"+errorCode+",errorMessage:"+errorMessage);
        this.errorCode=errorCode;
        this.errorMessage=errorMessage;
    }

    public BaseException(EnumCode enumCode){
        super("errorCode:"+enumCode.getCode()+",errorMessage:"+enumCode.getMessage());
        this.errorCode=enumCode.getCode();
        this.errorMessage=enumCode.getMessage();
    }


    public Integer getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(Integer errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }



}
