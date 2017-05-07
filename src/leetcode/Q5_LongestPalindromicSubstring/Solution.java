package leetcode.Q5_LongestPalindromicSubstring;


import java.util.HashSet;
import java.util.Set;

/**
 * https://leetcode.com/problems/median-of-two-sorted-arrays/#/description
 * 时间复杂度O(log(m+n))
 * 没有求出解
 * Created by minghua.zmh on 2017/5/4.
 */
public class Solution {
    public String longestPalindrome(String s) {
        System.out.println(s.length());
        //当前回文
        int start = 0;
        //当前回文长度
        int len = 0;
        //回文字符数
        Set<Character> set = new HashSet<>();

        //最大回文长度
        int maxStart =0;
        int maxLen = 0;


        for(int i=1;i<s.length();i++){
            //之前没有回文
            if(len==0){
                if(s.charAt(i)==s.charAt(i-1)){
                    start = i-1;
                    len = 2;
                    set.add(s.charAt(i));
                }else if(i>1 && s.charAt(i)==s.charAt(i-2)){
                    start = i-2;
                    len = 3;
                    set.add(s.charAt(i));
                    set.add(s.charAt(i-1));
                }
            }
            //当前回文不为空
            else if(len>0){
                //延长当前回文 多字符回文
                if(start-1>=0 && s.charAt(i)==s.charAt(start-1)) {
                    start = start - 1;
                    len = len + 2;
                    set.add(s.charAt(i));
                //延长当前回文 单字符回文
                }else if( s.charAt(i)==s.charAt(start) && set.size()==1){
                    len = len + 1;
                //重新开启 单字符回文
                }else if(s.charAt(i)==s.charAt(i-1)) {
                    start = i - 1;
                    len = 2;
                    set.clear();
                    set.add(s.charAt(i));
                //重新开启 多字符回文
                }else if(s.charAt(i)==s.charAt(i-2)) {
                    if(set.size()==2 && len>3 && s.charAt(i)==s.charAt(i-2)&&s.charAt(i)==s.charAt(i-4)){
                        start = start + 1;
                    }else{
                        start = i - 2;
                        len = 3;
                    }
                }else{//终止回文
                    start = 0;
                    len = 0;
                    set.clear();
                }
            }
            //赋值最大回文
            if(len>maxLen){
                maxLen = len;
                maxStart = start;
            }
        }
        if(maxLen==0){
            return s;
        }else {
            return s.substring(maxStart, maxStart+maxLen);
        }

    }

    public static void main(String[] args){

        String s = new Solution().longestPalindrome("32100123");
        System.out.println(s);
        System.out.println(s.length());
    }
}


