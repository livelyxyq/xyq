package com.study.studyspringboot.jsoup;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

/**
 * 说明：
 *
 * @author xiaoyuqin
 * @date 2020/9/15 15:50
 */
@RestController
@RequestMapping("/pic")
@AllArgsConstructor
public class CrawlerController {

    private CrawlerService crawlerService;

    @GetMapping("/get/jin-fu-ren")
    public String getJinFuRen() throws IOException {

        crawlerService.crawlerPictureByUrl("http://gz.121314.com/a/jidukezhao/2164.html");

        return "SUCCESS";
    }

    @GetMapping("/download")
    public String download() {

        crawlerService.download();

        return "SUCCESS";
    }
}
