import utils.Tools;

import java.util.Arrays;

/**
 * Created by minghua.zmh on 2017/4/13.
 */
public class Select {
    private Integer[] list = {10,2,9,4,5,6,3,8,7,1};
    public static void main(String[] main){
        Select m = new Select();
        m.partitionRandomizedSelect(7);
        m.maxAndMin();
        m.randomizedSelect(7);
    }

    /**
     * 上界2/3n
     */
    public void maxAndMin(){
        int len = list.length;
        int min;
        int max;
        int start = 0;
        int i= 0;
        if(len%2==0){
            if(list[0]>list[1]){
                min = list[1];
                max = list[0];
            }else{
                min = list[0];
                max = list[1];
            }
            start = 0;
            i = 1;
        }else{
            min = list[0];
            max = list[1];
            start = 1;
        }
        for(;i<len/2;i++){
            int vmin = list[start + i * 2];
            int vmax = list[start + i * 2 + 1];
            if(vmin>vmax) {
                int temp = vmax;
                vmax = vmin;
                vmin = temp;
            }
            min = min<vmin?min:vmin;
            max = max>vmax?max:vmax;
        }
        System.out.println("min:"+min);
        System.out.println("max:"+max);
    }

    /**
     * 寻找第i小的数 平局O(n) 最坏O(n^2)
     * @param i 第i小的数
     */
    public void randomizedSelect(int i){
        int end = list.length-1;
        int iV = fast(7,0,end);
        System.out.println("i:"+iV);
    }


    public int fast(int n,int start,int end){
        int iV = list[n];
        Tools.swap(list,n,end);
        int i;
        int last = end-1;
        for(i=start;i<last;i++){
            if(list[i]<=iV) continue;
            for(int j=last;j>i;j--){
                last --;
                if(list[j]<iV){
                    Tools.swap(list,i,j);
                    break;
                }
            }
        }
        if(list[i]<iV){
            i++;
        }
        if(i==n-1) return iV;
        Tools.swap(list,i,end);
        if(i<n){
            return fast(n,i+1,end);
        }else{
            return fast(n,start,i-1);
        }

    }

    /**
     * 寻找第i小的数 平局O(n) 最坏O(n)
     * 分区法 BFPRT算法
     * http://noalgo.info/466.html
     * @param i 第i小的数
     */
    public void partitionRandomizedSelect(int i){
        int iV = partitionRandomizedSelect(list,0,list.length,i);
        System.out.println("partitionRandomizedSelect:"+iV);
    }

    public <T extends Comparable<T>> T partitionRandomizedSelect(T[] a, int fromIndex, int toIndex, int id) {
        // 小于等于5个数，直接排序得到结果
        if (toIndex - fromIndex <= 5) {
            Arrays.sort(a, fromIndex, toIndex);
            return a[fromIndex + id - 1];
        }

        int t = fromIndex - 1; // 当前替换到前面的中位数的下标
        // 每5个进行处理
        for (int st = fromIndex, ed; (ed = st + 5) <= toIndex; st += 5) {
            Arrays.sort(a, st, ed); // 5个数的排序
            t++;
            Tools.swap(a, t, st + 2); // 将中位数替换到数组前面，便于递归求取中位数的中位数
        }

        int pivotId = (fromIndex + t) >> 1; // l到t的中位数的下标，作为主元的下标
        partitionRandomizedSelect(a, fromIndex, t, pivotId - fromIndex + 1);// 不关心中位数的值，保证中位数在正确的位置
        int m = partition(a, fromIndex, toIndex, pivotId), cur = m - fromIndex + 1;
        if (id == cur)
            return a[m]; // 刚好是第id个数
        else if (id < cur)
            return partitionRandomizedSelect(a, fromIndex, m, id);// 第id个数在左边
        else
            return partitionRandomizedSelect(a, m + 1, toIndex, id - cur); // 第id个数在右边
    }


    // 对数组a下标从l到r的元素进行划分
    private static <T extends Comparable<T>> int partition(T[] a, int l, int r, int pivotId) {
        // 以pivotId所在元素为划分主元
        Tools.swap(a, pivotId, --r);
        int j = l - 1; // 左边数字最右的下标
        for (int i = l; i < r; i++)
            if (a[i].compareTo(a[r]) <= 0)
                Tools.swap(a, ++j, i);
        Tools.swap(a, ++j, r);
        return j;
    }
}
