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

// iterative
class Solution {
    public ListNode reverseList(ListNode head) {
        if (head == null) return null;
        ListNode curr = head;
        ListNode prev = null;
        while (head != null) {
            curr = head;
            head = head.next;
            curr.next = prev;
            prev = curr;
        }
        return prev;
    }
}

// recursion
class Solution {
    public ListNode reverseList(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode p = reverseList(head.next);
        head.next.next = head;
        head.next = null;
        return p;
    }
}