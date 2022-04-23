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
    public ListNode oddEvenList(ListNode head) {
        ListNode oddHead = new ListNode();
        ListNode oddCurr = oddHead;
        ListNode evenHead = new ListNode();
        ListNode evenCurr = evenHead;
        int count = 1;
        while (head != null) {
            if (count % 2 == 1) {
                oddCurr.next = head;
                oddCurr = oddCurr.next;
            } else {
                evenCurr.next = head;
                evenCurr = evenCurr.next;
            }
            head = head.next;
            count++;
        }
        oddCurr.next = evenHead.next;
        evenCurr.next = null;
        return oddHead.next;
    }
}

// do not need to maintain count
public class Solution {
    public ListNode oddEvenList(ListNode head) {
        if (head == null) return null;
        ListNode odd = head, even = head.next, evenHead = even;
        while (even != null && even.next != null) {
            odd.next = even.next;
            odd = odd.next;
            even.next = odd.next;
            even = even.next;
        }
        odd.next = evenHead;
        return head;
    }
}