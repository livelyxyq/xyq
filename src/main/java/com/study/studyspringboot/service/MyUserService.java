package com.study.studyspringboot.service;

import org.springframework.stereotype.Service;

/**
 * @Author xiaoyuqin
 * @Date 2020/4/1 10:49
 * @Description
 */
@Service
public class MyUserService {

    public void test(){
        System.out.println("I am test");
    }

    public void testPushToErrorBranch() {
        System.out.printf("测试推送代码到错误的分支。");
    }

}
