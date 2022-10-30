public class AmhArrayList <E> implements AmhList <E> {

    private Object[] _storage;
    private int _size;

    public AmhArrayList () {
	_storage = new Object[0];
	_size    = 0;
    } // AmhArrayList ()

    public void add (int index, E element) throws IndexOutOfBoundsException,
						  IllegalStateException {
	if (index < 0 || _size < index) {
	    throw new IndexOutOfBoundsException(index);
	}
	_size += 1;
	if (_size >= _storage.length) {
	    try {
		expandCapacity();
	    } catch (OutOfMemoryError e) {
		throw new IllegalStateException("Allocation failed");
	    }
	}
	for (int i = _size - 1; i > index; i -= 1) {
	    _storage[i] = _storage[i-1];
	}
	_storage[index] = element;
    } // add ()

    public E get (int index) throws IndexOutOfBoundsException {
	if (index < 0 || _size <= index) {
	    throw new IndexOutOfBoundsException(index);
	}
	return (E)_storage[index];
    } // get ()

    public E remove (int index) throws IndexOutOfBoundsException {
	if (index < 0 || _size <= index) {
	    throw new IndexOutOfBoundsException(index);
	}
	_size -= 1;
	E element = (E)_storage[index];
	for (int i = index; i < _size; i += 1) {
		    _storage[i] = _storage[i+1];
	}
	return element;
    } // remove ()

    public E set (int index, E element) throws IndexOutOfBoundsException {
	if (index < 0 || _size <= index) {
	    throw new IndexOutOfBoundsException(index);
	}
	E oldElement = (E)_storage[index];
	_storage[index] = element;
	return oldElement;
    } // set ()

    public int size () {
	return _size;
    } // size ()

    private void expandCapacity () {
    	int arrayLength;
    	if (_storage.length == 0){
    		arrayLength = 1;
		} else arrayLength = _storage.length * 2;
	Object[] newStorage = new Object[arrayLength];
	for (int i = 0; i < _storage.length; i += 1) {
	    newStorage[i] = _storage[i];
	}
	_storage = newStorage;
    } // expandCapacity ()

} // AmhArrayList
