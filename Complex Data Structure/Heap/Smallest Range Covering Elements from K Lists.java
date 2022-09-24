class Node {
    int arrayIndex;
    int itemIndex;
    Node(final int arrayIndex, final int itemIndex) {
        this.arrayIndex = arrayIndex;
        this.itemIndex = itemIndex;
    }
}
class Solution {
    public int[] smallestRange(List<List<Integer>> nums) {
        PriorityQueue<Node> minHeap = 
            new PriorityQueue<Node>((a, b) -> nums.get(a.arrayIndex).get(a.itemIndex) - nums.get(b.arrayIndex).get(b.itemIndex));
        int arraySize = nums.size();
        int currentMax = Integer.MIN_VALUE;
        for (int i = 0; i < arraySize; i++) {
            minHeap.offer(new Node(i, 0));
            currentMax = Math.max(currentMax, nums.get(i).get(0));
        }
        int start = 0;
        int end = Integer.MAX_VALUE;
        while (minHeap.size() == arraySize) {
            Node node = minHeap.poll();
            if (end - start > currentMax - nums.get(node.arrayIndex).get(node.itemIndex)) {
                end = currentMax;
                start = nums.get(node.arrayIndex).get(node.itemIndex);
            }
            if (node.itemIndex < nums.get(node.arrayIndex).size() - 1) {
                minHeap.add(new Node(node.arrayIndex, node.itemIndex + 1));
                currentMax = Math.max(currentMax, nums.get(node.arrayIndex).get(node.itemIndex + 1));
            }
        }
        return new int[]{start, end};
    }
}