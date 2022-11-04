import java.util.Scanner;
import java.util.InputMismatchException;
import java.io.File;
import java.io.FileNotFoundException;

public class RBTester {

    public static void main (String[] args) {

	if (args.length != 1) { showUsageAndExit(); }

	RedBlackTree<Integer> rbt = new RedBlackTree<Integer>();
	Scanner                sc = null;
        try {
            sc = new Scanner(new File(args[0]));
        } catch (FileNotFoundException e) {
            System.err.printf("ERROR: Could not open file %s\n", args[0]);
            System.exit(1);
        }

	// Read instructions until there are no more.
	int line = 0;
	while (sc.hasNext()) {

	    line = line + 1;
	    int    value = 0;
	    try {
		value = sc.nextInt();
	    } catch (InputMismatchException e) {
		System.err.printf("ERROR: Could not read value on line %d\n", line);
		System.exit(1);
	    }
            String  color = sc.next();
            boolean red   = false;;
            if (color.equalsIgnoreCase("red")) {
                red = true;
            } else if (!color.equalsIgnoreCase("black")) {
                System.err.printf("ERROR: Unknown color %s on line %d\n", color, line);
                System.exit(1);
            }
            
            rbt.insert(value, red);
            
	}

        System.out.printf("Is it valid? %b\n", isRBTree(rbt));

    }

    
    private static void showUsageAndExit () {
	
	System.err.printf("USAGE: java RBTester <input pathname>\n");
	System.exit(1);

    }


    private static boolean isRBTree (RedBlackTree<Integer> rbt) { // Method completed for assignment

	RBNode<Integer> root = rbt.root;
	boolean redRoot = root.red;
	boolean redRedProblem = redRedProblem(root);
	boolean unequalPaths = (pathLength(root) == -1);
	boolean redNullLeaves = redNullLeaves(root);
	System.out.println("Red root: " + redRoot + ". Red red problem: " + redRedProblem +
            ". unequal paths: " + unequalPaths + ". red null leaves: " + redNullLeaves + ".");
	return (!redRoot && !redRedProblem && !unequalPaths && !redNullLeaves);

    }

    private static boolean redRedProblem(RBNode<Integer> node){
        if (node.isNullLeaf())
            return false;
        if (node.red && (node.right.red || node.left.red))
            return true;
        return (redRedProblem(node.right) || redRedProblem(node.left));
    }

    private static int pathLength(RBNode<Integer> node){
        if (node.isNullLeaf()){
            return 1;
        } else if (pathLength(node.right) != pathLength(node.left))
            return -1;
        if (node.red)
            return pathLength(node.right);
        return (pathLength(node.right) + 1);
    }

    private static boolean redNullLeaves(RBNode<Integer> node){
        if (node.isNullLeaf()){
            if (node.red)
                return true;
            return false;
        }
        return (redNullLeaves(node.right) || redNullLeaves(node.left));
    }


} // class RBTest
