class MyHashSet {
    private Node[] nodes;

    public MyHashSet() {
        nodes = new Node[10000];
    }
    
    public void add(int key) {
        Node index = nodes[key % 10000];
        
        while (index != null && index.key != key) {
            index = index.next;
        }
        if (index == null) {
            Node newNode = new Node(key);
            newNode.next = nodes[key % 10000];
            nodes[key % 10000] = newNode;
        }
    }
    
    public void remove(int key) {
        Node index = nodes[key % 10000];
        Node dummy = new Node(0);
        dummy.next = index;
        Node curr = dummy;
        
        while (curr.next != null && curr.next.key != key) {
            curr = curr.next;
        }
        if (curr.next != null && curr.next.key == key) {
            curr.next = curr.next.next;
        } 
        nodes[key % 10000] = dummy.next;
    }
    
    public boolean contains(int key) {
        Node index = nodes[key % 10000];
        
        while (index != null && index.key != key) {
            index = index.next;
        }
        return index != null && index.key == key;
    }
    
    private class Node {
        int key;
        Node next;
        
        Node(int key) {
            this.key = key;
            this.next = null;
        }
    }
}
