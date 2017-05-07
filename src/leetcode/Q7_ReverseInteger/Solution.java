package leetcode.Q7_ReverseInteger;

/**
 * https://leetcode.com/problems/reverse-integer/#/description
 * 没有求出解
 * Created by minghua.zmh on 2017/5/4.
 */
public class Solution {
    public int reverse(int x) {
        if(x==0) return 0;
        int ret = 0;
        while(x!=0){

            int newRet = ret*10+x%10;

            if (newRet/10 != ret){ return 0; }
            ret = newRet;
            x = x/10;
        }
        return ret;
    }

    public static void main(String[] args){

        Integer s = new Solution().reverse(32100123);
        System.out.println(s);
    }
}


