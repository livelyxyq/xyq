package com.study.studyspringboot.test.collections;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 说明：
 *
 * @author xiaoyuqin
 * @date 2020/5/19 12:00
 */
public class TestForEach {
    public static void main(String[] args) {
        // 测试源
        List<String> sourceList = new ArrayList<>();
        for (int i = 0; i < 2000; i++) {
            sourceList.add("第" + i + "条数据");
        }
        System.out.println("数据条数：" + sourceList.size());

        long a1 = System.currentTimeMillis();
        for (int i = 0; i < sourceList.size(); i++) {
            doSome();
        }
        long a2 = System.currentTimeMillis();
        System.out.println("普通for循环用时：" + (a2 - a1));

        long b1 = System.currentTimeMillis();
        for (String t : sourceList) {
            doSome();
        }
        long b2 = System.currentTimeMillis();
        System.out.println("增强for循环用时：" + (b2 - b1));

        long c1 = System.currentTimeMillis();
        sourceList.forEach(t -> doSome());
        long c2 = System.currentTimeMillis();
        System.out.println("forEach循环用时：" + (c2 - c1));

        long d1 = System.currentTimeMillis();
        sourceList.parallelStream().forEach(t -> doSome());
        long d2 = System.currentTimeMillis();
        System.out.println("forEach-Stream循环用时：" + (d2 - d1));

        long e1 = System.currentTimeMillis();
        sourceList.stream().map(t -> {
            doSome();
            return t.substring(0, 1);
        }).collect(Collectors.toList());
        long e2 = System.currentTimeMillis();
        System.out.println("stream().map()循环用时：" + (e2 - e1));

        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9);
        numbers.parallelStream()
                .forEach(num -> System.out.print(num + ","));
        System.out.println();
        numbers.parallelStream()
                .forEachOrdered(num -> System.out.print(num + ","));

    }

    private static void doSome() {
        try {
            Thread.sleep(1);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
