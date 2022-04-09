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
// recursion, use null here to avoid the case that the val of the tree is Integer.MIN_VALUE or Integer.MAX_VALUE
class Solution {
    public boolean isValidBST(TreeNode root) {
        return helper(root, null, null);
    }
    
    boolean helper(TreeNode root, Integer min, Integer max) {
        if (root == null)
            return true;
        
        if ((min != null && root.val <= min) || (max != null && root.val >= max))
            return false;
        
        return helper(root.left, min, root.val) && helper(root.right, root.val, max);
    }
}


// non-recursion, use Long.MIN_VALUE to avoid the Integer.MIN_VALUE problem
class Solution {
    public boolean isValidBST(TreeNode root) {
        Stack<TreeNode> stack = new Stack();
        while (root != null) {
            stack.push(root);
            root = root.left;
        }
        long currMin = Long.MIN_VALUE;
        while (!stack.isEmpty()) {
            TreeNode node = stack.pop();
            if (node.val <= currMin) {
                return false;
            }
            currMin = node.val;
            node = node.right;
            while (node != null) {
                stack.push(node);
                node = node.left;
            }
        }
        return true;
    }
}


// non-recursion, use a node as pre, this could avoid MIN_VALUE case
class Solution {
    public boolean isValidBST(TreeNode root) {
       Stack<TreeNode> stack = new Stack<TreeNode> ();
       TreeNode cur = root ;
       TreeNode pre = null ;           
       while (!stack.isEmpty() || cur != null) {               
           if (cur != null) {
               stack.push(cur);
               cur = cur.left ;
           } else {                
               TreeNode p = stack.pop() ;
               if (pre != null && p.val <= pre.val) {                      
                   return false ;
               }                   
               pre = p ;                       
               cur = p.right ;
           }
       }
       return true ; 
    }
}