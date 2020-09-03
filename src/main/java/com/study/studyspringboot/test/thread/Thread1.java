package com.study.studyspringboot.test.thread;

/**
 * 说明：
 *
 * @author xiaoyuqin
 * @date 2020/5/26 17:07
 */
public class Thread1 extends Thread {
    private String name;

    public Thread1(String name) {
        this.name = name;
    }

    @Override
    public void run() {
        for (int i = 0; i < 5; i++) {
            System.out.println(name + "运行  :  " + i);
            try {
                sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
