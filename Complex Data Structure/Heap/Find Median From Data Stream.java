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

// easier to understand
class MedianOfAStream {

  PriorityQueue<Integer> maxHeap; //containing first half of numbers
  PriorityQueue<Integer> minHeap; //containing second half of numbers

  public MedianOfAStream() {
    maxHeap = new PriorityQueue<>((a, b) -> b - a);
    minHeap = new PriorityQueue<>((a, b) -> a - b);
  }

  public void insertNum(int num) {
    if (maxHeap.isEmpty() || maxHeap.peek() >= num)
      maxHeap.add(num);
    else
      minHeap.add(num);

    // either both the heaps will have equal number of elements or max-heap will have one 
    // more element than the min-heap
    if (maxHeap.size() > minHeap.size() + 1)
      minHeap.add(maxHeap.poll());
    else if (maxHeap.size() < minHeap.size())
      maxHeap.add(minHeap.poll());
  }

  public double findMedian() {
    if (maxHeap.size() == minHeap.size()) {
      // we have even number of elements, take the average of middle two elements
      return maxHeap.peek() / 2.0 + minHeap.peek() / 2.0;
    }
    // because max-heap will have one more element than the min-heap
    return maxHeap.peek();
  }