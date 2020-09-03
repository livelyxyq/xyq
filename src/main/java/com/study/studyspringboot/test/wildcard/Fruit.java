package com.study.studyspringboot.test.wildcard;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 说明：
 *
 * @author xiaoyuqin
 * @date 2020/5/22 15:36
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class Fruit extends Food{

    private float price;

    private String color;

    public Fruit(float price, String color) {
        this.price = price;
        this.color = color;
    }
}
