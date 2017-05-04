package leetcode.Q1_twosum;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * https://leetcode.com/problems/two-sum/#/description
 * 受三个数之和问题的影响。对array进行了排序，之后用双指针解决，复杂度为O(n*logn),空间复杂度为O(n)
 * 如果使用hash 时间复杂度O(n),空间复杂度O(n)
 * @link https://leetcode.com/articles/two-sum/
 *
 * Created by minghua.zmh on 2017/5/4.
 */
public class Solution {
    public int[] twoSum(int[] nums, int target) {
        int[] clone = Arrays.copyOf(nums,nums.length);
        Arrays.sort(nums);
        int len = nums.length;
        int i = 0;
        int j = len-1;
        while(i < j){
            int sum=nums[i]+nums[j];
            if(sum==target) break;
            else if(sum<target) i++;
            else j--;
        }
        int[] ret = new int[]{-1,-1};
        for(int idx=0;idx<len;idx++){
            if(clone[idx]==nums[i] && ret[0]==-1) {
                ret[0] = idx;
            }else if(clone[idx]==nums[j]){
                ret[1] = idx;
            }
        }
        return ret;
    }

    public int[] hash(int[] nums,int target){
        Map<Integer,Integer> map = new HashMap<>();

        for(int i = 0;i<nums.length;i++){
            int j = target-nums[i];
            if(map.containsKey(j)){
                return new int[]{map.get(j),i};
            }
            map.put(nums[i],i);
        }
        throw new IllegalArgumentException("不存在");
    }

    public static void main(String[] args){
        int[] nums = {3,2,2,4};
        int target = 4;
        new Solution().hash(nums,target);
    }
}
