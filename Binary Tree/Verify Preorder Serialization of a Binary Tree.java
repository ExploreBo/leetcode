/*
The idea is straightforward : take the nodes one by one from preorder traversal, and compute the number of available slots. 
If at the end all available slots are used up, the preorder traversal represents the valid serialization.
*/
class Solution {
  public boolean isValidSerialization(String preorder) {
    // number of available slots
    int slots = 1;

    for(String node : preorder.split(",")) {
      // one node takes one slot
      --slots;

      // no more slots available
      if (slots < 0) return false;

      // non-empty node creates two children slots
      if (!node.equals("#")) slots += 2;
    }

    // all slots should be used up
    return slots == 0;
  }
}

// to optimize the space complexity
class Solution {
  public boolean isValidSerialization(String preorder) {
    // number of available slots
    int slots = 1;

    int n = preorder.length();
    for(int i = 0; i < n; ++i) {
      if (preorder.charAt(i) == ',') {
        // one node takes one slot
        --slots;

        // no more slots available
        if (slots < 0) return false;

        // non-empty node creates two children slots
        if (preorder.charAt(i - 1) != '#') slots += 2;
      }
    }

    // the last node
    slots = (preorder.charAt(n - 1) == '#') ? slots - 1 : slots + 1;
    // all slots should be used up
    return slots == 0;
  }
}

// indegrees nad outdegrees 
class Solution {
     public boolean isValidSerialization(String preorder) {
        String[] strs = preorder.split(",");
        int degree = -1;         // root has no indegree, for compensate init with -1
        for (int i = 0; i < strs.length; i++) {
            degree++;             // all nodes have 1 indegree (root compensated)
            if (degree > 0) {     // total degree should never exceeds 0
                return false;
            }      
            if (!str.equals("#")) {// only non-leaf node has 2 outdegree
                degree -= 2;
            }  
        }
        return degree == 0;
    }
}

// using stack
public class Solution {
    public boolean isValidSerialization(String preorder) {
        // using a stack, scan left to right
        // case 1: we see a number, just push it to the stack
        // case 2: we see #, check if the top of stack is also #
        // if so, pop #, pop the number in a while loop, until top of stack is not #
        // if not, push it to stack
        // in the end, check if stack size is 1, and stack top is #
        if (preorder == null) {
            return false;
        }
        Stack<String> st = new Stack<>();
        String[] strs = preorder.split(",");
        for (int pos = 0; pos < strs.length; pos++) {
            String curr = strs[pos];
            while (curr.equals("#") && !st.isEmpty() && st.peek().equals(curr)) {
                st.pop();
                if (st.isEmpty()) {
                    return false;
                }
                st.pop();
            }
            st.push(curr);
        }
        return st.size() == 1 && st.peek().equals("#");
    }
}