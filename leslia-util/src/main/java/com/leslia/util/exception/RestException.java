package com.leslia.util.exception;

import com.leslia.util.data.Result;
import com.leslia.util.data.ResultUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;


@ControllerAdvice
public class RestException {

    private Logger logger=LoggerFactory.getLogger(RestException.class);

    @ExceptionHandler
    @ResponseBody
    public Result RestException(Exception ex){
        logger.error("全局异常捕获");
        LogException.logger(logger,ex);
        Result result;
        if(ex instanceof BaseException){
            BaseException baseEx=(BaseException)ex;
            result=new ResultUtil().setError(baseEx.getErrorCode(),baseEx.getErrorMessage());
        }else{
            result=new ResultUtil().setError(-1,"未知错误");
        }
        return result;
    }


}
