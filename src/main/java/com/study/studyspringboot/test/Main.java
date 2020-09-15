package com.study.studyspringboot.test;

import lombok.extern.slf4j.Slf4j;

import java.util.Optional;
import java.util.Random;
import java.util.Scanner;
import java.util.regex.Pattern;

/**
 * 说明：
 *
 * @author xiaoyuqin
 * @date 2020/5/28 11:44
 */
@Slf4j
public class Main {

    Pattern pattern = Pattern.compile("规则");

    public static void main(String[] args) {
        test8();
    }

    public static void test8() {
        int a = 1;
        int b = 2;

        if (a == b) {
            System.out.println("a==b");
        } else if (a < b) {
            System.out.println("a < b");
        } else {
            System.out.println("a > b");
        }

    }

    public static void test7() {
        Random random = new Random();
        int nextInt = random.nextInt(1000) + 100;
        System.out.println(nextInt);

    }

    public static void test6() {

        long startTime = System.currentTimeMillis();

        log.info("start");
        int i = Integer.MAX_VALUE;
        while (i-- > 0) {
            // doSome()
        }
        log.info("end");

        long endTime = System.currentTimeMillis();

        log.info("耗时:" + (endTime - startTime) + "毫秒");
    }

    public static void test5() {

        Scanner sc = new Scanner(System.in);

        // 读取的字符串数量
        int i = sc.nextInt();
        int index = 0;

        // 存放读取的字符串
        String[] array = new String[i];
        while (index < i) {
            array[index++] = sc.nextLine();
        }

        // 输出读取的字符串
        for (String str : array) {
            System.out.println("输入的字符串：" + str);
        }

    }

    public static void test4() {

        System.out.println("start");
        assert true;
        System.out.println("go on");
        assert false;
        System.out.println("end");
    }

    public static void test3() {

        Integer i = new Integer(1);
        String s = Optional.ofNullable(i).map(num -> num.toString()).orElse("unKnow");

        System.out.println(s);

        String str = "  ";
        System.out.println(Optional.ofNullable(str).map(String::toString).orElse("UnKnow"));

    }

    public static void test2() {

        int num1 = 10;
        float num2 = 9.99f;

        byte i = 127;
        System.out.println(i);

        System.out.println(num1 >= num2 ? num1 : num2);
    }

    public static void test1() {

//        Integer i = new Integer(1);
//        Integer j = new Integer(1);

//        Integer i = new Integer(1);
//        int j = 1;

//        Integer i = new Integer(1);
//        Integer j = 1;

        // 对于两个非new生成的Integer对象，进行比较时，
        // 如果两个变量的值在区间-128到127之间，则比较结果为true，
        // 如果两个变量的值不在此区间，则比较结果为false
        Integer i = 128;
        Integer j = 128;
        System.out.println(i == j);

//        Integer a = new Integer(1);
//        Integer b = new Integer(2);
//        Integer c = null;
//        int a = 1;
//        int b = 2;
//        int c = 0;
//        Boolean flag = false;
//        // a*b 的结果是 int 类型，那么 c 会强制拆箱成 int 类型，抛出 NPE 异常
//        Integer result = (flag ? a * b : c);

    }

    public static void method(String param) {
        switch (param) {
            // 肯定不是进入这里
            case "sth":
                System.out.println("it's sth");
                break;
            // 也不是进入这里
            case "null":
                System.out.println("it's null");
                break;
            // 也不是进入这里
            default:
                System.out.println("default");
        }
    }
}
