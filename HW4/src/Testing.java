public class Testing {
    public static void main(String[] args){
        SkipList<Integer> test = new SkipList<>(29);
        test.add(5);
        test.add(10);
        test.add(20);
        test.add(5);
        test.add(15);
        test.add(25);
        test.add(0);
        test.print();
        //test.add(30);
        //test.add(27865);
        test.remove(6);
        test.remove(25);
        //test.print();
        test.remove(10084);
        //test.remove(15);
        test.remove(0);
        test.remove(-55);
        test.print();
    }
}
