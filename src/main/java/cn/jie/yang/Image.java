package cn.jie.yang;

/**
 * @author xiaojie
 * @date 2020年4月26日
 * @direction <p> <p>
 */
public class Image {

    private String thumbURL;
    
    private String type;


    public String getThumbURL() {
        return thumbURL;
    }


    public void setThumbURL(String thumbURL) {
        this.thumbURL = thumbURL;
    }


    public String getType() {
        return type;
    }


    public void setType(String type) {
        this.type = type;
    }


    @Override
    public String toString() {
        return "Image [thumbURL=" + thumbURL + ", type=" + type + "]";
    }
}
