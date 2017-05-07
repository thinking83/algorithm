package leetcode.Q14_LongestCommonPrefix;

import java.util.HashMap;

/**
 * https://leetcode.com/problems/roman-to-integer/#/description
 * 可以有很多思路，归并和二分比较有趣。
 * Created by minghua.zmh on 2017/5/4.
 * https://leetcode.com/articles/longest-common-prefix/
 */
public class Solution {
    public String longestCommonPrefix(String[] strs) {
        String ret = "";
        if(strs.length==0) return ret;
        if(strs.length==1) return strs[0];
        for(int i=1;i<=strs[0].length();i++){
            ret = strs[0].substring(0,i);
            for(int j=1;j<strs.length;j++){
                if(i>strs[j].length() || !strs[j].startsWith(ret)){
                    return strs[0].substring(0,i-1);
                }
            }
        }
        return ret;
    }

    public static void main(String[] args){
        String[] a = {"a","a"};
        String s = new Solution().longestCommonPrefix(a);
        System.out.println(s);
    }
}


