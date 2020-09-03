package com.study.studyspringboot.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

/**
 * 说明：
 *
 * @author xiaoyuqin
 * @date 2020/6/3 16:08
 */
@Slf4j
@Service
public class AsyncService {

    /**
     * 执行异步任务
     */
    @Async("asyncServiceExecutor")
    public void executeAsync() {

        log.info("start executeAsync");
        try {
            Thread.sleep(5000);
        } catch (Exception e) {
            e.printStackTrace();
        }

        log.info("end executeAsync");
    }

}
