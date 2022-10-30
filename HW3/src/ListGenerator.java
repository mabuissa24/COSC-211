// ==============================================================================
// IMPORTS

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.Random;
// ==============================================================================



// ==============================================================================
/**
 * Test generator for implementation of an {@interface AmhList} against a known,
 * correct implementation.
 *
 * @author Scott Kaplan -- sfkaplan@amherst.edu
 * @date   Feb 2021
 */
public class ListGenerator {
// ==============================================================================


    
    // ==========================================================================
    private int              totalOps;
    private Random           random;
    private PrintStream      output;
    private int              line;
    private AmhList<Integer> list;
    // ==========================================================================



    // ==========================================================================
    public static void main (String[] args) {

	// Check the arguments.
	if (args.length != 3) {
	    showUsageAndExit();
	}

	// Extract the arguments.
	int    numOps         = 0;
	long   seed           = 0;
        String outputPathname = args[2];
	try {
	    numOps = Integer.parseUnsignedInt(args[0]);
	    seed   = Long.parseUnsignedLong(args[1]);
	} catch (NumberFormatException e) {
	    showUsageAndExit();
	}

        // Create the generator and start it.
        ListGenerator generator = new ListGenerator(numOps, seed, outputPathname);
        generator.go();

    } // main ()
    // ==========================================================================



    // ==========================================================================
    public ListGenerator (int numOps, long seed, String outputPathname) {

        totalOps = numOps;
        random   = new Random(seed);
        line     = 0;
        list     = new WrapperList<Integer>();
        
        File outputFile = new File(outputPathname);
        try {
            output = new PrintStream(outputFile);
        } catch (FileNotFoundException e) {
            System.err.printf("ERROR: Could not open file %s\n", outputPathname);
            showUsageAndExit();
        }

    } // ListGenerator ()
    // ==========================================================================



    // ==========================================================================
    private void go () {

        // Write up to the request number of instructions.
	for (line = 1; line <= totalOps; line = line + 1) {

	    switch (random.nextInt(5)) {

	    case 0:
                add();
		break;
		
	    case 1:
                remove();
		break;

	    case 2:
                get();
		break;

            case 3:
                set();
                break;

            case 4:
                size();
                break;

	    default:
		System.err.printf("ERROR: Invalid operation number ?!\n");
		System.exit(1);

	    }
	    
	}
	
    } // main ()
    // ==========================================================================



    // ==========================================================================
    private static void showUsageAndExit () {
	
	System.err.printf("USAGE: java ListGenerator <# ops>\n"           +
                          "                          <random seed>\n"     +
                          "                          <output pathname>\n" );
	System.exit(1);
	
    } // showUsageAndExit ()
    // ==========================================================================



    // ==========================================================================
    private void add () {

        // Choose random index and value...
        int index = getRandomIndex();
        int value = getRandomValue();

        // ...and try to apply them to a real list, to keep its size updated.
        try {
            list.add(index, value);
        }
        catch (IndexOutOfBoundsException e) {}
        catch (IllegalStateException e) {}

        // And emit the instruction.
        output.printf("%6s %9d %9d\n", "add", index, value);
        
    } // add ()
    // ==========================================================================
    


    // ==========================================================================
    private void remove () {

        // Choose random index...
        int index = getRandomIndex();

        // ...and try to apply it to a real list, to keep its size updated.
        try {
            list.remove(index);
        }
        catch (IndexOutOfBoundsException e) {}

        // And emit the instruction.
        output.printf("%6s %9d\n", "remove", index);
        
    } // add ()
    // ==========================================================================
    


    // ==========================================================================
    private void get () {

        // Choose random index.
        int index = getRandomIndex();

        // And emit the instruction.
        output.printf("%6s %9d\n", "get", index);
        
    } // add ()
    // ==========================================================================
    


    // ==========================================================================
    private void set () {

        // Choose random index and value.
        int index = getRandomIndex();
        int value = getRandomValue();

        // And emit the instruction.
        output.printf("%6s %9d %9d\n", "set", index, value);
        
    } // add ()
    // ==========================================================================
    


    // ==========================================================================
    private void size () {

        output.printf("%6s\n", "size");
        
    } // add ()
    // ==========================================================================
    


    // ==========================================================================
    private int getRandomIndex () {
        
        // Pick a random index between 1/4 of the size below 0 and 1/4 of the
        // size above the length, thus leaving a good change of invalid indices
        // being tested.
        int size       = list.size();
        int lowerIndex = -(size / 4);
        int upperIndex = size + (size / 4);
        int indexRange = upperIndex - lowerIndex;
        int index      = random.nextInt(indexRange + 1) + lowerIndex;

        return index;

    } // getRandomIndex ()
    // ==========================================================================



    // ==========================================================================
    private int getRandomValue () {

        // The choice of random value isn't important here.  We just want values
        // that are reasonably distinct.  So we tie it to the total number of
        // instructions being produced to give a proportional spread.
        return random.nextInt(totalOps);
        
    } // getRandomValue ()
    // ==========================================================================


    
// ==============================================================================
} // class ListGenerator
// ==============================================================================
