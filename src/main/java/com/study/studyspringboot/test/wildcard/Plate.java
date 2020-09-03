package com.study.studyspringboot.test.wildcard;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * 说明：
 *
 * @author xiaoyuqin
 * @date 2020/5/22 15:38
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Plate<T> {

    private List<T> item;
}
