public class RedBlackTree< E extends Comparable<E> > {
    
    public RBNode<E> root;

    public RedBlackTree () {

	// The root is a null leaf.
	root = new RBNode<E>(null);

    }

    private RBNode<E> find (RBNode<E> node, E key) {

	// If this is a null leaf, return it as an indicator of failure.
	if (node.isNullLeaf()) {
	    return node;
	}

	// If this node holds the key, return it.  Otherwise, recur to
	// the left or right subtree.
        int comparison = node.key.compareTo(key);
	if (comparison == 0) {
	    return node;
	} else if (comparison < 0) {
	    return find(node.left, key);
	} else {
	    return find (node.right, key);
	}
	
    }

    public boolean insert (E key, boolean red) {

	RBNode<E> node = find(root, key);
	if (node.key == null) {

	    // At a null leaf, so make this hold a key.
	    node.key   = key;
	    node.left  = new RBNode<E>(node);
	    node.right = new RBNode<E>(node);
	    node.red   = red;
	    return true;
	    
	} else if (node.key.equals(key)) {
	    
	    // Key is already in the tree.
	    return false;
	    
	} else {
	    
	    // Something has gone wrong.
	    throw new RuntimeException("Can't insert on unequal key");
	    
	}

    }

} // class RedBlackTree
