/* just use the features of Linked Hash Map

A LinkedHashMap is a combination of hash table and linked list. 
It has a predictable iteration order (a la linked list), 
yet the retrieval speed is that of a HashMap. 
The order of the iteration is determined by the insertion order, 
so you will get the key/values back in the order that they were added to this Map
*/

class LRUCache {
    private LinkedHashMap<Integer, Integer> map;
    private int SIZE;
    public LRUCache(int capacity) {
        map = new LinkedHashMap<>();
        SIZE = capacity;
    }

    public int get(int key) {
        if (map.containsKey(key)) {
            int value = map.remove(key);
            map.put(key, value);
            return value;
        }
        return -1;
    }

    public void put(int key, int value) {
        if (map.containsKey(key)) {
            // Insertion order is not affected if a key is re-inserted into the map.
            map.remove(key);
        } else if(map.size() + 1 > SIZE) {
            map.remove(map.keySet().iterator().next());
        }
        map.put(key, value);
    }
}


// Solution 2: HashMap + DoublyLinkedList
public class LRUCache {

    class DLinkedNode {
      int key;
      int value;
      DLinkedNode pre;
      DLinkedNode post;
    }

    /**
     * Always add the new node right after head;
     */
    private void addNode(DLinkedNode node) {
      node.pre = head;
      node.post = head.post;

      head.post.pre = node;
      head.post = node;
    }

    /**
     * Remove an existing node from the linked list.
     */
    private void removeNode(DLinkedNode node){
      node.pre.post = node.post;
      node.post.pre = node.pre;
    }

    /**
     * Move certain node in between to the head.
     */
    private void moveToHead(DLinkedNode node){
      this.removeNode(node);
      this.addNode(node);
    }

    // pop the current tail. 
    private DLinkedNode popTail(){
      DLinkedNode res = tail.pre;
      this.removeNode(res);
      return res;
    }

    private HashMap<Integer, DLinkedNode> 
      cache = new HashMap<Integer, DLinkedNode>();
    private int count;
    private int capacity;
    private DLinkedNode head, tail;

    public LRUCache(int capacity) {
      this.count = 0;
      this.capacity = capacity;

      head = new DLinkedNode();
      head.pre = null;

      tail = new DLinkedNode();
      tail.post = null;

      head.post = tail;
      tail.pre = head;
    }

    public int get(int key) {

      DLinkedNode node = cache.get(key);
      if (node == null) {
        return -1;
      }

      // move the accessed node to the head;
      this.moveToHead(node);

      return node.value;
    }


    public void put(int key, int value) {
      DLinkedNode node = cache.get(key);

      if (node == null) {

        DLinkedNode newNode = new DLinkedNode();
        newNode.key = key;
        newNode.value = value;

        this.cache.put(key, newNode);
        this.addNode(newNode);

        ++count;

        if (count > capacity) {
          // pop the tail
          DLinkedNode tail = this.popTail();
          this.cache.remove(tail.key);
          --count;
        }
      } else {
        // update the value.
        node.value = value;
        this.moveToHead(node);
      }
    }

}