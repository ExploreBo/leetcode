class MinStack {
    ListNode node;
    ListNode head;
    int min;
    boolean isMinTrusted;

    public MinStack() {
        head = new ListNode(0);
        head.next = node;
        min = Integer.MAX_VALUE;
        isMinTrusted = true;
    }
    
    public void push(int val) {
        if (isMinTrusted == true && val < min) {
            min = val;
        }
        ListNode newNode = new ListNode(val);
        newNode.next = head.next;
        head.next = newNode;
    }
    
    public void pop() {
        if (head.next != null) {
            if (min == head.next.value) {
                isMinTrusted = false;
            }
            head.next = head.next.next;
        }
    }
    
    public int top() {
        return head.next.value;
    }
    
    public int getMin() {
        if (isMinTrusted == true) {
            return min;
        } else {
            int result = Integer.MAX_VALUE;
            ListNode curr = head.next;
            while (curr != null) {
                if (curr.value < result) {
                    result = curr.value;
                }
                curr = curr.next;
            }
            isMinTrusted = true;
            min = result;
            return result;
        }
    }
    
    private class ListNode {
        int value;
        ListNode next;
        
        ListNode(int value) {
            this.value = value;
            this.next = null;
        }
    }
}


// Solution two: store min at every Node
class MinStack {
    private Node head;
        
    public void push(int x) {
        if (head == null) 
            head = new Node(x, x, null);
        else 
            head = new Node(x, Math.min(x, head.min), head);
    }
    
    public void pop() {
        head = head.next;
    }
    
    public int top() {
        return head.val;
    }
    
    public int getMin() {
        return head.min;
    }
        
    private class Node {
        int val;
        int min;
        Node next;
            
        private Node(int val, int min, Node next) {
            this.val = val;
            this.min = min;
            this.next = next;
        }
    }
}


// Solution 3: push min everytime and pop twice to update min
Stack<Integer> stack=new Stack<>();
int min=Integer.MAX_VALUE;
public void push(int x) {
    if(x<=min) {stack.push(min); min=x;}
    stack.push(x);
}
public void pop() {
    if(stack.peek()==min){ stack.pop(); min=stack.pop(); }
    else stack.pop();
}
public int top() {
    return stack.peek();
}
public int getMin() {
    return min;
}











