package com.study.studyspringboot.test.threadPool;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 说明：
 *
 * @author xiaoyuqin
 * @date 2020/6/2 17:06
 */
public class Test {
    public static void main(String[] args) {

        // 创建线程池
        ThreadPoolExecutor executor = new ThreadPoolExecutor(
                5, 10,
                200, TimeUnit.MILLISECONDS,
                new ArrayBlockingQueue<>(5));

        for (int i = 0; i < 20; i++) {
            MyTask myTask = new MyTask(i);

            // 执行任务
            executor.execute(myTask);
            System.out.println("线程池中线程数目：" + executor.getPoolSize() + "，队列中等待执行的任务数目：" +
                    executor.getQueue().size() + "，已执行完的任务数目：" + executor.getCompletedTaskCount());
        }

        // 关闭线程池
        executor.shutdown();
    }
}


class MyTask implements Runnable {
    private int taskNum;

    public MyTask(int num) {
        this.taskNum = num;
    }

    @Override
    public void run() {
        System.out.println("正在执行task " + taskNum);
        try {
            Thread.sleep(4000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("task " + taskNum + "执行完毕");
    }
}