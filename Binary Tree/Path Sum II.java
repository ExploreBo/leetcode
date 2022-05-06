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

/*
Recursion. 
DPS traversal and maintain two things along the way:

1. running sum of all the nodes traversed till that point in recursion.
2. list of all those nodes
*/
class Solution { 
    private void recurseTree(TreeNode node, int remainingSum, List<Integer> pathNodes, List<List<Integer>> pathsList) {
        
        if (node == null) {
            return;
        }
        
        // Add the current node to the path's list
        pathNodes.add(node.val);
        
        // Check if the current node is a leaf and also, if it
        // equals our remaining sum. If it does, we add the path to
        // our list of paths
        if (remainingSum == node.val && node.left == null && node.right == null) {
            pathsList.add(new ArrayList<>(pathNodes));
        } else {
            
            // Else, we will recurse on the left and the right children
            this.recurseTree(node.left, remainingSum - node.val, pathNodes, pathsList);
            this.recurseTree(node.right, remainingSum - node.val, pathNodes, pathsList);
        }
        
        // We need to pop the node once we are done processing ALL of it's
        // subtrees.
        pathNodes.remove(pathNodes.size() - 1);
    }
    
    public List<List<Integer>> pathSum(TreeNode root, int sum) {
        List<List<Integer>> pathsList = new ArrayList<List<Integer>>();
        List<Integer> pathNodes = new ArrayList<Integer>();
        this.recurseTree(root, sum, pathNodes, pathsList);
        return pathsList;        
    }
}






/*
Inspired by one of the solutions in Lowest Common Ancestor. 
Using hashmap to track the ancestor, running sum and everything.

*/
class Solution {
    public List<List<Integer>> pathSum(TreeNode root, int targetSum) {
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        if (root == null) {
            return result;
        }
        // Stack for tree traversal
        Stack<TreeNode> stack = new Stack<>();

        // HashMap for parent pointers
        Map<TreeNode, TreeNode> parent = new HashMap<>();
        Map<TreeNode, Integer> sum = new HashMap();
        Set<TreeNode> set = new HashSet();

        parent.put(root, null);
        sum.put(root, root.val);
        stack.push(root);

        // Iterate until we find both the nodes p and q
        while (!stack.isEmpty()) {
            TreeNode node = stack.pop();
            if (node.left == null && node.right == null && targetSum == sum.get(node)) {
                set.add(node);
                continue;
            }

            // While traversing the tree, keep saving the parent pointers.
            if (node.left != null) {
                parent.put(node.left, node);
                sum.put(node.left, node.left.val + sum.get(node));
                stack.push(node.left);
            }
            if (node.right != null) {
                parent.put(node.right, node);
                sum.put(node.right, node.right.val + sum.get(node));
                stack.push(node.right);
            }
        }
        
        for (TreeNode node : set) {
            List<Integer> ancestors = new ArrayList();
            while (node != null) {
                ancestors.add(0, node.val);
                node = parent.get(node);
            }
            result.add(ancestors);
        }
        return result;
    }  
}