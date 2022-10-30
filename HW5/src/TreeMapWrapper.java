import java.util.Map;
import java.util.TreeMap;

public class TreeMapWrapper<K extends Comparable<K>, V> implements Dictionary<K, V> {
    private TreeMap<K, V> tree = new TreeMap<>();

    @Override
    public V add(K key, V value) {
        return tree.put(key, value);
    }

    @Override
    public V remove(K key) {
        return tree.remove(key);
    }

    @Override
    public V lookup(K key) {
        return tree.get(key);
    }
    @Override
    public void inOrderTraverse() {
        for (Map.Entry m: tree.entrySet()){
            System.out.println("(" + m.getKey() + ", " + m.getValue() + ")");
        }
    }
}
