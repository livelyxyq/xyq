package com.study.studyspringboot.jsoup;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

/**
 * 说明：
 *
 * @author xiaoyuqin
 * @date 2020/9/14 14:13
 */
public class Test {
    public static void main(String[] args) throws IOException {

        // 金夫人网址包含 "http://gz.121314.com"

        Document doc = Jsoup.connect("http://gz.121314.com/product/shidachangguan/xingyaomeixueguan/2212.html").get();

        // 扩展名为.jpg的图片
        Elements jpgs = doc.select("img[src$=.jpg]");

        for (Element element : jpgs) {
            String src = element.attr("src");

            // todo 过滤图片大于等于2000*2000图片
            if (src.contains("uploads")) {

                System.out.println("url=http://gz.121314.com" + src);

                // todo 保存图片，考虑去重
            }

        }
    }
}
