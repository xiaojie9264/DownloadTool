package cn.jie.yang.down;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import cn.jie.yang.util.LogUtil;

public class DownLoad {

    private static LogUtil log = new LogUtil();

    List<Map<String, String>> urls; // 下载链接
    Integer count; // 下载总数
    static volatile int successnu = 0; // 下载成功计数
    static volatile int faildnu = 0; // 下载失败计数

    public DownLoad(List<Map<String, String>> urls) {
        super();
        this.urls = urls;
        this.count = urls.size();
    }

    /*
     * 开始下载
     */
    public void start() {
        long start = System.currentTimeMillis();
        ExecutorService service = Executors.newFixedThreadPool(100);
        for (Map<String, String> map : urls) {
            service.execute(new DownloadThread(map.get("url"), map.get("filename")));
        }
        service.shutdown();
        try {
            service.awaitTermination(2, TimeUnit.HOURS);
        } catch (InterruptedException e) {
            log.print(e.getStackTrace().toString());
        }
        long end = System.currentTimeMillis();
        double time = (end - start)/1000;
        log.print(String.format("下载文件总数为： %d 个 ，成功下载： %d 个，失败：%d  个，耗时：%.2f  秒", count,successnu,faildnu,time));
    }
    
    public synchronized static void setStatus(boolean b) {
        if (b) {
            successnu++;
        }else {
            faildnu++;
        }
    }
       
}
