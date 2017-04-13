import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/**
 * 排序算法
 * Created by minghua.zmh on 2017/3/27.
 */
public class Sort {
    private List<Long> list = Arrays.asList(10L,9L,8L,7L,6L,5L,4L,3L,2L,1L);
    public static void main(String[] args){
        Sort sort = new Sort();
        List<Long> source = new ArrayList<>();
        source.addAll(sort.list);
        sort.insertSort(source);

        source.clear();
        source.addAll(sort.list);
        sort.mergeSort(source);

        source.clear();
        source.addAll(sort.list);
        sort.heapSort(source);

        source.clear();
        source.addAll(sort.list);
        sort.countSort(source);

        source.clear();
        source.addAll(sort.list);
        sort.quickSort(source);

        sort.radixSort();
        sort.bucketSort();
    }

    /*
    插入排序
     */
    private void insertSort(List<Long> source){
        int size = source.size();
        int num = 0;
        for(int j=0;j<size;j++){
            Long key = source.get(j);
            int i=j-1;
            while(i>=0 && source.get(i)>key){
                source.set(i+1,source.get(i));
                i--;
                num ++;
            }
            source.set(i+1,key);
            num ++;
        }
        System.out.println(source.toString());
        System.out.println(num);
    }

    /**
     * 归并排序
     * @param source 输入
     */
    private void mergeSort(List<Long> source){
        int num = 0;
        double level = Math.ceil(Math.log(source.size()));
        List<Long> result = new ArrayList<>(10);
        result.addAll(source);
        int size = source.size();
        //迭代次数
        for(int j=0;j<=level;j++){
            double interval = Math.pow(2,j);
            int i=0;
            //遍历
            while(i<size) {
                int start = i;
                int end = Math.min(size,i + 2*(int)interval);
                //分组 10,7
                for (int k1 = start ; i < end; ) {
                    //两组比较
                    for (int k2 = start + (int) interval ; i < end; ) {
                        if(k2>=end) {
                            result.set(i, source.get(k1));
                            k1++;
                            num++;
                        }else if(k1==start + (int) interval) {
                            result.set(i, source.get(k2));
                            k2++;
                            num++;
                        }else if (source.get(k1) < source.get(k2)) {
                            result.set(i, source.get(k1));
                            k1++;
                            num++;
                        } else {
                            result.set(i, source.get(k2));
                            k2++;
                            num++;
                        }
                        i++;
                    }
                }
            }
            source.clear();
            source.addAll(result);
        }
        System.out.println(source.toString());
        System.out.println(num);
    }

    /**
     * 堆排序
     * @param source
     */
    private void heapSort(List<Long> source){
        int num = 0;
        int size = source.size();
        int begin;

        for(int i = size-1; i > 0; i--){
            begin = (i-1)>>1;
            for(int j=begin;j>=0;j--) {
                maxHeapify(source, j, i);
                num++;
            }
            swap(source,0, i);
        }

        //堆排序
        System.out.println(source.toString());
        System.out.println(num);
    }

    private void maxHeapify(List<Long> source ,int index,int size){
        int left = (index<<1)+1;
        int right = left+1;
        int maxI = left;
        if(left>=size){
            return;
        }
        if(right<source.size() && source.get(left)<source.get(right)){
            maxI = right;
        }
        if(source.get(index)<source.get(maxI)){
            swap(source,index,maxI);
        }
    }

    private void swap(List<Long> source , int index1 ,int index2){
        Long tmp = source.get(index1);
        source.set(index1,source.get(index2));
        source.set(index2,tmp);
    }

    /**
     * 计数排序
     * @param source
     */
    private void countSort(List<Long> source){
        int num =0;
        List<Integer> arr = Arrays.asList(0,0,0,0,0,0,0,0,0,0,0,0,0);
        for(Long i :source){
            arr.set(i.intValue(),arr.get(i.intValue())+1);
            num ++;
        }
        int i = 0;
        for(int j=0;j<arr.size();j++){
            if(arr.get(j)>0){
                source.set(i,(long)j);
                i++;
            }
            num ++;
        }
        System.out.println(arr);
        System.out.println(num);
    }

    /**
     * 快速排序
     * @param source
     */
    private void quickSort(List<Long> source){
        int num = quickSortRecursive(source,0,source.size()-1);

        System.out.println(source.toString());
        System.out.println(num);
    }

    private int quickSortRecursive(List<Long> source,int start,int end){
        int num=0;
        int mid = (end-start+1)/2+start;
        if(mid<=start) return num;
        long v = source.get(mid);
        swap(source,mid,end);
        int swap = mid;
        for(int i = start;i<end;i++){
            if(source.get(i)>=v){
                num ++;
                for(int j=i;j<end;j++){
                    if(source.get(j)<v) {
                        num ++;
                        swap = i + 1;
                        swap(source, i, j);
                        break;
                    }
                }
            }
        }
        swap(source,swap,end);
        num +=quickSortRecursive(source,start,swap-1);
        num +=quickSortRecursive(source,swap+1,end);
        return num;
    }

    /**
     * 基数排序LSD
     */
    private void radixSort(){
        List<Long> l = Arrays.asList(123L,3655L,456L,871L,471L);
        ArrayList<Long> list = new ArrayList<>();
        list.addAll(l);
        int bit = getBit(getMax(list));
        for(int b=1;b<=bit;b++){
            //计数排序
            List<Integer> counts = Arrays.asList(0,0,0,0,0,0,0,0,0,0);
            HashMap<Integer,List<Long>> map = new HashMap<>();
            for(int idx=0;idx<list.size();idx++){
                int countIdx = getBitVal(list.get(idx),b);
                counts.set(countIdx,counts.get(countIdx)+1);
                List<Long> tmp;
                if(!map.containsKey(countIdx)){
                    tmp = new ArrayList<>();
                    map.put(countIdx,tmp);
                }else{
                    tmp = map.get(countIdx);
                }
                tmp.add(list.get(idx));
            }
            list.clear();
            for(int idx = 0;idx<counts.size();idx++){
                if(counts.get(idx)>0){
                    list.addAll(map.get(idx));
                }
            }

        }
        System.out.println(list.toString());
    }

    private int getBit(long max){
        int bit = 1;
        while(max/10>0){
            bit ++;
            max=max/10;
        }
        return bit;
    }
    private long getMax(List<Long> source){
        long max = 0L;
        for(int i=0;i<source.size();i++){
            max = source.get(i)>max?source.get(i):max;
        }
        return max;
    }
    private int getBitVal(Long val ,Integer bit){
        for (int b=bit ;b>0;b--){
                val = val/10;
        }
        if(val>10) val=val%10;
        return val.intValue();
    }

    /**
     * 桶排序
     */
    private void bucketSort(){
        List<Long> l = new ArrayList<>();
        l.addAll(Arrays.asList(123L,365L,456L,871L,471L,189L,456L,823L));
        HashMap<Integer,List<Long>> buckets = new HashMap<>();
        for(Long i:l){
            int b=i.intValue()/100;
            if(!buckets.containsKey(b)){
                buckets.put(b,new ArrayList<>());
            }
            List<Long> bucket = buckets.get(b);
            bucket.add(i);
        }
        l.clear();
        for(List<Long> bucket :buckets.values()){
            insertSort(bucket);
            l.addAll(bucket);
        }

        System.out.println(l.toString());
    }

}
