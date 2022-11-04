// ==============================================================================
// IMPORTS

import java.lang.IllegalStateException;
import java.util.LinkedList;
// ==============================================================================



// ==============================================================================
class Link <E> {
    public Link<E> next;
    public Link<E> prev;
    public E       value;
}
// ==============================================================================



// ==============================================================================
/**
 * Implement a simplified list using a standard <code>LinkedList</code>.
 *
 * @author Scott Kaplan -- sfkaplan@amherst.edu
 * @date   Mar 2021
 */
public class AmhLinkedList <E> implements AmhList <E> {
// ==============================================================================

    // This class was debugged for assignment

    
    // ==========================================================================
    private Link<E> head;
    private int     size;
    // ==========================================================================


    
    // ==========================================================================
    /**
     * Constructor.  Create an empty list.
     */
    public AmhLinkedList () {
        head = new Link<E>();
        Link<E> tail = new Link<E>();
        head.next = tail;
        head.prev = null;
        tail.next = null;
        tail.prev = head;
        size = 0;

    } // AmhLinkedList ()
    // ==========================================================================


    
    // ==========================================================================
    /**
     * Add an element to this list at the given, indexed position.  The index
     * must be in range, or must be immediately after the last valid index, thus
     * appending the value to the list.
     *
     * @param index   The position at which to insert the element.
     * @param element The element to add.
     * @throws IndexOutOfBoundsException if {@code index < 0 || size < index}
     * @throws IllegalStateException  if the list cannot be expanded to
     *                                accomodate the additional element.
     */
    public void add (int index, E element) throws IndexOutOfBoundsException,
						  IllegalStateException {

	if (index < 0 || size < index) {
	    throw new IndexOutOfBoundsException(index);
	}
        Link<E> p = walk(index);
        Link<E> n = new Link<E>();
        n.value = element;
        n.next      = p;
        n.prev      = p.prev;
        n.prev.next = n;
        p.prev      = n;

        size = size + 1;
        
    } // add ()
    // ==========================================================================

    

    // ==========================================================================
    /**
     * Retrieve the element at the given, indexed position.
     *
     * @param index The position from which to retrieve the element.
     * @return the value at the given position.
     * @throws IndexOutOfBoundsException if {@code index < 0 && size <= index}
     */
    public E get (int index) throws IndexOutOfBoundsException {

	if (index < 0 || size <= index) {
	    throw new IndexOutOfBoundsException(index);
	}

        Link<E> p = walk(index);

        return p.value;
        
    } // get ()
    // ==========================================================================



    // ==========================================================================
    /**
     * Remove an element from the given, indexed position.  The list size is
     * decremented, with the elements in higher position sliding down to fill in
     * the gap.
     *
     * @param index The position at which to remove an element.
     * @return the removed element.
     * @throws IndexOutOfBoundsException if {@code index < 0 && size <= index}
     */
    public E remove (int index) throws IndexOutOfBoundsException {

	if (index < 0 || size <= index) {
	    throw new IndexOutOfBoundsException(index);
	}

        Link<E> p     = walk(index);
        E       value = p.value;
        p.prev.next = p.next;
        p.next.prev = p.prev; // This line is giving null pointer

        size = size - 1;
        
        return value;
        
    } // remove ()
    // ==========================================================================

    

    // ==========================================================================
    /**
     * Replace the element at the given index with another, given element.
     *
     * @param index   The position at which to replace an element.
     * @param element The new element to place at the given position.
     * @return the removed element previously at the given position.
     * @throws IndexOutOfBoundsException if {@code index < 0 && size <= index}
     */
    public E set (int index, E element) throws IndexOutOfBoundsException {

	if (index < 0 || size <= index) {
	    throw new IndexOutOfBoundsException(index);
	}

        Link<E> p     = walk(index);
        E       value = p.value;
        p.value = element;

        return value;
    } // set ()
    // ==========================================================================

    

    // ==========================================================================
    /**
     * Provide the number of elements in the list.
     *
     * @return the length of the list.
     */
    public int size () {

        return size;
        
    } // size ()
    // ==========================================================================



    // ==========================================================================
    private Link<E> walk (int index) {

        Link<E> current = head.next;
        for (int i = 0; i < index; i = i + 1) {
            current = current.next;
            if (current == null){
                break;
            }
        }
        return current;
        
    } // walk ()
    // ==========================================================================
    


// ==============================================================================
} // class AmhLinkedList
// ==============================================================================
