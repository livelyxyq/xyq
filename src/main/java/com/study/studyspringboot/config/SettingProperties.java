package com.study.studyspringboot.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.List;

/**
 * 说明：
 *
 * @author xiaoyuqin
 * @date 2020/12/31 17:30
 */
@Data
@Configuration
@ConfigurationProperties(prefix = "setting")
public class SettingProperties {

    private User user;
    private List<Integer> labelIdList;

    @Data
    public static class User {
        private Integer age;
        private String name;
        private String address;
    }

}
