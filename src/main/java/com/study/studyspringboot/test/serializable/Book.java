package com.study.studyspringboot.test.serializable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 说明：
 *
 * @author xiaoyuqin
 * @date 2020/5/20 17:47
 */
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class Book implements Serializable {

    public static final long serialVersionUID = 1L;
    private Integer price;
    private String author;

}
