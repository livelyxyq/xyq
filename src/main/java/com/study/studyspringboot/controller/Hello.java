package com.study.studyspringboot.controller;

import com.study.studyspringboot.service.AsyncService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

/**
 * 说明：
 *
 * @author xiaoyuqin
 * @date 2020/6/3 16:09
 */
@Slf4j
@AllArgsConstructor
@RestController
public class Hello {

    private AsyncService asyncService;

    @RequestMapping("/")
    public String submit() {

        log.info("start submit");

        // 调用service层的任务
        asyncService.executeAsync();

        log.info("end submit");

        return "success";
    }

    @GetMapping("/test")
    public String test(@RequestHeader Integer userId, @RequestParam String name) {

        log.info("测试请求。user={}，name={}", userId, name);
        return "test success";
    }
}
