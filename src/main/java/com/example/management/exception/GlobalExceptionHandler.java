package com.example.management.exception;

import com.example.management.entity.ResultBody;
import com.example.management.entity.enums.ResultEnum;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.io.IOException;
import java.sql.SQLException;

@RestControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    /**
     *捕获数据库操作异常
     */
    @ExceptionHandler(value = SQLException.class)
    public ResultBody SQLExceptionHandler(SQLException e){
        logger.error("数据库操作异常:",e.getMessage());
        return ResultBody.error(e.getErrorCode(),e.getMessage());
    }

    /**
     *捕获数据校验异常
     */
    @ExceptionHandler(value = BindException.class)
    public ResultBody bindExceptionHandler(BindException e){
        logger.error("数据校验异常",e.getMessage());
        //改进一下返回信息。
        return ResultBody.error(ResultEnum.UNSUPPORTED_MEDIA_TYPE);
    }
    /**
     *捕获空指针异常
     */
    @ExceptionHandler(value = NullPointerException.class)
    public ResultBody NullPointExceptionHandler(NullPointerException e){
        logger.error("空指针异常",e.getMessage());
        return ResultBody.error(ResultEnum.NULL_POINT);
    }

    /**
     * 捕获400异常
     */
    @ExceptionHandler(value = HttpMessageNotReadableException.class)
        public ResultBody  HttpMessageNotReadableExceptionHandler( HttpMessageNotReadableException e){
            logger.error("错误请求",e.getMessage());
            return ResultBody.error(ResultEnum.BAD_REQUEST);
    }

    /**
     * IO异常处理
     */
    @ExceptionHandler(value = IOException.class)
    public ResultBody IOExceptionHandler(IOException e){
        logger.error("读写异常",e.getMessage());
        return ResultBody.error(ResultEnum.IO_EXCEPTION);
    }

     /**
     *权限认证
     */
    @ExceptionHandler(value = AuthException.class)
    public ResultBody AuthExceptionHandler(AuthException e){
        logger.error("没有权限",e);
        return ResultBody.error(ResultEnum.NO_AUTH);
    }

}
