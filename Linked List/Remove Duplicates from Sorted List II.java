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

// original solution 
class Solution {
    public ListNode deleteDuplicates(ListNode head) {
        if (head == null) return head;
        ListNode prev = head;
        ListNode curr = head.next;
        while (curr != null) {
            if (curr.val != prev.val) {
                if (curr.next != null && curr.next.val == curr.val) {
                    while (curr.next != null && curr.next.val == curr.val) {
                        curr = curr.next;
                    }
                    if (curr != null) {
                        prev.next = curr.next;
                        curr = curr.next;
                    } else {
                        prev.next = null;
                        curr = null;
                    }
                } else {
                    prev = curr;
                    curr = curr.next;
                }
            } else {
                while (curr.next != null && curr.next.val == curr.val) {
                    curr = curr.next;
                }
                if (curr == null) {
                    return null;
                } else {
                    head = curr.next;
                    prev = curr.next;
                    if (prev != null) {
                        curr = prev.next;
                    } else {
                        curr = null;
                    }
                } 
            }
        }
        return head;
    }
}

// sentinel head
class Solution {
    public ListNode deleteDuplicates(ListNode head) {
        // sentinel
        ListNode sentinel = new ListNode(0, head);

        // predecessor = the last node 
        // before the sublist of duplicates
        ListNode pred = sentinel;
        
        while (head != null) {
            // if it's a beginning of duplicates sublist 
            // skip all duplicates
            if (head.next != null && head.val == head.next.val) {
                // move till the end of duplicates sublist
                while (head.next != null && head.val == head.next.val) {
                    head = head.next;    
                }
                // skip all duplicates
                pred.next = head.next;     
            // otherwise, move predecessor
            } else {
                pred = pred.next;    
            }
                
            // move forward
            head = head.next;    
        }  
        return sentinel.next;
    }
}
