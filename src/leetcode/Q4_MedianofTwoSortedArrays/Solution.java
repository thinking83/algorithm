package leetcode.Q4_MedianofTwoSortedArrays;


/**
 * https://leetcode.com/problems/median-of-two-sorted-arrays/#/description
 * 时间复杂度O(log(m+n))
 * 没有求出解
 * Created by minghua.zmh on 2017/5/4.
 */
public class Solution {
    public double findMedianSortedArrays(int[] num1, int[] num2) {
        int[] ret = new int[2];
        int len1 = num1.length;
        int len2 = num2.length;
        if(len1==0){
            ret = getMedian(num2,0,len2);
            return (ret[0]+ret[1])/2.0;
        }
        if(len2==0){
            ret = getMedian(num1,0,len1);
            return (ret[0]+ret[1])/2.0;
        }

        //两个数组项数和是否为偶数
        boolean evenFLag = (len1+len2)%2==0;
        int index1 = evenFLag?(len1+len2)/2-1:(len1+len2)/2;
        int index2 = evenFLag?(len1+len2)/2:(len1+len2)/2;

        int loop = (int)(Math.floor(Math.log(len1+len2)));
        for(int i=0;i<loop;i++){

        }
        return 0;
    }

    private int[] getMedian(int[] num,int start,int end){
        int[] ret = new int[2];
        if((end-start)%2 == 0){
            ret[0] = num[start+(end-start)/2-1];
            ret[1] = num[start+(end-start)/2];
        }else{
            ret[0] = ret[1] = end-start>1?num[start+(end-start)/2]:num[start];
        }
        return ret;
    }

    public static void main(String[] args){

        new Solution().findMedianSortedArrays(new int[]{},new int[]{3,4});
    }
}


