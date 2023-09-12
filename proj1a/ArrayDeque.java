public class ArrayDeque<T> implements Deque<T> {
    private T[] array;
    private int head, tail, arraySize, size;
    public ArrayDeque() {
        array = (T[]) new Object[8];
        head = tail = 0;
        size = 0 ;
        arraySize = 8;
    }

    private void oversize() {
        T[] nowArray = (T[]) new Object[arraySize * 2];
        int j = 0;
        for(int i = head;i != tail;i=(i + 1) % arraySize) {
            nowArray[j] = array[i];
            j++;
        }
        array = nowArray;
        head = 0;
        tail = arraySize;
        arraySize *= 2;
    }

    private void offsize() {
        T[] nowArray = (T[]) new Object[arraySize / 2];
        int j = 0;
        for(int i = head;i != tail;i=(i + 1)% arraySize) {
            nowArray[j] = array[i];
            j++;
        }
        array = nowArray;
        head = 0;
        tail = arraySize;
        arraySize /= 2;
    }
    @Override
    public void addFirst(T item) {
        if(size == arraySize - 1) {
            oversize();
        }
        head = (head - 1 + arraySize) % arraySize;
        array[head] = item;
        size++;
    }

    @Override
    public void addLast(T item) {
        if(size == arraySize - 1) {
            oversize();
        }
        array[tail] = item;
        tail = (tail + 1) % arraySize;
        size++;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void printDeque() {
        for(int i = head;i < tail;i = (i + 1) % arraySize) {
            System.out.print(array[i].toString()+" ");
        }
        System.out.println();
    }

    @Override
    public T removeFirst() {
        if(head == tail) return null;
        T now = array[head];
        array[head] = null;
        head = (head + 1) % arraySize;
        size--;
        if(size < arraySize / 3 && size != 8) {
            offsize();
        }
        return now;
    }

    @Override
    public T removeLast() {
        if(head == tail) return null;
        tail = (tail - 1 + arraySize) % arraySize;
        T now = array[tail];
        array[tail] = null;
        size--;
        if(size < arraySize / 3 && size != 8) {
            offsize();
        }
        return now;
    }

    @Override
    public T get(int index) {
        if(index >= arraySize) return null;
        index = (head + index) % arraySize;
        return array[index];
    }
}
