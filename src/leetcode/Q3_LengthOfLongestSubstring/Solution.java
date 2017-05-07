package leetcode.Q3_LengthOfLongestSubstring;

import java.util.HashMap;
import java.util.Map;

/**
 * https://leetcode.com/problems/longest-substring-without-repeating-characters/#/description
 * 注意边界
 * @link https://leetcode.com/articles/longest-substring-without-repeating-characters/
 * 时间复杂度O(max(n)) 空间复杂度O(min(n,k))
 * Created by minghua.zmh on 2017/5/4.
 */
public class Solution {
    public int lengthOfLongestSubstring(String s) {
        char[] chars = s.toCharArray();
        Map<Character,Integer> map = new HashMap<>();
        int ret = 0, start = 0;
        for(int i=0,len=chars.length;i<len;i++){
            if(map.containsKey(chars[i])){
                start = (map.get(chars[i])+1)>start?map.get(chars[i])+1:start;
            }
            ret = i-start+1>ret?i-start+1:ret;
            map.put(chars[i],i);
        }
        return ret;
    }

    public static void main(String[] args){

        new Solution().lengthOfLongestSubstring("ababcd");
    }
}


