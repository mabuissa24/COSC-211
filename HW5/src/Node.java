public class Node<K, V> {
    private K key;
    private V value;
    private Node<K, V> parent;
    private Node<K, V> right;
    private Node<K, V> left;

    public Node(K key, V val, Node<K, V> parent){
        this.key = key;
        value = val;
        this.parent = parent;
    }

    public K getKey(){
        return key;
    }

    public V getValue(){return value; }

    public Node<K, V> getParent(){
        return parent;
    }

    public Node<K, V> getRight(){
        return right;
    }

    public Node<K, V> getLeft(){
        return left;
    }

    public void setRight(Node<K, V> r){
        right = r;
    }

    public void setLeft(Node<K, V> l){
        left = l;
    }

    public void setParent(Node<K, V> p){
        parent = p;
    }

    public void setValue(V val){
        value = val;
    }

    public void setKey(K key) { this.key = key; }
}
