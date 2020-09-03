package com.study.studyspringboot.test.synchronizedtest;

/**
 * 说明：
 *
 * @author xiaoyuqin
 * @date 2020/6/2 15:07
 */
public class SynchronizedTest implements Runnable {

//    /**
//     * 共享资源
//     */
//    static int i = 0;
//
//    /**
//     * synchronized 修饰实例方法
//     */
//    public static void increase() {
//        i++;
//    }
//
//    @Override
//    public void run() {
//        for (int j = 0; j < 10000; j++) {
//
//            increase();
//        }
//    }
//
//    public static void main(String[] args) throws InterruptedException {
//
//        SynchronizedTest test1 = new SynchronizedTest();
//        SynchronizedTest test2 = new SynchronizedTest();
//
//        Thread t1 = new Thread(test1);
//        Thread t2 = new Thread(test2);
//        t1.start();
//        t2.start();
//        t1.join();
//        t2.join();
//        System.out.println(i);
//    }


    // static SynchronizedTest instance = new SynchronizedTest();
    static int i = 0;

    @Override
    public void run() {
        // 省略其他耗时操作....
        // 使用同步代码块对变量i进行同步操作,锁对象为instance
        synchronized (this) {
            System.out.println(this);
            for (int j = 0; j < 10000; j++) {
                i++;
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {

        SynchronizedTest synchronizedTest = new SynchronizedTest();
        Thread t1 = new Thread(synchronizedTest);
        Thread t2 = new Thread(synchronizedTest);
        t1.start();
        t2.start();
        t1.join();
        t2.join();
        System.out.println(i);
    }

}

