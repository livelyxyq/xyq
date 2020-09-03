package com.study.studyspringboot.test.thread;

/**
 * 说明：
 *
 * @author xiaoyuqin
 * @date 2020/5/26 17:46
 */
public interface TestInterface {
    // 抽象方法
    void sub();

    // java.lang.Object中的方法不是抽象方法
    @Override
    boolean equals(Object var1);

    // default不是抽象方法
    default void defaultMethod() {

    }

    // static不是抽象方法
    static void staticMethod() {

    }

}
