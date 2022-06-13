class Solution {
    public ListNode deleteDuplicates(ListNode head) {
        if (head == null) return null;
        ListNode pre = head;
        ListNode cur = head.next;
        while (cur != null) {
            if (cur.val == pre.val) {
                while (cur != null && cur.val == pre.val) {
                    cur = cur.next;
                }
                pre.next = cur;                
            }
            pre = cur;
            if (cur != null) cur = cur.next;
        }
        return head;
    }
}

public ListNode deleteDuplicates(ListNode head) {
    ListNode current = head;
    while (current != null && current.next != null) {
        if (current.next.val == current.val) {
            current.next = current.next.next;
        } else {
            current = current.next;
        }
    }
    return head;
}