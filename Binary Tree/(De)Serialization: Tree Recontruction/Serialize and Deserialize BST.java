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
Requirement for this problem is "The encoded string should be as compact as possible.".
So we need to leverage the property of BST and get rid of null or # as sentinels.
*/
public class Codec {
    // Encodes a tree to a single string. PreOrder.
    public String serialize(TreeNode root) {
        StringBuilder sb = new StringBuilder();
        serialize(root, sb);
        return sb.toString();
    }
    
    public void serialize(TreeNode root, StringBuilder sb) {
        if (root == null) return;
        sb.append(root.val).append(",");
        serialize(root.left, sb);
        serialize(root.right, sb);
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        if (data.isEmpty()) return null;
        Queue<String> q = new LinkedList<>(Arrays.asList(data.split(",")));
        return deserialize(q, Integer.MIN_VALUE, Integer.MAX_VALUE);
    }
    
    public TreeNode deserialize(Queue<String> q, int lower, int upper) {
        if (q.isEmpty()) return null;
        String s = q.peek();
        int val = Integer.parseInt(s);
        if (val < lower || val > upper) return null;
        q.poll();
        TreeNode root = new TreeNode(val);
        root.left = deserialize(q, lower, val);
        root.right = deserialize(q, val, upper);
        return root;
    }
}


/*
This also utilizes the BST's property. By comparing each element with the root and divide into left subTree and right subTree.
*/
public class Codec {
    // Encodes a tree to a single string. PreOrder.
    public String serialize(TreeNode root) {
        StringBuilder sb = new StringBuilder();
        serialize(root, sb);
        return sb.toString();
    }
    
    public void serialize(TreeNode root, StringBuilder sb) {
        if (root == null) return;
        sb.append(root.val).append(",");
        serialize(root.left, sb);
        serialize(root.right, sb);
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        if (data.isEmpty()) return null;
        String[] strs = data.split(",");
        return deserialize(strs, 0, strs.length - 1);
    }
    
    public TreeNode deserialize(String[] strs, int start, int end) {
        String s = strs[start];
        int val = Integer.parseInt(s);
        TreeNode root = new TreeNode(val);
        int length = 0;
        while (start + length + 1 <= end) {
            if (Integer.parseInt(strs[start + length + 1]) < val) {
                length++;
            } else {
                break;
            }
        }
        if (start + length < strs.length && length > 0) root.left = deserialize(strs, start + 1, start + length);
        if (start + 1 + length <= end) root.right = deserialize(strs, start + length + 1, end);
        return root;
    }
}

