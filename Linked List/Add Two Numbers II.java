// reverse the list and add. reverse back after adding.
class Solution {
    public ListNode reverseList(ListNode head) {
        ListNode last = null;
        while (head != null) {
            // keep the next node
            ListNode tmp = head.next;
            // reverse the link
            head.next = last;
            // update the last node and the current node
            last = head;
            head = tmp;    
        }    
        return last;
    }
    
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        // reverse lists
        l1 = reverseList(l1);
        l2 = reverseList(l2);
        
        ListNode head = null;
        int carry = 0;
        while (l1 != null || l2 != null) {
            // get the current values 
            int x1 = l1 != null ? l1.val : 0;
            int x2 = l2 != null ? l2.val : 0;
            
            // current sum and carry
            int val = (carry + x1 + x2) % 10;
            carry = (carry + x1 + x2) / 10;
            
            // update the result: add to front
            ListNode curr = new ListNode(val);
            curr.next = head;
            head = curr;
            
            // move to the next elements in the lists
            l1 = l1 != null ? l1.next : null;
            l2 = l2 != null ? l2.next : null;
        }
        // don't forget the final check of carry
        if (carry != 0) {
            ListNode curr = new ListNode(carry);
            curr.next = head;
            head = curr;
        }

        return head;
    }
}

// follow up. don't reverse the linked list. You can either use two stacks or a hashmap.

// 1. HashMap
class Solution {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        HashMap<ListNode, ListNode> map = new HashMap();
        while (l1.next != null) {
            map.put(l1.next, l1);
            l1 = l1.next;
        }
        while (l2.next != null) {
            map.put(l2.next, l2);
            l2 = l2.next;
        }

        int sum = 0;
        ListNode head = new ListNode(-1);
        while (l1 != null || l2 != null) {
            if (l1 != null) sum += l1.val;
            if (l2 != null) sum += l2.val;
            head.val = sum % 10;
            ListNode node = new ListNode(sum / 10);
            node.next = head;
            head = node;
            l1 = map.getOrDefault(l1, null);
            l2 = map.getOrDefault(l2, null);
            sum /= 10;
        }
        return head.val == 0 ? head.next : head;
    }
}

// 2. Two stacks.
public class Solution {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        Stack<Integer> s1 = new Stack<Integer>();
        Stack<Integer> s2 = new Stack<Integer>();
        
        while(l1 != null) {
            s1.push(l1.val);
            l1 = l1.next;
        };
        while(l2 != null) {
            s2.push(l2.val);
            l2 = l2.next;
        }
        
        int sum = 0;
        ListNode list = new ListNode(0);
        while (!s1.empty() || !s2.empty()) {
            if (!s1.empty()) sum += s1.pop();
            if (!s2.empty()) sum += s2.pop();
            list.val = sum % 10;
            ListNode head = new ListNode(sum / 10);
            head.next = list;
            list = head;
            sum /= 10;
        }
        
        return list.val == 0 ? list.next : list;
    }
}