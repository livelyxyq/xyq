package com.study.studyspringboot.test.thread;

/**
 * 说明：
 *
 * @author xiaoyuqin
 * @date 2020/5/26 16:27
 */

import java.util.concurrent.*;

public class ThreadPool {

    //newFixedThreadPool创建固定大小的线程池。
    //每次提交一个任务就创建一个线程，直到线程达到线程池的最大大小。
    // 线程池的大小一旦达到最大值就会保持不变，如果某个线程因为执行异常而结束，那么线程池会补充一个新线程。
    //线程池接口是ExecutorService
    public static ExecutorService newFixedThreadPool(int nThreads) {
        return new ThreadPoolExecutor(
                nThreads,// corePoolSize
                nThreads,// maximumPoolSize == corePoolSize
                0L,// 空闲时间限制是 0
                TimeUnit.MILLISECONDS,
                //TimeUnit.DAYS天 TimeUnit.HOURS小时 TimeUnit.MINUTES分钟 TimeUnit.SECONDS秒 TimeUnit.MILLISECONDS毫秒
                new LinkedBlockingQueue<Runnable>(nThreads)//一个由链表结构组成的有界阻塞队列
        );
    }

    // newCachedThreadPool创建一个可缓存的线程池。
    //如果线程池的大小超过了处理任务所需要的线程，那么就会回收部分空闲（60秒不执行任务）的线程，
    //当任务数增加时，此线程池又可以智能的添加新线程来处理任务。
    //此线程池不会对线程池大小做限制，线程池大小完全依赖于操作系统（或者说JVM）能够创建的最大线程大小。
    public static ExecutorService newCachedThreadPool() {
        return new ThreadPoolExecutor(
                0,                  // corePoolSoze == 0
                Integer.MAX_VALUE,  // maximumPoolSize 非常大
                60L,                // 空闲判定是60 秒
                TimeUnit.SECONDS,//分钟
                // 神奇的无存储空间阻塞队列，每个 put 必须要等待一个 take
                new SynchronousQueue<Runnable>()
        );
    }

    //newSingleThreadExecutor创建一个单线程的线程池。
    //这个线程池只有一个线程在工作，也就是相当于单线程串行执行所有任务。
    //如果这个唯一的线程因为异常结束，那么会有一个新的线程来替代它。此线程池保证所有任务的执行顺序按照任务的提交顺序执行。
    //实际上FinalizableDelegatedExecutorService这个类就是对ExecutorService进行了一个包装，防止暴露出不该被暴露的方法
    //，然后加上了finalize方法保证线程池的关闭
    public static ExecutorService newSingleThreadExecutor() {
        return
                new ThreadPoolExecutor(1, 1,
                        0L, TimeUnit.MILLISECONDS,
                        new LinkedBlockingQueue<Runnable>(1));
    }

    public static void main(String[] args) {

        System.out.println("使用固定大小的线程池");
        ExecutorService newCachedThreadPool = newFixedThreadPool(2);
        for (int i = 0; i < 10; i++) {
            final int index = i;
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            newCachedThreadPool.execute(() -> System.out.println(Thread.currentThread().getName() + "----" + index));
        }
        try {
            Thread.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("使用可缓存的线程池");
        newCachedThreadPool = newCachedThreadPool();
        for (int i = 0; i < 10; i++) {
            final int index = i;
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            newCachedThreadPool.execute(() -> System.out.println(Thread.currentThread().getName() + "----" + index));
        }
        try {
            Thread.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("使用单线程的线程池");
        newCachedThreadPool = newSingleThreadExecutor();
        for (int i = 0; i < 10; i++) {
            final int index = i;
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            newCachedThreadPool.execute(() -> System.out.println(Thread.currentThread().getName() + "----" + index));
        }
    }
}
