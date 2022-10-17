class Solution {
    public int evalRPN(String[] tokens) {
        Stack<Integer> stack = new Stack();
        Set<String> operator = new HashSet();
        operator.addAll(Arrays.asList("+", "-", "/", "*"));
        for (int i = 0; i < tokens.length; ++i) {   
            if (operator.contains(tokens[i])) {
                Integer operator2 = stack.pop();
                Integer operator1 = stack.pop();
                if (tokens[i].equals("+")) {
                    operator1 += operator2;
                } else if (tokens[i].equals("-")) {
                    operator1 -= operator2;
                } else if (tokens[i].equals("*")) {
                    operator1 *= operator2;
                } else {
                    operator1 /= operator2;
                }
                stack.push(operator1);
            } else {    
                stack.push(Integer.parseInt(tokens[i]));
            }
        }
        return stack.peek();
    }
}