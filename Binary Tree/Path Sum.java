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

// recursion
class Solution {
    public boolean hasPathSum(TreeNode root, int targetSum) {
        if (root == null) return false;
        return dfs(targetSum, root);
    }
    
    private boolean dfs(int targetSum, TreeNode root) {
        targetSum = targetSum - root.val;
        if (root.left == null && root.right == null && 0 == targetSum) return true;
        boolean result = false;
        if (root.left != null) {
            result = dfs(targetSum, root.left);
        }
        if (root.right != null) {
            result =  result || dfs(targetSum, root.right);
        }
        return result;
    }
}

// recursion. improved version of above.
class Solution {
  public boolean hasPathSum(TreeNode root, int sum) {
    if (root == null)
      return false;

    sum -= root.val;
    if ((root.left == null) && (root.right == null))
      return (sum == 0);
    return hasPathSum(root.left, sum) || hasPathSum(root.right, sum);
  }
}

// Stack + DFS. Two stacks to maintian curr node and targetSum respectively.
class Solution {
  public boolean hasPathSum(TreeNode root, int sum) {
    if (root == null)
      return false;

    LinkedList<TreeNode> node_stack = new LinkedList();
    LinkedList<Integer> sum_stack = new LinkedList();
    node_stack.add(root);
    sum_stack.add(sum - root.val);

    TreeNode node;
    int curr_sum;
    while (!node_stack.isEmpty()) {
      node = node_stack.pollLast();
      curr_sum = sum_stack.pollLast();
      if ((node.right == null) && (node.left == null) && (curr_sum == 0))
        return true;

      if (node.right != null) {
        node_stack.add(node.right);
        sum_stack.add(curr_sum - node.right.val);
      }
      if (node.left != null) {
        node_stack.add(node.left);
        sum_stack.add(curr_sum - node.left.val);
      }
    }
    return false;
  }
}

// One stack. Using a pre to determine whether to backtrack or go right.
public boolean hasPathSum(TreeNode root, int target) {
        Stack<TreeNode> stack = new Stack<>();
        TreeNode pre = null, curr = root;
        int sum = 0;
        
        while (curr != null || !stack.isEmpty()) {
            while (curr != null) {
                stack.push(curr);
                sum += curr.val;
                curr = curr.left;
            }
            curr = stack.peek();
            if (curr.left == null && curr.right == null && sum == target)
                return true;
            if (curr.right != null && pre != curr.right)
                curr = curr.right;
            else {
                pre = curr;
                stack.pop();
                sum -= curr.val;
                curr = null;
            }
        }
        
        return false;
    }