public interface Dictionary<K extends Comparable<K>,V> {

    public V add(K key, V value);

    public V remove(K key);

    public V lookup(K key);

    public void inOrderTraverse();

}
