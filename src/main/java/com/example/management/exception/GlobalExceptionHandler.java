package com.example.management.exception;

import com.example.management.entity.ResultBody;
import com.example.management.entity.ResultEnum;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import java.sql.SQLException;

@RestControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    /**
     *捕获数据库操作异常
     */
    @ExceptionHandler(value = SQLException.class)
    public ResultBody SQLExceptionHandler(SQLException e){
        logger.error("数据库操作异常:",e);
        return ResultBody.error(e.getErrorCode(),e.getMessage());
    }

    /**
     *捕获数据校验异常
     */
    @ExceptionHandler(value = BindException.class)
    public ResultBody bindExceptionHandler(BindException e){
        logger.error("数据校验异常",e);
        //改进一下返回信息。
        return ResultBody.error(415,e.getMessage());
    }

    /**
     *捕获空指针异常
     */
    @ExceptionHandler(value = NullPointerException.class)
    public ResultBody NullPointExceptionHandler(NullPointerException e){
        logger.error("空指针异常",e);
        return ResultBody.error(ResultEnum.NULL_POINT);
    }

}
