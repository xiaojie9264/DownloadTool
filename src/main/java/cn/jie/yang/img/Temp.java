package cn.jie.yang.img;

import java.util.ArrayList;
import java.util.List;

public class Temp <T>{

    private  List<T> temp = new ArrayList<T>();
    public synchronized void addToTempList(List<T> list) {
         temp.addAll(list);
    }
    
    public synchronized List<T> getTemp() {
        return temp;
    }
}
