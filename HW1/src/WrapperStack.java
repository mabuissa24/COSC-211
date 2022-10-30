// ==============================================================================
// IMPORTS

import java.lang.IllegalStateException;
import java.util.NoSuchElementException;
import java.util.LinkedList;
// ==============================================================================



// ==============================================================================
/**
 * Implement a simplied stack using the <code>Stack</code> methods of a standard
 * <code>LinkedList</code>.
 *
 * @author Scott Kaplan -- sfkaplan@amherst.edu
 * @date   Feb 2021
 */
public class WrapperStack <E> implements AmhStack <E> {
// ==============================================================================


    
    // ==========================================================================
    /** The linked list in which the stack's elements will be stored. */
    private LinkedList<E> _storage;
    // ==========================================================================


    
    // ==========================================================================
    /**
     * Constructor.  Create an empty stack.
     */
    public WrapperStack () {

	_storage = new LinkedList<E>();

    } // WrapperStack ()
    // ==========================================================================


    
    // ==========================================================================
    /**
     * Add an element to the top of the stack.
     *
     * @param element The element to add to the stack.
     * @throws IllegalStateException if the stack cannot be expanded
     *                               to accomodate the additional element.
     */
    public void push (E element) throws IllegalStateException {

	_storage.push(element);
	
    } // push ()
    // ==========================================================================


    
    // ==========================================================================
    /**
     * Remove an element from the top of the stack.
     *
     * @return the element taken from the top of the stack.
     * @throws NoSuchElementException if the stack is empty.
     */    
    public E pop () throws NoSuchElementException {

	return _storage.pop();
	
    } // pop ()
    // ==========================================================================


    
    // ==========================================================================
    /**
     * Obtain the top element on the stack without removing it.
     *
     * @return the element at the top of the stack.
     * @throws NoSuchElementException if the stack is empty.
     */
    public E top () throws NoSuchElementException {

	return _storage.getFirst();
	
    } // peek ()
    // ==========================================================================


    
    // ==========================================================================
    /**
     * Provide the number of elements on the stack.
     *
     * @return the length of the stack.
     */
    public int size () {

	return _storage.size();
	
    } // size ()
    // ==========================================================================


    
// ==============================================================================
} // class WrapperStack
// ==============================================================================
