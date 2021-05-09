package com.study.studyspringboot.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 说明：
 *
 * @author xiaoyuqin
 * @date 2021/3/27 15:44
 */
@Service
public class TestService1 {

    @Autowired
    private TestService2 testService2;

    public static void main(String[] args) {
        List<String> list = new ArrayList<>();

        list.add("a");
        list.add("a");
        list.add("b");
        list.add("b");
        list.add("c");

        // 1 删除指定元素，通过迭代器
//        Iterator<String> iterator = list.iterator();
//        while (iterator.hasNext()) {
//            String next = iterator.next();
//            if ("b".equals(next)) {
//                iterator.remove();
//            }
//        }

        // 2 去重
        List<String> collect = list.stream().distinct().collect(Collectors.toList());
        printList(collect);
        printList(list);
    }

    private static void printList(List<String> list) {
        for (String s : list) {
            System.out.println(s);
        }
        System.out.println("============");
    }
}

