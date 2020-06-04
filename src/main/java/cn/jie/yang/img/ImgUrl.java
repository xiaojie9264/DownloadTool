package cn.jie.yang.img;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.ListIterator;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import com.alibaba.fastjson.JSONArray;

import cn.jie.yang.Image;
import cn.jie.yang.internet.Connection;
import cn.jie.yang.util.Regex;
import cn.jie.yang.util.UrlUtil;

public class ImgUrl {


    private List<Image> imgUrls;
    private Integer imgNum;
    private UrlUtil urlUtil = null;

    private List<String> urls;

    public ImgUrl(String key, Integer imgNum) {
        this.imgNum = imgNum;
        this.imgUrls = new ArrayList<Image>();
        this.urlUtil = new UrlUtil(key, imgNum);
        init();
    }

    private void init() {
        System.out.println("初始化所有URL。。。。。。");
        this.urls = urlUtil.getUrls();
    }

    @SuppressWarnings("unchecked")
    private List<Image> getImgUrls(String url) {
        String html = getHtml(url);
        String regexStr = "'imgData',(.*?})\\)";
        Regex regex = new Regex(html, regexStr);
        List<String> matchingValueBracketsOnly = regex.getMatchingValueBracketsOnly();
        regexStr = "\\[.*?}]";
        if (matchingValueBracketsOnly.size() == 0)
            return Collections.EMPTY_LIST;
        regex = new Regex(matchingValueBracketsOnly.get(0), regexStr);
        List<String> matchingValue = regex.getMatchingValue();
        if (matchingValueBracketsOnly.size() == 0)
            return Collections.EMPTY_LIST;
        List<Image> parseArray = JSONArray.parseArray(matchingValue.get(0), Image.class);
        if (parseArray.size() > 20) {
            return parseArray.subList(0, 20);
        }
        return parseArray;
    }

    private String getHtml(String url) {
        @SuppressWarnings("resource")
        Connection connection = new Connection(url);
        return connection.getResponseString();
    }

    public List<Image> getImgs() {
        Temp<Image> temp = new Temp<Image>();
        ExecutorService service = Executors.newCachedThreadPool();
        for (String url : this.urls) {
            service.execute(() -> {
                List<Image> urList = (List<Image>) this.getImgUrls(url);
                ListIterator<Image> listIterator = urList.listIterator();
                while (listIterator.hasNext()) {
                    Image image = (Image) listIterator.next();
                    if (image.getThumbURL()==null || image.getType()==null) {
                        listIterator.remove();
                    }
                    
                }
                temp.addToTempList(urList);
            });
        }
        service.shutdown();
        try {
            service.awaitTermination(1, TimeUnit.DAYS);
        } catch (InterruptedException e) {
            System.err.println(e.getStackTrace().toString());
        }

        System.out.println(String.format("临时列表中包含%d条数据", temp.getTemp().size()));
        if (this.imgNum >= temp.getTemp().size()) {
            return temp.getTemp();
        }
        this.imgUrls = temp.getTemp().subList(0, this.imgNum);
        System.out.println(String.format("截取数据%d条", this.imgUrls.size()));
        return this.imgUrls;
    }

}
