package com.study.studyspringboot.test;

import java.math.BigDecimal;

/**
 * 说明：
 *
 * @author xiaoyuqin
 * @date 2020/5/19 18:58
 */
public class TestBigDecimal {

    public static void main(String[] args) {

        BigDecimal bigDecimal = new BigDecimal("0.123");
        BigDecimal bigDecimal1 = BigDecimal.valueOf(0.123);
        // 禁止使用 new BigDecimal(double)
        BigDecimal bigDecimal2 = new BigDecimal(0.123);

        System.out.println(bigDecimal + "," + bigDecimal1 + "," + bigDecimal2);

    }
}
