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


// use addFirst or addLast based on the level (current size of the list to be returned)s
class Solution {
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        Queue<TreeNode> queue = new LinkedList();
        queue.add(root);
        while (!queue.isEmpty()) {
            int count = queue.size();
            LinkedList<Integer> temp = new LinkedList();
            while (count != 0) {
                TreeNode node = queue.poll();
                if (node != null) {
                    if (result.size() % 2 == 0) {
                        temp.addLast(node.val);    
                    } else {
                        temp.addFirst(node.val);
                    }
                    queue.add(node.left);
                    queue.add(node.right);                    
                }
                count--;
            }
            if (!temp.isEmpty()) {
                result.add(temp);                
            }
        }
        return result;        
    }
}