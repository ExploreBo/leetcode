// Recursion
class Solution {
  public void construct_paths(TreeNode root, String path, LinkedList<String> paths) {
    if (root != null) {
      path += Integer.toString(root.val);
      if ((root.left == null) && (root.right == null))  // if reach a leaf
        paths.add(path);  // update paths
      else {
        path += "->";  // extend the current path
        construct_paths(root.left, path, paths);
        construct_paths(root.right, path, paths);
      }
    }
  }

  public List<String> binaryTreePaths(TreeNode root) {
    LinkedList<String> paths = new LinkedList();
    construct_paths(root, "", paths);
    return paths;
  }
}

// Iterations: two stack, one for node, one for path
class Solution {
    public List<String> binaryTreePaths(TreeNode root) {
        LinkedList<String> paths = new LinkedList();
        if (root == null)
            return paths;

        LinkedList<TreeNode> node_stack = new LinkedList();
        LinkedList<String> path_stack = new LinkedList();
        node_stack.add(root);
        path_stack.add(Integer.toString(root.val));
        TreeNode node;
        String path;
        while ( !node_stack.isEmpty() ) {
            node = node_stack.pollLast();
            path = path_stack.pollLast();
            if ((node.left == null) && (node.right == null))
                paths.add(path);
            if (node.left != null) {
                node_stack.add(node.left);
                path_stack.add(path + "->" + Integer.toString(node.left.val));
            }
            if (node.right != null) {
                node_stack.add(node.right);
                path_stack.add(path + "->" + Integer.toString(node.right.val));
            }
        }
        return paths;
    }
}

// iteration
class Solution {
    public List<String> binaryTreePaths(TreeNode root) {
        if (root == null) return null;
        List<String> result = new ArrayList();
        Map<TreeNode, TreeNode> ancestor = new HashMap();
        Deque<TreeNode> stack = new ArrayDeque();
        stack.push(root);
        while (!stack.isEmpty()) {
            TreeNode node = stack.pop();
            if (node.left == null && node.right == null) {
                StringBuilder sb = new StringBuilder();
                while (node != null) {
                    sb.insert(0, "->");
                    sb.insert(0, node.val);                    
                    node = ancestor.getOrDefault(node, null);
                }
                sb.delete(sb.length() - 2, sb.length());
                result.add(sb.toString());
            } else {
                if (node.right != null) {
                    stack.push(node.right);
                    ancestor.put(node.right, node);
                } 
                if (node.left != null) {
                    stack.push(node.left);
                    ancestor.put(node.left, node);
                }
            }
        }
        return result;
    }
}