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
    public ListNode removeElements(ListNode head, int val) {
        ListNode dummy = new ListNode(-1, head);
        ListNode pre = dummy;
        while (head != null) {
            if (head.val == val) {
                pre.next = head.next;
                head = head.next;
            } else {
                pre = pre.next;
                head = head.next;
            }
        }
        return dummy.next;
    }
}