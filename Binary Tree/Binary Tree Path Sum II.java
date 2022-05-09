/**
 * Definition of TreeNode:
 * public class TreeNode {
 *     public int val;
 *     public TreeNode left, right;
 *     public TreeNode(int val) {
 *         this.val = val;
 *         this.left = this.right = null;
 *     }
 * }
 */

public class Solution {
    /**
     * @param root: the root of binary tree
     * @param target: An integer
     * @return: all valid paths
     *          we will sort your return value in output
     */
    public List<List<Integer>> binaryTreePathSum2(TreeNode root, int target) {
        List<List<Integer>> result = new ArrayList<>();
        if (root != null) {
            helper(root, target, new ArrayList<Integer>(), result);
        }
        return result;
    }

    private void helper(TreeNode root, int target, List<Integer> path, List<List<Integer>> result) {
        if (root == null) {
            return;
        }
        path.add(root.val);
        int sum = 0;
        for (int i = path.size() - 1; i >= 0; i--) {
            sum += path.get(i);
            if (sum == target) {
                result.add(new ArrayList<Integer>(path.subList(i, path.size())));
            }
        }
        helper(root.left, target, path, result);
        helper(root.right, target, path, result);
        path.remove(path.size() - 1);
    }
}