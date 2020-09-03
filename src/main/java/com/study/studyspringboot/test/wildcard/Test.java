package com.study.studyspringboot.test.wildcard;

import java.util.ArrayList;
import java.util.List;

/**
 * 说明：
 *
 * @author xiaoyuqin
 * @date 2020/5/22 15:39
 */
public class Test {

    public static void main(String[] args) {

        // 定义一个水果盘子。上界通配符
        List<Apple> appleList = new ArrayList<>();
        appleList.add(new Apple((float) 8.88, "red"));
        Plate<? extends Fruit> plate = new Plate<>(appleList);

        List<? extends Fruit> item = plate.getItem();
        System.out.println(item);

        Fruit fruit = item.get(0);
        System.out.println("fruit = " + fruit);

        // 定义一个水果盘子，下界通配符
        List<Food> foodList = new ArrayList<>();
        foodList.add(new Food(10));
        Plate<? super Fruit> tPlate = new Plate<>(foodList);

        List<? super Fruit> plateItem = tPlate.getItem();
        Object object = plateItem.get(0);
        System.out.println("object = " + object);

        plateItem.add(new Fruit((float) 8.88, "red"));

        System.out.println(plateItem.get(1));

    }
}
