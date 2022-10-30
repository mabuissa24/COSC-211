// ==============================================================================
// IMPORTS

import java.lang.IllegalStateException;
import java.util.NoSuchElementException;
import java.util.LinkedList;
// ==============================================================================



// ==============================================================================
/**
 * Implement a simplied queue using the <code>Queue</code> methods of a standard
 * <code>LinkedList</code>.
 *
 * @author Scott Kaplan -- sfkaplan@amherst.edu
 * @date   Feb 2021
 */
public class WrapperQueue <E> implements AmhQueue <E> {
// ==============================================================================


    
    // ==========================================================================
    /** The linked list in which the queue's elements will be stored. */
    private LinkedList<E> _storage;
    // ==========================================================================

    
    
    // ==========================================================================
    /**
     * Constructor.  Create an empty queue.
     */
    public WrapperQueue () {

	_storage = new LinkedList<E>();
	
    } // WrapperQueue ()
    // ==========================================================================


    
    // ==========================================================================
    /**
     * Add an element to the tail of the queue.
     *
     * @param element The element to add to the queue.
     * @throws IllegalStateException if the queue cannot be expanded
     *                               to accomodate the additional element.
     */
    public void add (E element) throws IllegalStateException {

	// Adding an element never fails with LinkedList.  Adding is
	// performed at the tail (highest index) of the list.
	_storage.add(element);
	
    } // add ()
    // ==========================================================================


    
    // ==========================================================================
    /**
     * Remove an element from the head of the queue.
     *
     * @return the element taken from the head of the queue.
     * @throws NoSuchElementException if the queue is empty.
     */
    public E remove () throws NoSuchElementException {

	// Removal it done at the head (index 0) of the list.
	return _storage.remove();

    } // remove ()
    // ==========================================================================


    
    // ==========================================================================
    /**
     * Obtain the head element in the queue without removing it.
     *
     * @return the element at the head of the queue.
     * @throws NoSuchElementException if the queue is empty.
     */
    public E peek () throws NoSuchElementException {

	// The element is at the head (index 0) in the list.
	return _storage.element();
	
    } // peek ()
    // ==========================================================================


    
    // ==========================================================================
    /**
     * Provide the number of elements in the queue.
     *
     * @return the length of the queue.
     */
    public int size () {

	return _storage.size();
	
    } // size ()
    // ==========================================================================


    
// ==============================================================================    
} // class WrapperQueue
// ==============================================================================

