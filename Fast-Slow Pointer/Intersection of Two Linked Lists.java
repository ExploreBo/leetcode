/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) {
 *         val = x;
 *         next = null;
 *     }
 * }
 */
// hashSet, time complexity O(m+n), space complexity O(m)
public class Solution {
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        Set<ListNode> set = new HashSet();
        while (headA != null) {
            set.add(headA);
            headA = headA.next;
        }
        while (headB !=null) {
            if (set.contains(headB)) {
                return headB;
            } 
            headB = headB.next;
        }
        return null;
    }
}


// get the lengths of two lists first, let the longer one go first until the rest of it equals the shorter one's size.
public class Solution {
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        int nA = 0;
        int nB = 0;
        ListNode pA = headA;
        ListNode pB = headB;
        while (pA != null) {
            pA = pA.next;
            nA++;
        }
        while (pB != null) {
            pB = pB.next;
            nB++;
        }   
        int diff = Math.abs(nA - nB);
        if (nA < nB) {
            while (diff != 0) {
                headB = headB.next;
                diff--;
            }
        } else {
            while (diff != 0) {
                headA = headA.next;
                diff--;
            }
        }
        while (true) {
            if (headA == null || headB == null) {
                return null;
            }
            if (headA == headB) {
                return headA;
            }
            headA = headA.next;
            headB = headB.next;
        } 
    }
}


// walk in a circle: A list's non-intersected size is a, B list's non-intersected size is b, the intersection size is c
// a + c + b = b + c + a. in the end they will all reach the intersection point
// if no intersection: a + b = b + a, they will all reach null. so just return pa
public class Solution {
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        ListNode pA = headA;
        ListNode pB = headB;
        while (pA != pB) {
            pA = pA == null ? headB : pA.next;
            pB = pB == null ? headA : pB.next;
        }
        return pA;
        // Note: In the case lists do not intersect, the pointers for A and B
        // will still line up in the 2nd iteration, just that here won't be
        // a common node down the list and both will reach their respective ends
        // at the same time. So pA will be NULL in that case.
    }
}