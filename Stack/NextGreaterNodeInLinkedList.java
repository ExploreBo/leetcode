/*
Problem: https://leetcode.com/problems/next-greater-node-in-linked-list



*/

// Original Solution
class Solution {
    public int[] nextLargerNodes(ListNode head) {
        Map<ListNode, Integer> map = new HashMap();
        int count = 0;
        ListNode curr = head;
        while (curr != null) {
            map.put(curr, count);
            count++;
            curr = curr.next;
        }
        int[] result = new int[count];
              
        Stack<ListNode> stack = new Stack();
        while (head != null) {
            while (!stack.isEmpty()) {
                if (stack.peek().val < head.val) {
                    result[map.get(stack.pop())] = head.val;
                } else {
                    break;
                }
                
            }
            stack.push(head);
            head = head.next;
        }
        while (!stack.isEmpty()) {
            result[map.get(stack.pop())] = 0;
        }
        return result;
    }
}



/* Improved Solution
1. Traverse the node and generate a new ArrayList which is better than a Map.
2. Push index into stack instead of value
*/
class Solution {
    public int[] nextLargerNodes(ListNode head) {
        ArrayList<Integer> list = new ArrayList<>();
        ListNode curr = head;
        while (curr !=null) {
            list.add(curr.val);
            curr = curr.next;
        }

        int[] result = new int[list.size()];
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < list.size(); ++i) {
            while (!stack.isEmpty() && list.get(stack.peek()) < list.get(i))
                result[stack.pop()] = list.get(i);
            stack.push(i); // push index instead of the value
        }
        return result;
    }
}