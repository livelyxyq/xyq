//package com.study.studyspringboot.test.collections;
//
//import javafx.util.Pair;
//
//import java.util.*;
//import java.util.stream.Collectors;
//
///**
// * 说明：集合测试类
// *
// * @author xiaoyuqin
// * @date 2020/5/21 17:53
// */
//public class Test {
//
//    private static void test1() {
//
//        List<Pair<String, Double>> pairArrayList = new ArrayList<>(4);
//        pairArrayList.add(new Pair<>("version", 6.19));
//        pairArrayList.add(new Pair<>("version", 10.24));
//        pairArrayList.add(new Pair<>("version", 13.14));
//        Map<String, Double> map = pairArrayList.stream().collect(
//                // 生成的 map 集合中只有一个键值对：{version=13.14}
//                Collectors.toMap(Pair::getKey, Pair::getValue, (v1, v2) -> v2));
//
//        map.forEach((k, v) -> {
//            System.out.println(k + "=" + v);
//        });
//    }
//
//    private static void test2() {
//
//        List<String> list = new ArrayList<>(2);
//
//        list.add("age");
//        list.add("name");
//        list.add("sex");
//
//        String[] array = list.toArray((new String[0]));
//    }
//
//    private static void test3() {
//
//        String[] array = new String[2];
//        array[0] = "age";
//        array[1] = "name";
//
//        List<String> list = Arrays.asList(array);
//
//        list.forEach(arg -> System.out.println(arg));
//
//        array[1] = "sex";
//
//        list.forEach(arg -> System.out.println(arg));
//    }
//
//    private static void test4() {
//
//        List<String> list = new ArrayList<>(2);
//
//        list.add("age");
//        list.add("name");
//        list.add("sex");
//
//        List<String> subList = list.subList(0, 2);
//        subList.forEach(arg -> System.out.println(arg));
//
//        list.set(0, "weight");
//        subList.forEach(arg -> System.out.println(arg));
//
//    }
//
//    public static void test5() {
//        int initialCapacity = (int) (2 / 0.75 + 1);
//        List<String> list = new ArrayList<>(initialCapacity);
//        list.add("1");
//        list.add("2");
////        Iterator<String> iterator = list.iterator();
////        while (iterator.hasNext()) {
////            String item = iterator.next();
////            if ("2".equals(item)) {
////                iterator.remove();
////            }
////        }
//
////        for (String item : list) {
////            if ("2".equals(item)) {
////                list.remove(item);
////            }
////        }
//
////        for (int i = 0; i < list.size(); i++) {
//////            if ("1".equals(list.get(i))) {
//////                list.remove(list.get(i));
//////            }
//////        }
//
//        for (String item : list) {
//            System.out.println(item);
//        }
//    }
//
//    public static void test6() {
//
//        Map<String, String> map = new HashMap<>(4);
//
//        map.put("key1", "value1");
//        map.put("key2", "value2");
//        map.put("key3", "value3");
//        map.put("key4", "value4");
//    }
//
//    public static void main(String[] args) {
//
//        test6();
//    }
//
//}
