package com.study.studyspringboot.service;

import org.springframework.stereotype.Component;

/**
 * @Author xiaoyuqin
 * @Date 2020/4/1 11:08
 * @Description
 */
@Component
public class TestAspect {
    public static void main(String[] args) {
        MyUserService userService = new MyUserService();
        userService.test();
    }
}
