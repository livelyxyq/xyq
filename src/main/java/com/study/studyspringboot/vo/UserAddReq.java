package com.study.studyspringboot.vo;

import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * 说明：
 *
 * @author xiaoyuqin
 * @date 2020/7/1 10:13
 */
@Data
public class UserAddReq {

    @NotNull(message = "年龄不能为空a")
    private Integer age;

    @NotBlank(message = "姓名不能为空")
    private String name;

    @Valid
    @NotNull(message = "面部特征不能为空")
    private Face face;

    @Data
    private static class Face {
        @NotBlank(message = "眼睛特征描述不能为空")
        private String eyeFeature;

        @NotBlank(message = "嘴巴特征描述不能为空")
        private String mouth;
    }
}
