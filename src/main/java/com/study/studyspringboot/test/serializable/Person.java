package com.study.studyspringboot.test.serializable;

import lombok.Data;

import java.io.Serializable;

/**
 * 说明：
 *
 * @author xiaoyuqin
 * @date 2020/5/20 17:03
 */
@Data
public class Person implements Serializable {

    public static final long serialVersionUID = 1L;
    private int age;
    private String name;
    private String sex;
    private String address;

}
