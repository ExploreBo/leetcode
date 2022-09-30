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


// Solution 2. iterative

class Solution {
    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        ListNode result = new ListNode();
        ListNode curr = result;
        while (list1 != null && list2 != null) {
            if (list1.val < list2.val) {
                curr.next = list1;
                list1 = list1.next;
                curr = curr.next;
            } else {
                curr.next = list2;
                list2 = list2.next;
                curr = curr.next;
            }
        }
        curr.next = list1 == null ? list2 : list1;
        return result.next;
    }
}

// Solution 3. Recursion
class Solution {
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        if (l1 == null) {
            return l2;
        }
        else if (l2 == null) {
            return l1;
        }
        else if (l1.val < l2.val) {
            l1.next = mergeTwoLists(l1.next, l2);
            return l1;
        }
        else {
            l2.next = mergeTwoLists(l1, l2.next);
            return l2;
        }

    }
}
