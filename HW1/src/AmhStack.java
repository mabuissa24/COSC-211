// ==============================================================================
// IMPORTS

import java.lang.IllegalStateException;
import java.util.NoSuchElementException;
// ==============================================================================



// ==============================================================================
/**
 * A simplified <code>Stack</code> interface for LIFO-ordered stack containers.
 *
 * @author Scott Kaplan -- sfkaplan@amherst.edu
 * @date   Feb 2021
 */
public interface AmhStack <E> {
// ==============================================================================



    // ==========================================================================
    /**
     * Add an element to the top of the stack.
     *
     * @param element The element to add to the stack.
     * @throws IllegalStateException if the stack cannot be expanded
     *                               to accomodate the additional element.
     */
    public void push (E element) throws IllegalStateException;
    // ==========================================================================


    
    // ==========================================================================
    /**
     * Remove an element from the top of the stack.
     *
     * @return the element taken from the top of the stack.
     * @throws NoSuchElementException if the stack is empty.
     */    
    public E pop () throws NoSuchElementException;
    // ==========================================================================


    
    // ==========================================================================
    /**
     * Obtain the top element on the stack without removing it.
     *
     * @return the element at the top of the stack.
     * @throws NoSuchElementException if the stack is empty.
     */
    public E top () throws NoSuchElementException;
    // ==========================================================================


    
    // ==========================================================================
    /**
     * Provide the number of elements on the stack.
     *
     * @return the length of the stack.
     */
    public int size ();
    // ==========================================================================

    

// ==============================================================================    
} // interface AmhStack
// ==============================================================================

