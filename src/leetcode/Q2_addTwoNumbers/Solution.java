package leetcode.Q2_addTwoNumbers;

/**
 * https://leetcode.com/problems/add-two-numbers/#/description
 * 使用哨兵节点，简化判断；链表的操作；边界
 * @link https://leetcode.com/articles/two-sum/
 * 时间复杂度O(max(m,n)) 空间复杂度O(max(m,n))
 * Created by minghua.zmh on 2017/5/4.
 */
public class Solution {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode l3 = new ListNode(0);
        int up = 0;
        ListNode flag = l3;
        int i=0;
        while(true||i>100){
            int num = up;
            i++;
            if(l1 == null && l2==null && num==0){
                break;
            }
            if(l1 != null){
                num += l1.val;
                l1 = l1.next;
            }
            if(l2 != null){
                num += l2.val;
                l2 = l2.next;
            }

            flag.next = new ListNode(up);
            flag = flag.next;

            flag.val = num%10;
            up = num/10;

        }
        return l3.next;
    }
    public void init(){
        ListNode l1 = new ListNode(0);
//        l1.next = new ListNode(8);
        ListNode l2 = new ListNode(0);
//        l2.next = new ListNode(9);
        addTwoNumbers(l1,l2);
    }
    public static void main(String[] args){

        new Solution().init();
    }

    public class ListNode {
        int val;
        ListNode next;
        ListNode(int x) { val = x; }
    }
}


