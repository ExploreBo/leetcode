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


// DFS to getHeight. sort by height.
class Solution {
    private List<Pair<Integer, Integer>> pairs;
    
    private int getHeight(TreeNode root) {
        
        // return -1 for null nodes
        if (root == null) return -1;
        
        // first calculate the height of the left and right children
        int leftHeight = getHeight(root.left);
        int rightHeight = getHeight(root.right);
        
        // based on the height of the left and right children, obtain the height of the current (parent) node
        int currHeight = Math.max(leftHeight, rightHeight) + 1;

        // collect the pair -> (height, val)
        this.pairs.add(new Pair<Integer, Integer>(currHeight, root.val));

        // return the height of the current node
        return currHeight;
    }
    
    public List<List<Integer>> findLeaves(TreeNode root) {
        this.pairs = new ArrayList<>();
        
        getHeight(root);
        
        // sort all the (height, val) pairs
        Collections.sort(this.pairs, Comparator.comparing(p -> p.getKey()));
        
        int n = this.pairs.size(), height = 0, i = 0;

        List<List<Integer>> solution = new ArrayList<>();
        
        while (i < n) {
            List<Integer> nums = new ArrayList<>();
            while (i < n && this.pairs.get(i).getKey() == height) {
                nums.add(this.pairs.get(i).getValue());
                i++;
            }
            solution.add(nums);
            height++;
        }
        return solution;
    }
}



// DFS without sorting. height could be converted to the level of leaves.
class Solution {
    
    private List<List<Integer>> solution;
    
    private int getHeight(TreeNode root) {
        
        // return -1 for null nodes
        if (root == null) {
            return -1;
        }
        
        // first calculate the height of the left and right children
        int leftHeight = getHeight(root.left);
        int rightHeight = getHeight(root.right);
        
        int currHeight = Math.max(leftHeight, rightHeight) + 1;
        
        if (this.solution.size() == currHeight) {
            this.solution.add(new ArrayList<>());
        }
        
        this.solution.get(currHeight).add(root.val);
        
        return currHeight;
    }
    
    public List<List<Integer>> findLeaves(TreeNode root) {
        this.solution = new ArrayList<>();
        
        getHeight(root);
        
        return this.solution;
    }
}






// Topological sorting
class Solution {
    public List<List<Integer>> findLeaves(TreeNode root) {
        // Topological sorting: outdegrees == 0 meanning it's leaf node
        Queue<TreeNode> queue = new LinkedList();
        Map<TreeNode, Integer> outDegrees = new HashMap();
        Map<TreeNode, TreeNode> parentMap = new HashMap();
        Queue<TreeNode> leafNodes = new LinkedList();
        List<List<Integer>> result = new ArrayList();
        if (root == null) return result;
        
        // BFS to get the outDegrees nad the first leaves
        queue.add(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            while (size > 0) {
                TreeNode node = queue.poll();
                if (node.left == null && node.right == null) {
                    leafNodes.add(node);
                    outDegrees.put(node, 0);
                } else {
                    if (node.left != null) {
                        outDegrees.put(node, outDegrees.getOrDefault(node, 0) + 1);
                        parentMap.put(node.left, node);
                        queue.add(node.left);
                    }
                    if (node.right != null) {
                        outDegrees.put(node, outDegrees.getOrDefault(node, 0) + 1);
                        parentMap.put(node.right, node);
                        queue.add(node.right);
                    }                    
                }
                size--;
            }
        }
        // add the first leaf nodes
        result.add(leafNodes.stream().map(node -> node.val).collect(Collectors.toList()));
        
        // iterate to get leaf nodes after removal the previous ones
        while (!leafNodes.isEmpty()) {
            int size = leafNodes.size();
            while (size > 0) {
                // update the outDegrees and find the new leaf nodes (outDegree == 0)
                TreeNode node = leafNodes.poll();
                if (node != root) {
                    TreeNode parent = parentMap.get(node);
                    outDegrees.put(parent, outDegrees.get(parent) - 1);
                    if (outDegrees.get(parent) == 0) {
                        leafNodes.add(parent);
                    }                                    
                }

                size--;
            }
            if (!leafNodes.isEmpty()) {
                result.add(leafNodes.stream().map(node -> node.val).collect(Collectors.toList()));    
            }
        }
        
        return result;
    }
}