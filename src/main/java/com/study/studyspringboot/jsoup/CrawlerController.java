package com.study.studyspringboot.jsoup;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

/**
 * 说明：
 *
 * @author xiaoyuqin
 * @date 2020/9/15 15:50
 */
@RestController
@RequestMapping("/picture")
@AllArgsConstructor
public class CrawlerController {

    private CrawlerService crawlerService;

    @GetMapping("/get")
    public String getJinFuRen() throws IOException {

        // 存放网站url的txt文件
        File file = new File("C:\\picture\\url.txt");
        if (!file.exists()) {
            System.out.println("C:\\picture\\url.txt 文件不存在。");
            return "C:\\picture\\url.txt 文件不存在。";
        }

        BufferedReader br = new BufferedReader(new FileReader(file));
        String url;
        while ((url = br.readLine()) != null) {

            System.out.println("图片网址url=" + url);
            crawlerService.crawlerPictureByUrl(url);
        }

        return "采集照片完成~";
    }

    @GetMapping("/download")
    public String download() {

        crawlerService.download();

        return "下载照片完成~";
    }
}
