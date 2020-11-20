package com.study.studyspringboot.advice;

import com.study.studyspringboot.exception.ErrorCode;
import com.study.studyspringboot.util.ExceptionUtil;
import com.study.studyspringboot.vo.ResponseResult;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.ValidationException;
import java.util.Set;

@ControllerAdvice(annotations = {RestController.class, Controller.class})
public class ExceptionAdvice {

    @ExceptionHandler
    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<?> handle(ValidationException exception) {

        StringBuilder message = new StringBuilder();
        if (exception instanceof ConstraintViolationException) {
            ConstraintViolationException exs = (ConstraintViolationException) exception;
            Set<ConstraintViolation<?>> violations = exs.getConstraintViolations();

            for (ConstraintViolation<?> violation : violations) {
                message.append(violation.getMessage()).append(",");
            }
        }

        int length = message.length();
        return new ResponseEntity<>(new ResponseResult(ErrorCode.ERR_PARAM_WRONG.getResult(),
                length > 1 ? message.toString().substring(0, length - 1) : message.toString()),
                HttpStatus.BAD_REQUEST);
    }

    // valid exception
    @ExceptionHandler({MethodArgumentNotValidException.class, BindException.class})
    @ResponseBody
    public <T extends Exception> ResponseEntity<?> handleMethodArgumentNotValidException(T ex) {

        StringBuilder message = new StringBuilder();

        // 拼接异常信息
        ExceptionUtil.appendExceptionInfo(ex, message);

        int length = message.length();
        return new ResponseEntity<>(new ResponseResult<>(ErrorCode.ERR_PARAM_NULL.getResult(),
                length > 1 ? message.toString().substring(0, length - 1) : message.toString()),
                HttpStatus.OK);
    }

}