package cn.jie.yang.img;

public class ImgFilePath {
    public static String BASE_PATH = "F:\\BaiduWeb";
    
    public static String getFileTypeFromUrl(String url) {
        String type = "";
        String fileName = getFileNameFromUrl(url);
        if (fileName.indexOf(".") == -1) {
            type = ".jpeg";
        }else {
            int index = url.lastIndexOf(".");
            type = url.substring(index);
        }
        return type.replaceAll("[^\\.\\w].*","");
    }
    
    public static String getFileNameFromUrl(String url) {
        int index = url.lastIndexOf("/");
        String type = url.substring(index+1);
        return type;
    }
}
