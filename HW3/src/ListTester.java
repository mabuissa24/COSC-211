// ==============================================================================
// IMPORTS

import java.io.File;
import java.io.FileNotFoundException;
import java.lang.IllegalStateException;
import java.util.InputMismatchException;
import java.util.NoSuchElementException;
import java.util.Random;
import java.util.Scanner;
// ==============================================================================



// ==============================================================================
/**
 * Tester for implementation of an {@interface AmhList} against a known, correct
 * implementation.
 *
 * @author Scott Kaplan -- sfkaplan@amherst.edu
 * @date   Mar 2021
 */
public class ListTester {
// ==============================================================================



    // ==========================================================================
    private AmhList<Integer> referenceList;
    private AmhList<Integer> testList;
    private Scanner          input;
    private int              line;
    // ==========================================================================


    
    // ==========================================================================
    public static void main (String[] args) {

	// Check the arguments.
	if (args.length != 2) {
	    showUsageAndExit();
	}

	// Extract the arguments and check them.  Create the list class to be
	// tested, and open the file from which to read instructions.
        String className = args[0];
        String inputPath = args[1];

        // Create the tester and start it.
        ListTester tester = new ListTester(className, inputPath);
        tester.go();

    } // main ()
    // ==========================================================================

    

    // ==========================================================================
    public ListTester (String className, String inputPathname) {

        // Create the reference list and the test list.
	referenceList = new WrapperList<Integer>();
        testList      = createList(className);
        if (testList == null) {
            showUsageAndExit();
        }

	// Initialize the input and the line counter.
        File inputFile = new File(inputPathname);
        if (!inputFile.canRead()) {
            System.out.printf("ERROR: Invalid input pathname %s\n", inputPathname);
            showUsageAndExit();
        }
        try {
            input = new Scanner(inputFile);
        } catch (FileNotFoundException e) {
            System.out.printf("ERROR: Could not open file %s\n", inputPathname);
            showUsageAndExit();
        }
        line  = 0;

    } // ListTester ()
    // ==========================================================================



    // ==========================================================================
    private void go () {

        // Read instructions until there are no more.
        line = 0;
        while (input.hasNext()) {

            // This call can be removed for less debugging output.
            compare();

            // Read the process the next instruction.
            line = line + 1;
            String operation = input.next();
            int    index     = -1;
            int    value     = -1;
            if (operation.equals("add")) {
                index = readInt();
                value = readInt();
                add(index, value);
            } else if (operation.equals("remove")) {
                index = readInt();
                remove(index);
            } else if (operation.equals("get")) {
                index = readInt();
                get(index);
            } else if (operation.equals("set")) {
                index = readInt();
                value = readInt();
                set(index, value);
            } else if (operation.equals("size")) {
                size();
            } else {
                System.out.printf("ERROR: Invalid operation %s at line %d\n",
                                  operation,
                                  line);
                System.exit(1);
            }

        }

    } // go ()
    // ==========================================================================



    // ==========================================================================
    private static void showUsageAndExit () {
	
	System.out.printf("USAGE: java ListTester <AmhList class>\n" +
                          "                       <input pathname>\n");
	System.exit(1);
	
    } // showUsageAndExit ()
    // ==========================================================================



    // ==========================================================================
    private static AmhList<Integer> createList (String className) {

        AmhList<Integer> list = null;
        if (className.equals("AmhArrayList")) {
            list = new AmhArrayList<Integer>();
        } else if (className.equals("AmhLinkedList")) {
            list = new AmhLinkedList<Integer>();
        }

        return list;
        
    } // createList ()
    // ==========================================================================



    // ==========================================================================
    private int readInt () {

        // Attempt to read and return an integer value from the input.
        int value = -1;
        try {
            value = input.nextInt();
        } catch (InputMismatchException e) {
            System.out.printf("ERROR: Could not read integer on line %d\n",
                              line);
            System.exit(1);
        }

        return value;
        
    } // readInt ()
    // ==========================================================================



    // ==========================================================================
    private void add (int index, int value) {

	// Insert the value into the reference list.
        Result referenceResult = Result.SUCCESS;
	try {
	    referenceList.add(index, value);
	} catch (IndexOutOfBoundsException e) {
            referenceResult = Result.INDEX_FAIL;
        } catch (IllegalStateException e) {
            referenceResult = Result.ALLOC_FAIL;
        }

	// Insert the value into the test list.
	Result testResult = Result.SUCCESS;
	try {
	    testList.add(index, value);
	} catch (IndexOutOfBoundsException e) {
            testResult = Result.INDEX_FAIL;
        } catch (IllegalStateException e) {
	    testResult = Result.ALLOC_FAIL;
	}

	// Did both succeed/fail in the same way?
        if (referenceResult != testResult) {
            System.out.printf("MISMATCH <%9d>: %6s %9d at [%9d]\n" +
                              "                ref  = %10s\n"       +
                              "                test = %10s\n",
                              line, "add", value, index,
                              referenceResult,
                              testResult);
        }

    } // add ()
    // ==========================================================================



    // ==========================================================================
    private void remove (int index) {

	// Remove a value from the reference list.
        Result referenceResult = Result.SUCCESS;
        int    referenceValue  = -1;
	try {
	    referenceValue = referenceList.remove(index);
	} catch (IndexOutOfBoundsException e) {
            referenceResult = Result.INDEX_FAIL;
        }

	// Remove a value from the test list.
	Result testResult = Result.SUCCESS;
        int    testValue  = -1;
	try {
	    testValue = testList.remove(index);
	} catch (IndexOutOfBoundsException e) {
            testResult = Result.INDEX_FAIL;
        }

	// Did both succeed/fail in the same way?
        if (referenceResult != testResult || referenceValue != testValue) {
            System.out.printf("MISMATCH <%9d>: %6s at [%9d]\n"      +
                              "                ref  = %10s / %9d\n" +
                              "                test = %10s / %9d\n",
                              line, "remove",  index,
                              referenceResult, referenceValue,
                              testResult,      testValue);
        }

    } // remove ()
    // ==========================================================================



    // ==========================================================================
    private void get (int index) {

	// Retrieve a value from the reference list.
        Result referenceResult = Result.SUCCESS;
        int    referenceValue  = -1;
	try {
	    referenceValue = referenceList.get(index);
	} catch (IndexOutOfBoundsException e) {
            referenceResult = Result.INDEX_FAIL;
        }

	// Remove a value from the test list.
	Result testResult = Result.SUCCESS;
        int    testValue  = -1;
	try {
	    testValue = testList.get(index);
	} catch (IndexOutOfBoundsException e) {
            testResult = Result.INDEX_FAIL;
        }

	// Did both succeed/fail in the same way?
        if (referenceResult != testResult || referenceValue != testValue) {
            System.out.printf("MISMATCH <%9d>: %6s at [%9d]\n"      +
                              "                ref  = %10s / %9d\n" +
                              "                test = %10s / %9d\n",
                              line, "get",     index,
                              referenceResult, referenceValue,
                              testResult,      testValue);
        }

    } // get ()
    // ==========================================================================



    // ==========================================================================
    private void set (int index, int value) {

	// Insert the value into the reference list.
        Result referenceResult = Result.SUCCESS;
        int    referenceValue  = -1;
	try {
	    referenceValue = referenceList.set(index, value);
	} catch (IndexOutOfBoundsException e) {
            referenceResult = Result.INDEX_FAIL;
        }
	// Insert the value into the test list.
	Result testResult = Result.SUCCESS;
        int    testValue  = -1;
	try {
	    testValue = testList.set(index, value);
	} catch (IndexOutOfBoundsException e) {
            testResult = Result.INDEX_FAIL;
        }

	// Did both succeed/fail in the same way?
        if (referenceResult != testResult) {
            System.out.printf("MISMATCH <%9d>: %6s %9d at [%9d]\n"      +
                              "                ref  = %10s / %9d\n" +
                              "                test = %10s / %9d\n",
                              line, "set",     value, index,
                              referenceResult, referenceValue,
                              testResult,      testValue);
        }

    } // set ()
    // ==========================================================================



    // ==========================================================================
    private void size () {

        // Grab the size of both the reference list and the test list.
        int referenceSize = referenceList.size();
        int testSize      = testList.size();

        // Did both return the same size?
        if (referenceSize != testSize) {
            System.out.printf("MISMATCH <%9d>: %6s\n"       +
                              "                ref  = %9d\n" +
                              "                test = %9d\n",
                              line, "size",
                              referenceSize,
                              testSize);
        }
        
    } // size ()
    // ==========================================================================



    // ==========================================================================
    private void compare () {

        if (referenceList.size() != testList.size()) {
            System.out.printf("<%d> size mismatch: ref = %d, test = %d\n",
                              line,
                              referenceList.size(),
                              testList.size());
            return;
        }

        for (int i = 0; i < referenceList.size(); i = i + 1) {
            int rv = referenceList.get(i);
            int tv = testList.get(i);
            System.out.printf("<%d> [%d]\t%d\t%d\t%s\n",
                              line,
                              i,
                              rv,
                              tv,
                              rv != tv ? "***" : "");
        }

        System.out.printf("-----\n\n");
        
    } // compare ()
    // ==========================================================================
    


// ==============================================================================
} // class ListTester
// ==============================================================================
