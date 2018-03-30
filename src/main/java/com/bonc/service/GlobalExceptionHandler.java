package com.bonc.service;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import com.bonc.util.JsonResult;


@ControllerAdvice
public class GlobalExceptionHandler {

    /**
     * 系统异常处理，比如：404,500
     * @param req
     * @param resp
     * @param e
     * @return
     * @throws Exception
     */
    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public ResponseEntity<Object> defaultErrorHandler(HttpServletRequest req, Exception e) throws Exception {
        
    	ResponseEntity<Object> r = null;
        if (e instanceof org.springframework.web.servlet.NoHandlerFoundException) {
             r = new ResponseEntity<Object>(JsonResult.makeErrorJson(0, "请求接口不存在").toString(),HttpStatus.NOT_FOUND);
        }else if (e instanceof org.springframework.web.bind.MissingServletRequestParameterException){
        	 r = new ResponseEntity<Object>(JsonResult.makeErrorJson(0, "请求参数异常！").toString(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
        else {
        	 r = new ResponseEntity<Object>(JsonResult.makeErrorJson(0, "请求接口异常！").toString(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return r;
    }
}