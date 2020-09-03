package com.study.studyspringboot.test.thread;

import lombok.extern.slf4j.Slf4j;

/**
 * 说明：
 *
 * @author xiaoyuqin
 * @date 2020/5/26 17:25
 */
@Slf4j
public class Thread2 implements Runnable {

    private String name;

    public Thread2(String name) {
        this.name = name;
    }

    @Override
    public void run() {
        for (int i = 0; i < 5; i++) {

            log.info(name + "运行  :  " + i);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
