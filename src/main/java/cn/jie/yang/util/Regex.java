package cn.jie.yang.util;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 
 * @author xiaojie
 * @date 2020年4月24日
 * @direction
 *            <p>
 *            创建正则表达式处理文本
 *            <p>
 */
public class Regex {

    private Pattern pattern;
    String start = "";
    String end = "";
    private Matcher m;
    
    public Regex(String text, String regex) {
        this.pattern = Pattern.compile(regex);
        this.m = this.pattern.matcher(text);
        this.init(regex);
    }

    private void init(String regex) {
        if (regex.indexOf("(") != -1) {
            this.start = regex.substring(0, regex.indexOf("("));
        }
        if (regex.indexOf(")") != -1) {
            this.end = regex.substring(regex.indexOf(")") + 1);
        }
    }
    /*
     * 返回匹配的值
     * @return
     */
    public List<String> getMatchingValue() {
        List<String> result = new ArrayList<String>();
        while (m.find()) {
            result.add(m.group());
        }
        return result;
    }
    
    /*
     * 返回匹值只包含括号的值
     * @return
     */
    public List<String> getMatchingValueBracketsOnly(){
        List<String> result = new ArrayList<String>();
        while (m.find()) {
            String group = m.group();
            String replace = group.replace(this.start, "").replace(end, "");
            result.add(replace);
        }
        return result;
    }

}
