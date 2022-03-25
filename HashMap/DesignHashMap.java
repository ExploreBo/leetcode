class MyHashMap {
    private Node[] nodes;
    private static final double LOAD_FACTOR = 0.75;
    private int size;
    
    public MyHashMap() {
        size = 16;
        nodes = new Node[size];
    }
    
    public void put(int key, int value) {
        Node index = nodes[key % nodes.length];
        while (index != null && index.key != key) {
            index = index.next;
        }
        if (index == null) {
            Node newNode = new Node(key, value);
            newNode.next = nodes[key % nodes.length];
            nodes[key % nodes.length] = newNode;            
            size++;
        } else {
            index.value = value;
        }
        if (size > nodes.length * LOAD_FACTOR) {
            expand();
        }
    }
    
    public int get(int key) {
        Node index = nodes[key % nodes.length];
        while (index != null && index.key != key) {
            index = index.next;
        }
        if (index == null) {
            return -1;
        } else {
            return index.value;
        }
    }
    
    public void remove(int key) {
        Node dummy = new Node(0, 0);
        Node index = nodes[key % nodes.length];
        dummy.next = index;
        Node curr = dummy;
        while (curr.next != null && curr.next.key != key) {
            curr = curr.next;
        }
        if (curr.next != null && curr.next.key == key) {
            curr.next = curr.next.next;
            size--;
        }
        nodes[key % nodes.length] = dummy.next;
    }
    
    private void expand() {
        Node[] oldNodes = nodes;
        nodes = new Node[oldNodes.length * 2];
        for (Node node: oldNodes) {
            while (node != null) {
                put(node.key, node.value);
                node = node.next;
            }
        }
        
    }
    
    private class Node {
        int key, value;
        Node next;
        Node(int key, int value) {
            this.key = key;
            this.value = value;
            this.next = null;
        }
        
    }
}