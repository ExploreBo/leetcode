// Lowest Common Ancestor by having the ancestor mapping
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
    public String getDirections(TreeNode root, int startValue, int destValue) {
        Map<TreeNode, TreeNode> ancestor = new HashMap();
        // tree traveral to get the child to parent mappings and also find two nodes
        TreeNode start = null;
        TreeNode dest = null;
        Queue<TreeNode> queue = new LinkedList();
        queue.add(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            while (size-- > 0) {
                TreeNode cur = queue.poll();
                if (cur.val == startValue) {
                    start = cur;
                }
                if (cur.val == destValue) {
                    dest = cur;
                }
                if (cur.left != null) {
                    queue.add(cur.left);
                    ancestor.put(cur.left, cur);
                } 
                if (cur.right != null) {
                    queue.add(cur.right);
                    ancestor.put(cur.right, cur);
                }
            }
        }
        Set<TreeNode> destAncestors = new HashSet();
        destAncestors.add(dest);
        while (ancestor.containsKey(dest)) {
            dest = ancestor.get(dest);
            destAncestors.add(dest);
        }

        StringBuilder sb = new StringBuilder();
        while (start.val != root.val && !destAncestors.contains(start)) {
            sb.append("U");
            start = ancestor.get(start);
        }
        while (start.val != destValue) {
            if (destAncestors.contains(start.left)) {
                sb.append("L");
                start = start.left;
            } else {
                sb.append("R");
                start = start.right;
            }
        }
        return sb.toString();
    }
}

/*
1. Build directions for both start and destination from the root.
    Say we get "LLRRL" and "LRR".
2. Remove common prefix path.
    We remove "L", and now start direction is "LRRL", and destination - "RR"
3. Replace all steps in the start direction to "U" and add destination direction.
    The result is "UUUU" + "RR".
*/ 
private boolean find(TreeNode n, int val, StringBuilder sb) {
    if (n.val == val) 
        return true;
    if (n.left != null && find(n.left, val, sb))
        sb.append("L");
    else if (n.right != null && find(n.right, val, sb))
        sb.append("R");
    return sb.length() > 0;
}
public String getDirections(TreeNode root, int startValue, int destValue) {
    StringBuilder s = new StringBuilder(), d = new StringBuilder();
    find(root, startValue, s);
    find(root, destValue, d);
    int i = 0, max_i = Math.min(d.length(), s.length());
    while (i < max_i && s.charAt(s.length() - i - 1) == d.charAt(d.length() - i - 1))
        ++i;
    return "U".repeat(s.length() - i) + d.reverse().toString().substring(i);
}