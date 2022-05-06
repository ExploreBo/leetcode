/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */

/*
Run DFS for both p and q values and create paths from root to p and q. T
hese paths can be stored in lists. 
Since we need lowest common ancestor, 
we can check from the front for the last such node which is common in both the lists.
*/
class Solution {
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null || p == null || q == null) return root;
        List<TreeNode> pList = new ArrayList();
        List<TreeNode> qList = new ArrayList();       
        dfs(pList, root, p);
        dfs(qList, root, q);
        int i = Math.min(pList.size(), qList.size()) - 1;
        while (i >= 0) {
            if (pList.get(i).val == qList.get(i).val) {
                break;
            }
            i--;
        }
        return pList.get(i);
    }
    
    private void dfs(List<TreeNode> list, TreeNode root, TreeNode node) {
        if (root == null) return;
        if (list.size() > 1 && list.get(list.size() - 1) == node) return;
        list.add(root);
        if (root == node) {
            return;
        }
        dfs(list, root.left, node);
        dfs(list, root.right, node);    
        if (list.get(list.size() - 1) != node) {
            list.remove(list.size() - 1);   
        }        
    }
}

/*
Stack + DFS until find both p and q.
Use hashmap to store ancestor of each node.
*/
class Solution {

    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        // Stack for tree traversal
        Stack<TreeNode> stack = new Stack<>();

        // HashMap for parent pointers
        Map<TreeNode, TreeNode> parent = new HashMap<>();

        parent.put(root, null);
        stack.push(root);

        // Iterate until we find both the nodes p and q
        while (!parent.containsKey(p) || !parent.containsKey(q)) {

            TreeNode node = stack.pop();

            // While traversing the tree, keep saving the parent pointers.
            if (node.left != null) {
                parent.put(node.left, node);
                stack.push(node.left);
            }
            if (node.right != null) {
                parent.put(node.right, node);
                stack.push(node.right);
            }
        }

        // Ancestors set() for node p.
        Set<TreeNode> ancestors = new HashSet<>();

        // Process all ancestors for node p using parent pointers.
        while (p != null) {
            ancestors.add(p);
            p = parent.get(p);
        }

        // The first ancestor of q which appears in
        // p's ancestor set() is their lowest common ancestor.
        while (!ancestors.contains(q))
            q = parent.get(q);
        return q;
    }
} 
