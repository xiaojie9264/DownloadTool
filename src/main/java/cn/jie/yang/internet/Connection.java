package cn.jie.yang.internet;

import java.io.BufferedReader;
import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;

/**
 * 
 * @author xiaojie
 * @date 2020年4月24日
 * @direction
 *            <p>
 *            获取网络连接
 *            <p>
 */
public class Connection implements Closeable {

    private InputStream inputStream;

    public Connection(String url) {
        try {
            URL con = new URL(url);
            this.inputStream =con.openStream();
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

    /*
     * 返回一个流
     * 
     * @return
     */
    public InputStream getInputStream() {
        return this.inputStream;
    }

    /*
     * 将响应的流转换为String
     * @return
     */
    public String getResponseString() {
        StringBuffer buffer = new StringBuffer(); 
        try (InputStreamReader iReader = new InputStreamReader(this.inputStream, "UTF-8");
              BufferedReader reader = new BufferedReader(iReader);) {
            String line = null;
            while((line = reader.readLine()) != null) {
                buffer.append(line);
                buffer.append("\n");
            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
        } 
        return buffer .toString();
    }

    @Override
    public void close() throws IOException {
        // TODO Auto-generated method stub
        this.inputStream.close();
    }

}
