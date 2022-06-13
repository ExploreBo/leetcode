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
    public ListNode reverseKGroup(ListNode head, int k) {
        if (k == 1 || head == null) return head;
        ListNode dummy = new ListNode(-1, head);
        ListNode pre = dummy;
        ListNode curr = head;
        while (curr != null) {
            ListNode node = curr;
            int count = 1;
            while (count < k) {
                if (node.next == null) {
                    pre.next = curr;
                    return dummy.next;
                } else {
                    node = node.next;
                }
                count++;
            }
            ListNode tmp = node.next;
            pre.next = reverseFirstKElements(curr, k);
            pre = curr;
            curr = tmp;
        }
        return dummy.next;
    }
    
    private ListNode reverseFirstKElements(ListNode head, int k) {
        ListNode tail = head;
        ListNode curr = head;
        ListNode next = head.next;
        ListNode prev = next.next;
        int count = 1;
        while (count != k) {
            next.next = curr;
            curr = next;
            next = prev;
            if (prev != null) {
                prev = prev.next;    
            }
            count++;
        }
        tail.next = next;
        return curr;
    }    
}