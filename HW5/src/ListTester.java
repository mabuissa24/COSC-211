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
    private TreeMapWrapper<String, Integer> referenceList;
    private BinarySearchTree<String, Integer> testList;
    private Scanner          input;
    private int              line;
    // ==========================================================================



    // ==========================================================================
    public static void main (String[] args) {

        // Check the arguments.
        if (args.length != 1) {
            showUsageAndExit();
        }

        // Extract the arguments and check them.  Create the list class to be
        // tested, and open the file from which to read instructions.
        String inputPath = args[0];

        // Create the tester and start it.
        ListTester tester = new ListTester(inputPath);
        tester.go();

    } // main ()
    // ==========================================================================



    // ==========================================================================
    public ListTester (String inputPathname) {

        // Create the reference list and the test list.
        referenceList = new TreeMapWrapper<>();
        testList      = new BinarySearchTree<>();
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

            // Read the process the next instruction.
            line = line + 1;
            String operation = input.next();
            String key = input.next();
            int    value     = -1;
            if (operation.equals("add")) {
                value = readInt();
                add(key, value);
            } else if (operation.equals("remove")) {
                remove(key);
            } else if (operation.equals("lookup")) {
                lookup(key);
            } else {
                System.out.printf("ERROR: Invalid operation %s at line %d\n",
                        operation,
                        line);
                System.exit(1);
            }
            compare();

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
    private void add (String a, int b) {

        // Insert the value into the reference list.
        Result referenceResult = Result.SUCCESS;
        try {
            referenceList.add(a, b);
        } catch (IndexOutOfBoundsException e) {
            referenceResult = Result.INDEX_FAIL;
        } catch (IllegalStateException e) {
            referenceResult = Result.ALLOC_FAIL;
        }

        // Insert the value into the test list.
        Result testResult = Result.SUCCESS;
        try {
            testList.add(a, b);
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
                    line, "add", a, b,
                    referenceResult,
                    testResult);
        }

    } // add ()
    // ==========================================================================



    // ==========================================================================
    private void remove (String key) {

        // Remove a value from the reference list.
        Result referenceResult = Result.SUCCESS;
        int    referenceValue  = -1; // CHANGING THIS
        try {
            referenceValue = referenceList.remove(key);
        } catch (NullPointerException e) {
            referenceResult = Result.INDEX_FAIL;
        }

        // Remove a value from the test list.
        Result testResult = Result.SUCCESS;
        int    testValue  = -1; // AND THIS TO INTEGERS RATHER THAN INTS BREAKS IT?
        try {
            testValue = testList.remove(key);
        } catch (NullPointerException e) {
            testResult = Result.INDEX_FAIL;
        }

        // Did both succeed/fail in the same way?
        if (referenceResult != testResult || referenceValue != testValue) {
            System.out.printf("MISMATCH <%9d>: %6s at [%9d]\n"      +
                            "                ref  = %10s / %9d\n" +
                            "                test = %10s / %9d\n",
                    line, "remove",  key,
                    referenceResult, referenceValue,
                    testResult,      testValue);
        }

    } // remove ()
    // ==========================================================================



    // ==========================================================================
    private void lookup (String key) {

        // Retrieve a value from the reference list.
        Result referenceResult = Result.SUCCESS;
        Integer    referenceValue  = -1;
        try {
            referenceValue = referenceList.lookup(key);
        } catch (IndexOutOfBoundsException e) {
            referenceResult = Result.INDEX_FAIL;
        }

        // Remove a value from the test list.
        Result testResult = Result.SUCCESS;
        Integer   testValue  = -1;
        try {
            testValue = testList.lookup(key);
        } catch (IndexOutOfBoundsException e) {
            testResult = Result.INDEX_FAIL;
        }

        // Did both succeed/fail in the same way?
        if (referenceResult != testResult || referenceValue != testValue) {
            System.out.printf("MISMATCH <%9d>: %6s at [%9d]\n"      +
                            "                ref  = %10s / %9d\n" +
                            "                test = %10s / %9d\n",
                    line, "get",     key,
                    referenceResult, referenceValue,
                    testResult,      testValue);
        }

    } // get ()
    // ==========================================================================


    // ==========================================================================
    private void compare () {

        /*if (referenceList.size() != testList.size()) {
            System.out.printf("<%d> size mismatch: ref = %d, test = %d\n",
                    line,
                    referenceList.size(),
                    testList.size());
            return;
        }
         */
        System.out.println("Reference: ");
        referenceList.inOrderTraverse();
        System.out.println("------\nTest: ");
        testList.inOrderTraverse();
        System.out.println("------");
        /*
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
         */
    } // compare ()

    // ==========================================================================



// ==============================================================================
} // class ListTester
// ==============================================================================
