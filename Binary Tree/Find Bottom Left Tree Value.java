// intuitive solution: level order traversal and get the first element of the last level.
class Solution {
    public int findBottomLeftValue(TreeNode root) {
        List<List<Integer>> result = new ArrayList();
        Queue<TreeNode> queue = new LinkedList();
        queue.add(root);
        while (!queue.isEmpty()) {
            int count = queue.size();
            List<Integer> temp = new ArrayList();
            while (count != 0) {
                TreeNode node = queue.poll();
                if (node != null) {
                    temp.add(node.val);
                    queue.add(node.left);
                    queue.add(node.right);                    
                }
                count--;
            }
            if (!temp.isEmpty()) {
                result.add(temp);                
            }
        }
        return result.get(result.size() - 1).get(0);        
    }
}

// BFS, use global variables to track the level and first element of each level
public class Solution {
    int ans=0, h=0;
    public int findBottomLeftValue(TreeNode root) {
        findBottomLeftValue(root, 1);
        return ans;
    }
    public void findBottomLeftValue(TreeNode root, int depth) {
        if (h<depth) {ans=root.val;h=depth;}
        if (root.left!=null) findBottomLeftValue(root.left, depth+1);
        if (root.right!=null) findBottomLeftValue(root.right, depth+1);
    }
}

// Level order but from right to left, get the last element. (either BFS or DFS should work)
// BFS
public int findLeftMostNode(TreeNode root) {
    Queue<TreeNode> queue = new LinkedList<>();
    queue.add(root);
    while (!queue.isEmpty()) {
        root = queue.poll();
        if (root.right != null)
            queue.add(root.right);
        if (root.left != null)
            queue.add(root.left);
    }
    return root.val;
}

// DFS from right to left
class Solution {
    int res = 0;
    int maxDepth = 0;
    public int findBottomLeftValue(TreeNode root) {
        dfs(root, 0);
        return res;
    }
    
    public void dfs(TreeNode root, int depth){
        if(root == null) return;
        if(maxDepth <= depth){
            res = root.val;
            maxDepth = depth;
        }
        dfs(root.right, depth + 1);
        dfs(root.left, depth + 1);
    }
}

