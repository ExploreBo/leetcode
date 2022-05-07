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
class Solution {
    public TreeNode bstFromPreorder(int[] preorder) {
        if (preorder.length == 0) return null;
        return deserialize(preorder, 0, preorder.length - 1);
        
    }
    
    private TreeNode deserialize(int[] preorder, int start, int end) {
        int val = preorder[start];
        TreeNode root = new TreeNode(val);
        int length = 0;
        while (start + length + 1 <= end) {
            if (preorder[start + length + 1] < val) {
                length++;
            } else {
                break;
            }
        }
        if (start + length < preorder.length && length > 0) root.left = deserialize(preorder, start + 1, start + length);
        if (start + 1 + length <= end) root.right = deserialize(preorder, start + length + 1, end);
        return root;
    }    
}

// use lower and upper
class Solution {
  int idx = 0;
  int[] preorder;
  int n;

  public TreeNode helper(int lower, int upper) {
    // if all elements from preorder are used
    // then the tree is constructed
    if (idx == n) return null;

    int val = preorder[idx];
    // if the current element 
    // couldn't be placed here to meet BST requirements
    if (val < lower || val > upper) return null;

    // place the current element
    // and recursively construct subtrees
    idx++;
    TreeNode root = new TreeNode(val);
    root.left = helper(lower, val);
    root.right = helper(val, upper);
    return root;
  }

  public TreeNode bstFromPreorder(int[] preorder) {
    this.preorder = preorder;
    n = preorder.length;
    return helper(Integer.MIN_VALUE, Integer.MAX_VALUE);
  }
}