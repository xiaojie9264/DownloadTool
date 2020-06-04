package cn.jie.yang.down;

import java.io.File;
import java.net.URL;

import cn.jie.yang.img.ImgFilePath;

public class DownloadThread implements Runnable {


    private String url;
    private String file;

    public DownloadThread(String url, String file) {
        super();
        this.url = url;
        this.file = file;
    }

    @Override
    public void run() {
        long start = System.currentTimeMillis();
        long end = 0L;
        try {
            WebDownload.download(new URL(this.url), new File(ImgFilePath.BASE_PATH, this.file));
            Thread.sleep(100);
        } catch (Exception e) {
            File file = new File(ImgFilePath.BASE_PATH, this.file);
            if (file.exists()) {
                file.delete();
            }
            System.err.println(e.getStackTrace().toString());
            end = System.currentTimeMillis();
            DownLoad.setStatus(false);
            System.err.println(String.format("文件:%s 下载失败啦    %d", this.file, (end - start)));
            return ;
        }
        DownLoad.setStatus(true);
        end = System.currentTimeMillis();
        System.out.println(String.format("文件:%s 下载成功啦    %d ms", this.file, (end - start)));
    }

}
