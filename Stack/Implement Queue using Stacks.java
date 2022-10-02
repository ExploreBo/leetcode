class MyQueue {
    Stack<Integer> getStack;
    Stack<Integer> pushStack;

    public MyQueue() {
        this.getStack = new Stack();
        this.pushStack = new Stack();        
    }
    
    public void push(int x) {
        this.pushStack.push(x);
    }
    
    public int pop() {
        if (getStack.isEmpty()) {
            while (!pushStack.isEmpty()) {
                getStack.push(pushStack.pop());
            }
        }
        return getStack.pop();        
    }
    
    public int peek() {
        if (getStack.isEmpty()) {
            while (!pushStack.isEmpty()) {
                getStack.push(pushStack.pop());
            }
        }
        return getStack.peek();
    }
    
    public boolean empty() {
        return getStack.isEmpty() && pushStack.isEmpty();
    }
}

/**
 * Your MyQueue object will be instantiated and called as such:
 * MyQueue obj = new MyQueue();
 * obj.push(x);
 * int param_2 = obj.pop();
 * int param_3 = obj.peek();
 * boolean param_4 = obj.empty();
 */