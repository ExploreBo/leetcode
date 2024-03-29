/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
// prefix Sum
class Solution {
    int count = 0;
    int k;
    HashMap<Integer, Integer> h = new HashMap();
    
    public void preorder(TreeNode node, int currSum) {
        if (node == null)
            return;
        
        // current prefix sum
        currSum += node.val;

        // here is the sum we're looking for
        if (currSum == k)
            count++;
        
        // number of times the curr_sum − k has occured already, 
        // determines the number of times a path with sum k 
        // has occured upto the current node
        count += h.getOrDefault(currSum - k, 0);
        
        // add the current sum into hashmap
        // to use it during the child nodes processing
        h.put(currSum, h.getOrDefault(currSum, 0) + 1);

        // process left subtree
        preorder(node.left, currSum);
        // process right subtree
        preorder(node.right, currSum);

        // remove the current sum from the hashmap
        // in order not to use it during 
        // the parallel subtree processing
        h.put(currSum, h.get(currSum) - 1);
    }    
            
    public int pathSum(TreeNode root, int sum) {
        k = sum;
        preorder(root, 0);
        return count;
    }
}


// solution of parent hashmap
class Solution {
    public int pathSum(TreeNode root, int targetSum) {
        if (root == null) return 0; 
        Stack<TreeNode> stack = new Stack();
        Map<TreeNode, TreeNode> map = new HashMap();
        int count = 0;
        stack.push(root);
        map.put(root, null);
        
        while (!stack.isEmpty()) {
            TreeNode node = stack.pop();
            if (node.left != null) {
                stack.push(node.left);
                map.put(node.left, node);
            }
            if (node.right != null) {
                stack.push(node.right);
                map.put(node.right, node);                
            }
        }
        
        for (TreeNode leaf : map.keySet()) {
            double sum = 0.0;
            while (leaf != null) {
                sum += leaf.val;
                if (sum == targetSum) {
                    count++;
                }                
                leaf = map.get(leaf);
            }
        }
        return count;
    }
}