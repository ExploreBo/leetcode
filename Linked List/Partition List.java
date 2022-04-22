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
    public ListNode partition(ListNode head, int x) {
        ListNode dummyLeft = new ListNode();
        ListNode leftCurr = dummyLeft;
        ListNode dummyRight = new ListNode();
        ListNode rightCurr = dummyRight;
        while (head != null) {
            if (head.val < x) {
                leftCurr.next = head;
                leftCurr = leftCurr.next;
            } else {
                rightCurr.next = head;
                rightCurr = rightCurr.next;
            }
            head = head.next;
        }
        leftCurr.next = dummyRight.next;
        rightCurr.next = null;
        return dummyLeft.next;
    }
}