// ==============================================================================
// IMPORTS

import java.lang.IllegalStateException;
import java.util.NoSuchElementException;
// ==============================================================================



// ==============================================================================
/**
 * A simplified <code>Queue</code> interface for FIFO-ordered queue containers.
 *
 * @author Scott Kaplan -- sfkaplan@amherst.edu
 * @date   Feb 2021
 */
public interface AmhQueue <E> {
// ==============================================================================



    // ==========================================================================
    /**
     * Add an element to the tail of the queue.
     *
     * @param element The element to add to the queue.
     * @throws IllegalStateException if the queue cannot be expanded
     *                               to accomodate the additional element.
     */
    public void add (E element) throws IllegalStateException;
    // ==========================================================================

    

    // ==========================================================================
    /**
     * Remove an element from the head of the queue.
     *
     * @return the element taken from the head of the queue.
     * @throws NoSuchElementException if the queue is empty.
     */
    public E remove () throws NoSuchElementException;
    // ==========================================================================


    
    // ==========================================================================
    /**
     * Obtain the head element in the queue without removing it.
     *
     * @return the element at the head of the queue.
     * @throws NoSuchElementException if the queue is empty.
     */
    public E peek () throws NoSuchElementException;
    // ==========================================================================


    
    // ==========================================================================
    /**
     * Provide the number of elements in the queue.
     *
     * @return the length of the queue.
     */
    public int size ();
    // ==========================================================================


    
// ==============================================================================
} // interface AmhQueue
// ==============================================================================
