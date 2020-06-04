package cn.jie.yang.down;

import java.io.File;
import java.net.URL;

import org.apache.commons.io.FileUtils;

public class WebDownload {
    public synchronized static void download(URL url, File file) throws Exception {
        FileUtils.copyURLToFile(url, file);
    }

}
