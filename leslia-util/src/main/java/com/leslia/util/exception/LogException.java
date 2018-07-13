package com.leslia.util.exception;

import org.slf4j.Logger;

import java.io.PrintWriter;
import java.io.StringWriter;


/**
 * 异常-日志
 */
public class LogException {

    public static void logger(Logger logger,BaseException e){
        StringWriter trace=new StringWriter();
        e.printStackTrace(new PrintWriter(trace));
        logger.error(trace.toString());
    }

    public static void logger(Logger logger,Exception e){
        StringWriter trace=new StringWriter();
        e.printStackTrace(new PrintWriter(trace));
        logger.error(trace.toString());
    }

}
