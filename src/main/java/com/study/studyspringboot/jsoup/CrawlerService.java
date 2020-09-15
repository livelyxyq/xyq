package com.study.studyspringboot.jsoup;

import com.study.studyspringboot.util.LocalDateTimeUtils;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.*;
import java.net.URL;

/**
 * 说明：
 *
 * @author xiaoyuqin
 * @date 2020/9/15 14:21
 */
@Slf4j
@Service
public class CrawlerService {

    @Resource
    private RedisTemplate<String, Object> redisTemplate;

    private final String commonFolder = "C:\\picture\\";

    public static void main(String[] args) {
//        downloadPicture("http://gz.121314.com/uploads/allimg/20200608/1591585372122933.jpg", "C:\\picture\\1591585372122933.jpg");

//        String s = "jin-fu-ren||2020-09-15||1575431200143510.jpg||http://gz.121314.com/uploads/allimg/20191204/1575431200143510.jpg";
//
//        String[] split = s.split("\\|\\|");
//
//        for (String ss : split) {
//            System.out.println(ss);
//        }

        String folder = "C:\\picture\\";

        // todo 判断文件夹是否存在
        File folderFile = new File(folder);

        if (!folderFile.exists() && !folderFile.isDirectory()) {

            folderFile.mkdirs();
            System.out.println("创建文件夹");
        } else {
            System.out.println("文件夹已存在");
        }
    }

    /**
     * 根据url爬图片（金夫人）
     *
     * @param url 网址
     * @throws IOException IO异常
     */
    public void crawlerPictureByUrl(String url) throws IOException {

        // 金夫人网址包含 "http://gz.121314.com"
        // 例如：http://gz.121314.com/product/shidachangguan/xingyaomeixueguan/2212.html

        Document doc = Jsoup.connect(url).get();

        // 扩展名为.jpg的图片
        Elements jpgs = doc.select("img[src$=.jpg]");

        String date = LocalDateTimeUtils.formatNow("yyyy-MM-dd");

        for (Element element : jpgs) {
            String src = element.attr("src");

            // todo 过滤图片大于等于2000*2000图片
            if (src.contains("uploads")) {
                // 保存图片，并去重

                // 抓的图片url示例：http://gz.121314.com/uploads/allimg/20191204/1575431183357163.jpg
                String imgUrl = "http://gz.121314.com" + src;

                String[] strings = imgUrl.split("/");
                String imgKey = strings[6];
                String key = CacheKey.IMG_KEY + "jin-fu-ren:" + imgKey;

                if (!redisTemplate.hasKey(key)) {
                    System.out.println("imgUrl=" + imgUrl);
                    redisTemplate.opsForValue().set(key, 0);

                    // 数据示例："jin-fu-ren||2020-09-15||1575431200143510.jpg||http://gz.121314.com/uploads/allimg/20191204/1575431200143510.jpg"
                    String data = "jin-fu-ren||" + date + "||" + imgKey + "||" + imgUrl;

                    // 保存数据
                    redisTemplate.opsForList().leftPush(CacheKey.DATE_KEY, data);
                }
            }
        }
    }

    /**
     * 下载图片（根据redis中的图片数据）
     */
    public void download() {

        Long size = redisTemplate.opsForList().size(CacheKey.DATE_KEY);

        if (size != null) {
            for (long i = 0; i < size; i++) {

                // 数据示例："jin-fu-ren||20200915||1575431200143510.jpg||http://gz.121314.com/uploads/allimg/20191204/1575431200143510.jpg"
                Object object = redisTemplate.opsForList().leftPop(CacheKey.DATE_KEY);

                if (object != null) {
                    String[] strings = object.toString().split("\\|\\|");

                    String folder = commonFolder + strings[0] + "\\" + strings[1];

                    downloadPicture(strings[3], folder, folder + "\\" + strings[2]);
                }
            }
        }
    }

    /**
     * 根据图片url下载图片到本地
     *
     * @param imgUrl 图片url
     * @param folder 文件夹
     * @param path   文件完整路径
     */
    private static void downloadPicture(String imgUrl, String folder, String path) {

        URL url;
        try {

            // 文件夹不存在，则创建文件夹
            File folderFile = new File(folder);
            if (!folderFile.exists() && !folderFile.isDirectory()) {

                folderFile.mkdirs();
                System.out.println("创建文件夹：" + folder);
            }

            System.out.println("下载图片：" + imgUrl);

            url = new URL(imgUrl);
            DataInputStream dataInputStream = new DataInputStream(url.openStream());

            FileOutputStream fileOutputStream = new FileOutputStream(new File(path));
            ByteArrayOutputStream output = new ByteArrayOutputStream();

            byte[] buffer = new byte[1024];
            int length;

            while ((length = dataInputStream.read(buffer)) > 0) {
                output.write(buffer, 0, length);
            }
            fileOutputStream.write(output.toByteArray());

            dataInputStream.close();
            fileOutputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
