package cn.jie.yang;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.jie.yang.down.DownLoad;
import cn.jie.yang.img.ImgFilePath;
import cn.jie.yang.img.ImgUrl;

/**
 * 下载开始入口
 * @author xiaojie
 * @date 2020年4月25日
 * @direction <p> <p>
 */
public class BaiduImgDownLoad {
    
    
    static String word = "";
    /*
     * 开始执行函数
     * @param key
     * @param imgNum
     */
    public static void run(String key,Integer imgNum) {
        word = key.concat("_%d.");
        ImgFilePath.BASE_PATH = ImgFilePath.BASE_PATH.concat("\\"+key);
        System.out.println(String.format("下载的路径为： %s", ImgFilePath.BASE_PATH));
        ImgUrl imgUrl = new ImgUrl(key, imgNum);
        List<Image> imgs = imgUrl.getImgs(); 
        List<Map<String, String>> maps = getMaps(imgs);
        System.out.println(String.format("开始下载===%d个文件===",maps.size()));
        DownLoad downLoad = new DownLoad(maps);
        downLoad.start();
        System.out.println(String.format("已经保存到路径： %s下面啦。", ImgFilePath.BASE_PATH));
    }
    
    private static List<Map<String, String>> getMaps(List<Image> imgs){
        List<Map<String, String>> maps = new ArrayList<Map<String,String>>();
        int index = 1;//每张图片的下标
        for (Image image : imgs) {
            Map<String, String> map = new HashMap<String, String>();
            map.put("url", image.getThumbURL());
            String concat = word.concat(image.getType());
            map.put("filename", String.format(concat, index++));
            maps.add(map);
        }
        return maps;
    }
    
    
}
