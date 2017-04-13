package utils;

/**
 * Created by minghua.zmh on 2017/4/13.
 */
public class Tools {
    public  static <T> void swap(T[] list,int i,int j){
        T tmp = list[i];
        list[i] = list[j];
        list[j] = tmp;
    }
}
