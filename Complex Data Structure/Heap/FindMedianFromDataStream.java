class MedianFinder {
    // max queue is always larger or equal to min queue
    PriorityQueue<Integer> min = new PriorityQueue();
    PriorityQueue<Integer> max = new PriorityQueue(1000, Collections.reverseOrder());
    // Adds a number into the data structure.
    public void addNum(int num) {
        // first two steps are try to get the right place of the newly added element
        max.offer(num);
        min.offer(max.poll());
        // balance the size of the two heaps after the new element is inserted
        if (max.size() < min.size()){
            max.offer(min.poll());
        }
    }

    // Returns the median of current data stream
    public double findMedian() {
        if (max.size() == min.size()) return (max.peek() + min.peek()) /  2.0;
        // add operation ensures length of max is no smaller than min
        else return max.peek();
    }
};