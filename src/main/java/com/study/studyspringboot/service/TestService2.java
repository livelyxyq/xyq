package com.study.studyspringboot.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 说明：
 *
 * @author xiaoyuqin
 * @date 2021/3/27 15:45
 */
@Service
public class TestService2 {

    @Autowired
    private TestService1 testService1;
}
