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

// Time complexity O(n^2). Time Limit Exceeded.
class Solution {
    public ListNode sortList(ListNode head) {
        if (head == null) return null;
        ListNode dummy = new ListNode(-1);
        while (head != null) {
            ListNode node = head;
            head = head.next;
            ListNode pre = dummy;
            while (pre.next != null && pre.next.val < node.val) {
                pre = pre.next;
            }
            node.next = pre.next;
            pre.next = node;
        }
        return dummy.next;
    }
}

// using priority queue. not constant space.
class Solution {
    public ListNode sortList(ListNode head) {
        if (head == null) return null;
        ListNode dummy = new ListNode(-1);
        HashMap<Integer, ListNode> map = new HashMap();
        PriorityQueue<ListNode> q = new PriorityQueue<>((x, y) -> {
            // since you want to sort by highest value first
            return Integer.compare(x.val, y.val); 
        });          
        while (head != null) {
            q.add(head);
            head = head.next;
        }
        ListNode node = dummy;
        while (!q.isEmpty()) {
            node.next = q.poll();
            node = node.next;
            node.next = null;
        }
        
        return dummy.next;
    }
}


// merge sort.
class Solution {
    public ListNode sortList(ListNode head) {
        if (head == null || head.next == null)
            return head;
        ListNode mid = getMid(head);
        ListNode left = sortList(head);
        ListNode right = sortList(mid);
        return merge(left, right);
    }

    ListNode merge(ListNode list1, ListNode list2) {
        ListNode dummyHead = new ListNode();
        ListNode tail = dummyHead;
        while (list1 != null && list2 != null) {
            if (list1.val < list2.val) {
                tail.next = list1;
                list1 = list1.next;
                tail = tail.next;
            } else {
                tail.next = list2;
                list2 = list2.next;
                tail = tail.next;
            }
        }
        tail.next = (list1 != null) ? list1 : list2;
        return dummyHead.next;
    }

    ListNode getMid(ListNode head) {
        ListNode midPrev = null;
        while (head != null && head.next != null) {
            midPrev = (midPrev == null) ? head : midPrev.next;
            head = head.next.next;
        }
        ListNode mid = midPrev.next;
        midPrev.next = null;
        return mid;
    }
}

