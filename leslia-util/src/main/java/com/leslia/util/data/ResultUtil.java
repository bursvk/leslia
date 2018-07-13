package com.leslia.util.data;


import static com.leslia.util.enums.EnumCode.*;

public class ResultUtil {


    public Result setData(){
        Result result=new Result<>();
        result.setCode(SUCCESS.getCode());
        result.setMessage(SUCCESS.getMessage());
        return result;
    }

    public <T> Result setData(T t){
        Result result=new Result<>();
        result.setCode(SUCCESS.getCode());
        result.setMessage(SUCCESS.getMessage());
        result.setData(t);
        return result;
    }

    public  Result setError(Integer code,String message){
        Result result=new Result<>();
        result.setCode(code);
        result.setMessage(message);
        return result;
    }

    public  Result setError(){
        Result result=new Result<>();
        result.setCode(ERROR.getCode());
        result.setMessage(ERROR.getMessage());
        return result;
    }



}
