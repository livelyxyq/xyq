package com.study.studyspringboot.controller;

import com.study.studyspringboot.service.MyUserService;
import com.study.studyspringboot.vo.UserAddReq;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * @Author xiaoyuqin
 * @Date 2020/4/1 14:03
 * @Description
 */
@Slf4j
@RestController
@RequestMapping("user")
public class UserController {
    @Autowired
    private MyUserService userService;

    @GetMapping("/test")
    public String test() {
        userService.test();
        return "test";
    }

    @PostMapping("/add")
    public String addUser(@RequestBody @Validated UserAddReq userAddReq, BindingResult bindingResult) throws BindException {
        log.info("请求参数。{}", userAddReq);

        if (bindingResult.hasErrors()) {
            throw new BindException(bindingResult);
        }

        return "success";
    }

    public static void main(String[] args) {
        int num = 30;
        Integer n = 30;
        if (num < n) {
            System.out.println("min");
        } else {
            System.out.println("not min");
        }
    }

}
