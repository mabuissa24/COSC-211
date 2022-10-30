import java.io.File;
import java.io.FileNotFoundException;
import java.util.Locale;
import java.util.Scanner;

public class WordCount {
    public static void main(String[] args) throws FileNotFoundException{
        File text = new File("sampletext.txt");
        Scanner input = new Scanner(text);
        BinarySearchTree<String, Integer> dictionary = new BinarySearchTree<>();
        while (input.hasNext()) {
            String word = input.next().toLowerCase();
            word = word.replaceAll("[^a-zA-Z]", "");
            if (dictionary.lookup(word) != null){
                Integer num = dictionary.lookup(word);
                dictionary.add(word, num + 1);
            } else dictionary.add(word, 1);
        }
        dictionary.inOrderTraverse();
    }
}
