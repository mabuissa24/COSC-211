public class BinarySearchTree<K extends Comparable<K>, V> implements Dictionary<K, V>{
    private Node<K, V> head = new Node<>(null, null, null);

    public V add(K key, V value){
        if (head.getKey() == null){
            head = new Node<>(key, value, null);
            return null;
        }
        Node<K, V> current = head;
        while (true) {
            int compare = key.compareTo(current.getKey());
            if (compare < 0) { // new node is less than parent
                if (current.getLeft() == null) { // current has no left child and toAdd wants to be its left child
                    current.setLeft(new Node<>(key, value, current));
                    return null;
                }
                current = current.getLeft();
            } else if (compare > 0) { // new node is greater than parent
                if (current.getRight() == null){
                    current.setRight(new Node<>(key, value, current));
                    return null;
                }
                current = current.getRight();
            } else { // key is the same
                V toReturn = current.getValue();
                current.setValue(value);
                return toReturn;
            }
        }
    }

    public V remove(K key) {
        Node<K, V> toRemove = lookupHelper(key);
        if (toRemove == null)
            return null;
        V toReturn = toRemove.getValue();
        Node<K, V> parent = toRemove.getParent();
        boolean head = (parent == null);
        boolean leftChild = false;
        if (!head)
            leftChild = (key.compareTo(parent.getKey()) < 0);
        if (toRemove.getRight() == null && toRemove.getLeft() == null){
            if (head){
                this.head.setKey(null);
                this.head.setValue(null);
            } else {
                if (leftChild) {
                    parent.setLeft(null);
                }
                else parent.setRight(null);
            }
        } else if (toRemove.getLeft() == null){
            if (head){
                this.head = toRemove.getRight();
                this.head.setParent(null);

            } else {
                if (leftChild) {
                    parent.setLeft(toRemove.getRight());
                    parent.getLeft().setParent(parent);
                } else {
                    parent.setRight(toRemove.getRight());
                    parent.getRight().setParent(parent);
                }
            }
        } else if (toRemove.getRight() == null){
            if (head){
                this.head = toRemove.getLeft();
                this.head.setParent(null);
            } else {
                if (leftChild) {
                    parent.setLeft(toRemove.getLeft());
                    parent.getLeft().setParent(parent);
                }
                else {
                    parent.setRight(toRemove.getLeft());
                    parent.getRight().setParent(parent);
                }
            }
        } else {
            Node<K, V> successor = findSuccessor(toRemove);
            if (successor.getParent().getRight() == successor){// toRemove's successor is directly to the right of it
                toRemove.setRight(successor.getRight());
                if (successor.getRight() != null)
                    successor.getRight().setParent(toRemove);
            } else {
                successor.getParent().setLeft(successor.getRight());
                if (successor.getRight() != null)
                    successor.getRight().setParent(successor.getParent());
            }
            toRemove.setValue(successor.getValue());
            toRemove.setKey(successor.getKey());
        }
        return toReturn;
    }

    private Node<K, V> findSuccessor(Node<K, V> start){
        Node<K, V> toReturn = start;
        toReturn = start.getRight();
        while (toReturn.getLeft() != null){
            toReturn = toReturn.getLeft();
        }
        return toReturn;
    }

    public V lookup(K key) {
        Node<K, V> toUse = lookupHelper(key);
        if (toUse == null)
            return null;
        return toUse.getValue();
    }

    private Node<K, V> lookupHelper(K key){
        Node<K, V> current = head;
        if (head.getKey() == null)
            return null;
        while (true){
            int compare = key.compareTo(current.getKey());
            if (compare < 0) { // node we're looking for is less than parent
                if (current.getLeft() == null)
                    return null;
                current = current.getLeft();

            } else if (compare > 0) { // node we're looking for is greater than parent
                if (current.getRight() == null)
                    return null;
                current = current.getRight();
            } else { // key is the same
                return current;
            }
        }
    }

    public void inOrderTraverse() {
        inOrderTraverseHelper(head);
    }

    private void inOrderTraverseHelper(Node a){
        if (a == null)
            return;
        inOrderTraverseHelper(a.getLeft());
        System.out.println("(" + a.getKey() + ", " + a.getValue() + ")");
        inOrderTraverseHelper(a.getRight());
    }
}
