package leetcode.Q13_RomanToInteger;

import java.util.HashMap;

/**
 * https://leetcode.com/problems/roman-to-integer/#/description
 * 了解罗马数字的计数方式
 * Created by minghua.zmh on 2017/5/4.
 *  String[][] c= {
 {"","I","II","III","IV","V","VI","VII","VIII","IX"},
 {"","X","XX","XXX","XL","L","LX","LXX","LXXX","XC"},
 {"","C","CC","CCC","CD","D","DC","DCC","DCCC","CM"},
 {"","M","MM","MMM"}
 };
 */
public class Solution {
    public int romanToInt(String s) {
        HashMap<Character,Integer> map = new HashMap();
        map.put('M',1000);
        map.put('D',500);
        map.put('C',100);
        map.put('L',50);
        map.put('X',10);
        map.put('V',5);
        map.put('I',1);

        int ret = map.get(s.charAt(s.length()-1));
        for(int i=0;i<s.length()-1;i++){
            if(map.get(s.charAt(i))<map.get(s.charAt(i+1))){
                ret -= map.get(s.charAt(i));
            }else {
                ret += map.get(s.charAt(i));
            }
        }
        return ret;
    }

    public static void main(String[] args){

        Integer s = new Solution().romanToInt("MMMDLXXXVI");
        System.out.println(s);
    }
}


