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

/* There might exist multiple answers as postorder and preorder could not lead to a only answer.

Example:
         A                             A
        /                               \
       B                                 B     

Pre:   A->B                            A->B
Post:  B->A                            B->A
*/
class Solution {
    public TreeNode constructFromPrePost(int[] preorder, int[] postorder) {
        return buildTree(preorder, postorder, 0, preorder.length - 1, 0, postorder.length - 1);
    }
    
    private TreeNode buildTree(int[] preorder, int[] postorder, int preorderStart, int preorderEnd, int postorderStart, int postorderEnd) {
        if (preorderStart > preorderEnd) {
            return null;
        }
        if (preorderStart == preorderEnd) {
            return new TreeNode(preorder[preorderStart]);
        }        
        TreeNode root = new TreeNode(preorder[preorderStart]);
        int leftLength = 0;
        while (true) {
            if (preorder[preorderStart + 1] == postorder[postorderStart + leftLength++]) {
                break;
            }
        }
        root.left = buildTree(preorder, postorder, preorderStart + 1, preorderStart + leftLength, postorderStart, postorderStart + leftLength - 1);
        root.right = buildTree(preorder, postorder, preorderStart + 1 + leftLength, preorderEnd, postorderStart + leftLength, postorderEnd - 1);
        return root;
    }
}