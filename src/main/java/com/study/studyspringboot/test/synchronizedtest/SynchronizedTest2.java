package com.study.studyspringboot.test.synchronizedtest;

/**
 * 说明：
 *
 * @author xiaoyuqin
 * @date 2020/6/2 15:14
 */
public class SynchronizedTest2 {

    public synchronized void method1() {
        System.out.println("Method 1 start");
        try {
            System.out.println("Method 1 execute");
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Method 1 end");
    }

    public synchronized void method2() {
        System.out.println("Method 2 start");
        try {
            System.out.println("Method 2 execute");
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Method 2 end");
    }


    public static void main(String[] args) {
        final SynchronizedTest2 test1 = new SynchronizedTest2();
        final SynchronizedTest2 test2 = new SynchronizedTest2();

        new Thread(test1::method1).start();

        new Thread(test2::method2).start();
    }

}
