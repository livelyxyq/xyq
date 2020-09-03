package com.study.studyspringboot.test.thread;

import lombok.extern.slf4j.Slf4j;

/**
 * 说明：
 *
 * @author xiaoyuqin
 * @date 2020/5/26 10:48
 */
@Slf4j
public class Test {

    public static void main(String[] args) {
//        Thread1 mTh1 = new Thread1("A");
//        Thread1 mTh2 = new Thread1("B");
//        mTh1.start();
//        mTh2.start();

//        new Thread(new Thread2("C")).start();
//        new Thread(new Thread2("D")).start();
//
//        log.info("start...");

        int y = test2(10);

        Thread t = new Thread() {

            @Override
            public void run() {
                test2(20);
            }
        };

        t.setName("t1");
        t.start();
    }

    public static int test2(int x) {
        int y = x++;
        System.out.println(y);
        return y;
    }

    public static void test1() {

        new Thread(() -> {
            while (true) {
                try {
                    Thread.sleep(500);
                } catch (Exception e) {
                    log.error(e.toString());
                }

                log.info("running...");
            }
        }, "t1").start();

        new Thread(() -> {
            while (true) {
                try {
                    Thread.sleep(500);
                } catch (Exception e) {
                    log.error(e.toString());
                }
                log.info("running...");
            }
        }, "t2").start();

    }
}
