package com.study.studyspringboot.jsoup;

import com.study.studyspringboot.util.LocalDateTimeUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * 说明：
 *
 * @author xiaoyuqin
 * @date 2020/9/15 14:21
 */
@Service
public class CrawlerService {

    @Resource
    private RedisTemplate<String, Object> redisTemplate;

    private final String commonFolder = "C:\\picture\\";

    public static void main(String[] args) throws IOException {
        File file = new File("C:\\picture\\ul.txt");

        if (!file.exists()) {
            System.out.println("C:\\picture\\url.txt 文件不存在。");
            return;
        }

        BufferedReader br = new BufferedReader(new FileReader(file));
        String url;
        List<String> urlList = new ArrayList<>();
        while ((url = br.readLine()) != null) {
            urlList.add(url);
            System.out.println("url=" + url);
        }
    }

    /**
     * 根据url爬图片（金夫人）
     *
     * @param url 网址
     * @throws IOException IO异常
     */
    public void crawlerPictureByUrl(String url) throws IOException {

        // .金夫人婚纱网址
        if (url.contains(UrlConstant.JIN_FU_REN)) {
            crawlerJinFuRenPicture(url);

            // .百度图片
        } else if (url.contains(UrlConstant.BAI_DU)) {
            crawlerBaiDuPicture(url);

            // .古摄影网址
        } else if (url.contains(UrlConstant.GU_LOVE)) {
            crawlerGuLovePicture(url);
        }
    }


    /**
     * 采集取金夫人网址的照片
     *
     * @param url
     * @throws IOException
     */
    private void crawlerJinFuRenPicture(String url) throws IOException {

        System.out.println("======================金夫人婚纱网址============================");
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
                String imgUrl = UrlConstant.JIN_FU_REN + src;

                String[] strings = imgUrl.split("/");
                String imgKey = strings[strings.length - 1];
                String key = CacheKey.IMG_KEY + "jin-fu-ren:" + imgKey;

                if (!redisTemplate.hasKey(key)) {
                    System.out.println("可用图片url=" + imgUrl);
                    redisTemplate.opsForValue().set(key, 0);

                    // 数据示例："金夫人婚纱||2020-09-15||1575431200143510.jpg||http://gz.121314.com/uploads/allimg/20191204/1575431200143510.jpg"
                    String data = "金夫人婚纱||" + date + "||" + imgKey + "||" + imgUrl;

                    // 保存数据
                    redisTemplate.opsForList().leftPush(CacheKey.DATE_KEY, data);
                }
            }
        }
    }

    private void crawlerGuLovePicture(String url) throws IOException {

        System.out.println("======================古摄影网址============================");

        Document doc = Jsoup.connect(url).get();

        String date = LocalDateTimeUtils.formatNow("yyyy-MM-dd");

        // 扩展名为.jpg的图片 data-original
        Elements jpgs = doc.select("img[src$=.jpg]");
        savePictureByElements(jpgs, "src", date);

        Elements jpgs2 = doc.select("img[data-original$=.jpg]");
        savePictureByElements(jpgs2, "data-original", date);
    }

    /**
     * 根据【古摄影网站】图片Elements对象以及属性名称（src/data-original)保存图片
     *
     * @param elements
     * @param attr
     * @param date
     */
    private void savePictureByElements(Elements elements, String attr, String date) {
        for (Element element : elements) {

            // 抓的图片url示例：http://uploadfile.gulove.com/cd/2019/1202/1182_1773_20191202062548215.jpg
            String imgUrl = element.attr(attr);

            // todo 过滤图片大于等于2000*2000图片
            if (imgUrl.contains("uploadfile")) {

                // 保存图片，并去重
                String[] strings = imgUrl.split("/");
                String imgKey = strings[strings.length - 1];
                String key = CacheKey.IMG_KEY + "gu-love:" + imgKey;

                if (!redisTemplate.hasKey(key)) {
                    System.out.println("可用图片url=" + imgUrl);
                    redisTemplate.opsForValue().set(key, 0);

                    // 数据示例："古摄影||2020-09-15||1182_1773_20191202062548215.jpg||http://uploadfile.gulove.com/cd/2019/1202/1182_1773_20191202062548215.jpg"
                    String data = "古摄影||" + date + "||" + imgKey + "||" + imgUrl;

                    // 保存数据
                    redisTemplate.opsForList().leftPush(CacheKey.DATE_KEY, data);
                }
            }
        }
    }

    /**
     * TODO 图片超出规定大小
     *
     * @return
     */
    private boolean pictureExceedsSize() {

        return false;
    }

    /**
     * 采集百度网址的照片
     *
     * @param url
     * @throws IOException
     */
    private void crawlerBaiDuPicture(String url) throws IOException {

        System.out.println("======================百度网址============================");

        String key = CacheKey.IMG_KEY + "baidu:" + url;
        if (redisTemplate.hasKey(key)) {
            System.out.println("已经采集过该网址照片，不再重复采集。");
        }

        // 记录key，下次不再重复采集
        redisTemplate.opsForValue().set(key, 0);

        Document doc = Jsoup.connect(url).get();
//        System.out.println("start:" + doc.toString() + "....end....");

        // 扩展名为.jpg的图片
        Elements jpgs = doc.select("hoverURL[src$=.jpg]");

        String date = LocalDateTimeUtils.formatNow("yyyy-MM-dd");

        for (Element element : jpgs) {
            // 抓的图片url
            String imgUrl = element.attr("src");
            // todo 过滤图片大于等于2000*2000图片
            System.out.println("可用图片url=" + imgUrl);

            // 数据示例："baidu||2020-09-15||1600153450429.jpg||https://ss1.bdstatic.com/70cFuXSh_Q1YnxGkpoWK1HF6hhy/it/u=1997162276,1818872301&fm=26&gp=0.jpg"
            String data = "baidu||" + date + "||" + System.currentTimeMillis() + "||" + imgUrl;

            // 保存数据
            redisTemplate.opsForList().leftPush(CacheKey.DATE_KEY, data);
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
