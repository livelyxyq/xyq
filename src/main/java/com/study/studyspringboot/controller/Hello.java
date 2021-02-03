package com.study.studyspringboot.controller;

import com.study.studyspringboot.config.SettingProperties;
import com.study.studyspringboot.service.AsyncService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import java.util.List;

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
    private SettingProperties settingProperties;

    @PostConstruct
    public void init() {
        log.info("我执行了init()方法。");
    }

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

    @PostMapping("/test2")
    public String test2(@RequestParam("name") String name, String age, Integer userId) {

        log.info("测试请求。age={}，name={}，user={}", age, name, userId);
        return "test success";
    }

    @GetMapping("/test3")
    public String test3() {
        SettingProperties.User user = settingProperties.getUser();
        log.info("user={}", user);

        List<Integer> labelIdList = settingProperties.getLabelIdList();
        log.info("labelIdList={}", labelIdList);

        return "success";
    }
}
