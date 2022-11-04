// ==============================================================================
// IMPORTS

import java.lang.IllegalStateException;
import java.util.NoSuchElementException;
// ==============================================================================



// ==============================================================================
/**
 * A stack implemented using queues.
 *
 * @author Scott Kaplan -- sfkaplan@amherst.edu
 * @date   Feb 2021
 */
public class StackOfQueues <E> implements AmhStack <E> {
// ==============================================================================


    
    // ==========================================================================
    /** The queue in which the stack's elements will be stored. */
    private AmhQueue<E> _queue;
    // ==========================================================================


    
    // ==========================================================================
    /**
     * Constructor.  Create an empty stack.
     */
    public StackOfQueues () {

	// Specifically use a WrapperQueue, which is a type of AmhQueue, to
	// store the elements.
	_queue = new WrapperQueue<E>();

    } // StackOfQueues ()
    // ==========================================================================


    
    // ==========================================================================
    /**
     * Add an element to the top of the stack.
     *
     * @param element The element to add to the stack.
     * @throws IllegalStateException if the stack cannot be expanded
     *                               to accomodate the additional element.
     */
    public void push (E element) throws IllegalStateException { // Filled in for assignment

        _queue.add(element);
        for (int i = 0; i < _queue.size() - 1; i++){
            _queue.add(_queue.remove());
        }
    } // push ()
    // ==========================================================================


    
    // ==========================================================================
    /**
     * Remove an element from the top of the stack.
     *
     * @return the element taken from the top of the stack.
     * @throws NoSuchElementException if the stack is empty.
     */    
    public E pop () throws NoSuchElementException { // Filled in for assignment

        return _queue.remove();
    } // pop ()
    // ==========================================================================


    
    // ==========================================================================
    /**
     * Obtain the top element on the stack without removing it.
     *
     * @return the element at the top of the stack.
     * @throws NoSuchElementException if the stack is empty.
     */
    public E top () throws NoSuchElementException { // Filled in for assignment

        return _queue.peek();
    } // top ()
    // ==========================================================================


    
    // ==========================================================================
    /**
     * Provide the number of elements on the stack.
     *
     * @return the length of the stack.
     */
    public int size () { // Filled in for assignment

        return _queue.size();
    } // size ()
    // ==========================================================================


    
// ==============================================================================
} // class StackOfQueues
// ==============================================================================
