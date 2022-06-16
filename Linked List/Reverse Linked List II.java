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

// initial version
class Solution {
    public ListNode reverseBetween(ListNode head, int left, int right) {
        if (left == right) return head; 
        if (left == 1) {
            return reverseFirstKElements(head, right - left + 1);
        } else {
            ListNode prev = head;
            ListNode curr = head.next;
            int count = 2;
            while (count != left) {
                prev = curr;
                curr = curr.next;
                count++;
            }
            prev.next = reverseFirstKElements(curr, right - left + 1);
            return head;
        }
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

// simpler version
class Solution {
    public ListNode reverseBetween(ListNode head, int left, int right) {
        ListNode fakeHead = new ListNode(-1, head);
        ListNode prev = fakeHead;
        ListNode curr = fakeHead.next;
        int i = 1;
        while (i < left) {
            prev = curr;
            curr = curr.next;
            i++;
        }
        ListNode node = prev;
        while (i <= right) {
            ListNode tmp = curr.next;
            curr.next = prev;
            prev = curr;
            curr = tmp;
            i++;
        }
        node.next.next = curr;
        node.next = prev;
        return fakeHead.next;
    }
}