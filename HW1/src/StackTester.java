// ==============================================================================
// IMPORTS

import java.lang.IllegalStateException;
import java.util.NoSuchElementException;
// ==============================================================================



// ==============================================================================
/**
 * Test implementation of an {@interface AmhStack} against a known, correct
 * implementation.
 *
 * @author Scott Kaplan -- sfkaplan@amherst.edu
 * @date   Feb 2021
 */
public class StackTester {
// ==============================================================================


    
    // ==========================================================================
    public static void main (String[] args) {

	// Make one StackOfQueues to test, and one WrapperStack to test it
	// against.
	AmhStack<Integer> testStack      = new StackOfQueues<Integer>();
	AmhStack<Integer> referenceStack = new WrapperStack<Integer>();

	// Do some enqueuing.
	push(testStack, referenceStack, 1);
	push(testStack, referenceStack, 2);
	push(testStack, referenceStack, 3);
	
	// Do some dequeuing.
	pop(testStack, referenceStack);
	pop(testStack, referenceStack);
	pop(testStack, referenceStack);

	// Try to pop one item too many.
	pop(testStack, referenceStack);
	
    } // main ()
    // ==========================================================================



    // ==========================================================================
    private static void push (AmhStack<Integer> testStack,
			      AmhStack<Integer> referenceStack,
			      Integer value) {

	// Insert the value into the reference stack.
	boolean referenceSuccess;
	try {
	    referenceStack.push(value);
	    referenceSuccess = true;
	} catch (IllegalStateException e) {
	    referenceSuccess = false;
	}

	// Insert the value into the test stack.
	boolean testSuccess;
	try {
	    testStack.push(value);
	    testSuccess = true;
	} catch (IllegalStateException e) {
	    testSuccess = false;
	}

	// Did both succeed/fail in the same way?
	if (referenceSuccess != testSuccess) {
	    System.err.printf("push success mismatch: adding %d, test = %b, ref = %b\n",
			      value,
			      testSuccess,
			      referenceSuccess);
	}
	
    } // push ()
    // ==========================================================================



    // ==========================================================================
    private static void pop (AmhStack<Integer> testStack,
				 AmhStack<Integer> referenceStack) {


	// Pop from the reference stack.
	boolean referenceSuccess;
	int     referenceValue = 0;
	try {
	    referenceValue   = referenceStack.pop();
	    referenceSuccess = true;
	} catch (NoSuchElementException e) {
	    referenceSuccess = false;
	}

	// Pop from the test stack.
	boolean testSuccess;
	int     testValue = 0;
	try {
	    testValue   = testStack.pop();
	    testSuccess = true;
	} catch (NoSuchElementException e) {
	    testSuccess = false;
	}

	// Did both succeed/fail the same way?
	if (referenceSuccess != testSuccess) {
	    System.err.printf("pop success mismatch: test = %b, ref = %b\n",
			      testSuccess,
			      referenceSuccess);
	} else {

	    // If they succeeded, did the values match?
	    if (testSuccess && (testValue != referenceValue)) {
		System.err.printf("pop value mismatch: test = %d, ref = %b\n",
				  testValue,
				  referenceValue);
	    }

	}
	
    } // pop ()
    // ==========================================================================


    
// ==============================================================================
} // class StackTester
// ==============================================================================
