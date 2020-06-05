package cn.jie.yang.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class LogUtil {

    private StackTraceElement stackTraceElement;
    private String msgstr;


    public void print(String str) {
        StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
        this.stackTraceElement = stackTrace[stackTrace.length - 1];
        init();
        System.out.println(String.format(this.msgstr, str));
    }
    
    private void init() {
        StringBuffer buffer = new StringBuffer();
        SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss");
        String date = format.format(new Date());
        buffer.append("时间： ");
        buffer.append(date);
        buffer.append(" ");
        String className = this.stackTraceElement.getFileName();
        buffer.append("文件： ");
        buffer.append(className);
        buffer.append(" ");
        String methodName = this.stackTraceElement.getMethodName();
        buffer.append("方法： ");
        buffer.append(methodName);
        buffer.append(" ");
        int lineNumber = this.stackTraceElement.getLineNumber();
        buffer.append("行数： ");
        buffer.append(lineNumber);
        buffer.append("  :=>> ");
        buffer.append("%s");
        this.msgstr = buffer.toString();
    }

}
