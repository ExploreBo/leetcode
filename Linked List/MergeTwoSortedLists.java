/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
class Solution {
    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        ListNode result = new ListNode();
        ListNode index = result;
        while (list1 != null || list2 != null) {
            if (list1 == null) {
                index.next = new ListNode(list2.val);
                index = index.next;
                list2 = list2.next;
            } else if (list2 == null) {
                index.next = new ListNode(list1.val);
                index = index.next;
                list1 = list1.next;
            } else {
                if (list1.val < list2.val) {
                    index.next = new ListNode(list1.val);
                    index = index.next;
                    list1 = list1.next;                    
                } else {
                    index.next = new ListNode(list2.val);
                    index = index.next;
                    list2 = list2.next;                    
                } 
            }
        }
        return result.next;
    }
}