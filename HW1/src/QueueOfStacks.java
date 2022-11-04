// ==============================================================================
// IMPORTS

import java.lang.IllegalStateException;
import java.util.NoSuchElementException;
// ==============================================================================



// ==============================================================================
/**
 * A queue implemented using stacks.
 *
 * @author Scott Kaplan -- sfkaplan@amherst.edu
 * @date   Feb 2021
 */
public class QueueOfStacks <E> implements AmhQueue <E> {
// ==============================================================================


    
    // ==========================================================================
    /** The stack in which the queue's elements will be stored. */
    private AmhStack<E> _stack;
    // ==========================================================================


    
    // ==========================================================================
    /**
     * Constructor.  Create an empty queue.
     */
    public QueueOfStacks () {

	// Specifically use a WrapperStack, which is a type of AmhStack, to
	// store the elements.
	_stack = new WrapperStack<E>();

    } // StackOfQueues ()
    // ==========================================================================


    
    // ==========================================================================
    /**
     * Add an element to the tail of the queue.
     *
     * @param element The element to add to the queue.
     * @throws IllegalStateException if the queue cannot be expanded
     *                               to accomodate the additional element.
     */
    public void add (E element) throws IllegalStateException { // Filled in for assignment
        AmhStack<E> tempStack = new WrapperStack<E>();
        while (_stack.size() > 0) {
            tempStack.push(_stack.pop());
        }
        _stack.push(element);
        while (tempStack.size() > 0){
            _stack.push(tempStack.pop());
        }
    } // add ()
    // ==========================================================================


    
    // ==========================================================================
    /**
     * Remove an element from the head of the queue.
     *
     * @return the element taken from the head of the queue.
     * @throws NoSuchElementException if the queue is empty.
     */
    public E remove () throws NoSuchElementException { // Filled in for assignment

        return _stack.pop();
    } // remove ()
    // ==========================================================================


    
    // ==========================================================================
    public E peek () throws NoSuchElementException { // Filled in for assignment

        return _stack.top();
    } // peek ()
    // ==========================================================================


    
    // ==========================================================================
    public int size () { // Filled in for assignment

        return _stack.size();
    } // size ()
    // ==========================================================================


    
// ==============================================================================
} // class QueueOfStacks
// ==============================================================================
