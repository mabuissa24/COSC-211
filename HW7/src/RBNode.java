public class RBNode< E extends Comparable<E> > {

    public E         key;
    public RBNode<E> parent;
    public RBNode<E> left;
    public RBNode<E> right;
    public boolean   red;

    public RBNode (RBNode<E> parent) {
	this.key    = null;
	this.parent = parent;
	this.left   = null;
	this.right  = null;
	this.red    = false;
    }

    public boolean isNullLeaf () {
	return key == null;
    }

}

