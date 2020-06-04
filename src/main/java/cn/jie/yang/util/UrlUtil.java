package cn.jie.yang.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class UrlUtil {

    private static final String BASE_URL = "https://image.baidu.com/search/flip";
    
    
    
    private String key;
    private Integer imgNum;

    public UrlUtil(String key, Integer imgNum) {
        this.key = key;
        this.imgNum = imgNum;
    }

    public List<String> getUrls() {
        List<String> urls = new ArrayList<String>();
        Integer calculationPage = calculationPage();
        for (int i = 0; i < calculationPage; i++) {
            urls.add(createUrl(i));
        }
        return urls;
    }

    /*
     * 传入参数值
     * 
     * @param word 搜索的关键字
     * 
     * @param startNum 起始图片下标
     * 
     * @return
     */
    private Map<String, String> getParams(String word, String startNum) {
        Map<String, String> params = new HashMap<String, String>();
        params.put("tn", "baiduimage");
        params.put("ie", "utf-8");
        params.put("word", word);
        params.put("pn", startNum);
        params.put("gsm", "50");
        params.put("ct", "");
        params.put("ic", "0");
        params.put("lm", "-1");
        params.put("width", "0");
        params.put("height", "0");
        return params;
    }

    /*
     * 创建url
     * @param page 传入页码
     * @return 返回 URL
     */
    private String createUrl(Integer page) {
        Integer strat = page * 20;
        StringBuffer url = new StringBuffer();
        url.append(BASE_URL);
        url.append("?");
        Map<String, String> params = getParams(this.key, String.valueOf(strat));
        for (Entry<String, String> entry : params.entrySet()) {
            url.append(entry.getKey().trim());
            url.append("=");
            url.append(entry.getValue().trim());
            url.append("&");
        }
        url.append("time="+System.currentTimeMillis());
        return url.toString();
    }

    /*
     * 计算出总页码
     * @return 
     */
    private Integer calculationPage() {
        System.out.println("计算页码.....");
        Integer page = 0;
        if (this.imgNum % 20 == 0) {
            page = this.imgNum / 20;
        }else {
            page = this.imgNum / 20 + 1;
        }
        System.out.println(String.format("计算出页码有 %s 页", page));
        return page;
    }

}
