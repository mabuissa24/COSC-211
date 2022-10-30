import java.lang.reflect.Array;
import java.util.Random;
import java.util.Stack;

public class SkipList<E extends Comparable<E>> implements AmhSortedSet<E> {

    private Node<E> head;
    private int height;
    private int numElts = 0;
    private Random rand;
    private double p;

    private int countOps = 0;

    public boolean add(E x) {
        Stack<Node<E>> preds = findAllPreds(x);
        Node<E> pred0 = null;
        if (!preds.isEmpty()) {
            pred0 = preds.peek();
            if (pred0.nextNodes[0] != null && pred0.nextNodes[0].data.equals(x))
                return false; // elt x was already present
        }
        Node<E> newNode = new Node<E>(x, chooseHeight());
        int newHeight = newNode.getHeight();
        if (newHeight > height) {
            if (newHeight > head.nextNodes.length-1) {
                Node<E>[] temp = (Node<E>[]) Array.newInstance(Node.class, newHeight+1);
                for(int i = 0; i < head.nextNodes.length; i++) {
                    temp[i] = head.nextNodes[i];
                }
                head.nextNodes = temp;
            }
            for (int i = newHeight; i > height; i--) {
                head.nextNodes[i] = newNode;
            }
            height = newHeight;
            preds = findAllPreds(x);
        }
        for (int i = 0; i <= newHeight; i++){
            if (numElts == 0){
                head.nextNodes[i] = newNode;
            } else {Node<E> pred = preds.pop();
                Node<E> tempNext = null;
                if (pred.nextNodes[i] != null && !pred.nextNodes[i].equals(newNode)) {
                    tempNext = pred.nextNodes[i];
                }
                pred.nextNodes[i] = newNode;
                newNode.nextNodes[i] = tempNext;
            }
        }
        numElts++;
        return true;
    }

    public E remove(E x) {
        Stack<Node<E>> preds = findAllPreds(x);

        Node<E> pred0 = preds.peek();
        if(pred0.nextNodes[0] == null) return null; // empty list
        if(pred0.nextNodes[0] != null && !pred0.nextNodes[0].data.equals(x)) return null; // non-empty list, elt x wasn't present

        Node<E> toRemove = pred0.nextNodes[0];
        for (int i = 0; i <= toRemove.getHeight(); i++){
            Node<E> pred = preds.pop();
            pred.nextNodes[i] = toRemove.nextNodes[i]; // Do i need to test for nullity?
        }
        numElts--;
        return x;
    }

    public E find(E x) {
        // find the predecessor nodes on each level of the list
        Stack<Node<E>> preds = findAllPreds(x);
        Node<E> level0 = preds.pop(); // the top of the stack is the predecessor on level 0
        if (level0.nextNodes[0] == null) return null;
        else return level0.nextNodes[0].data;
    }

    public int size() {
        return numElts;
    }

    protected Stack<Node<E>> findAllPreds(E x) {
        countOps = 0;
        Stack<Node<E>> toReturn = new Stack<Node<E>>();
        for (int i = height; i >= 0; i--){
            Node<E> toAdd = head;
            while (toAdd.nextNodes[i] != null && toAdd.nextNodes[i].data.compareTo(x) < 0){
                toAdd = toAdd.nextNodes[i];
                countOps++;
            }
            toReturn.push(toAdd);
            countOps++;
        }
        return toReturn;
    }

    public void print() {
        for(int i = 0; i <= height; i++) {
            Node<E> y = head;
            while(y.nextNodes[i] != null) {
                y = y.nextNodes[i];
                System.out.print(y.data + " ");
            }
            System.out.println();
        }
    }


    public int getOps() {
        return countOps;
    }

    private int chooseHeight() {
        int level = 0;
        double flip = rand.nextDouble();
        while(flip < p) {
            level++;
            flip = rand.nextDouble();
        }
        return level;
    }

    public SkipList() {
        head = new Node<E>(null, 0);
        height = 0;
        p = 0.5;
        rand = new Random();
    }

    public SkipList(int seed) {
        head = new Node<E>(null, 0);
        height = 0;
        p = 0.5;
        rand = new Random(seed);
    }

    public SkipList(double prob) {
        head = new Node<E>(null,0);
        height = 0;
        p = prob;
        rand = new Random();
    }

    public SkipList(double prob, int seed) {
        head = new Node<E>(null,0);
        height = 0;
        p = prob;
        rand = new Random(seed);
    }

}
