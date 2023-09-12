public class LinkedListDeque<T> implements deque<T>{
    private class Node {
        public T val;
        public Node last,next;

        public Node() {
            this.val = null;
            this.last = this;
            this.next = this;
        }
        public Node(T val, Node last, Node next) {
            this.val = val;
            this.last = last;
            this.next = next;
        }
    }

    private final Node head;

    private int size;

    public LinkedListDeque() {
        head = new Node();
        size = 0;
    }

    @Override
    public void addFirst(T item) {
        size++;
        Node now = new Node(item, head, head.next);
        head.next.last = now;
        head.next = now;
    }

    @Override
    public void addLast(T item) {
        size++;
        Node now = new Node(item, head.last, head);
        head.last.next = now;
        head.last = now;
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
        for(Node i = head.next;i != head;i = i.next) {
            System.out.print(i.val.toString() + " ");
        }
        System.out.println();
    }

    @Override
    public T removeFirst() {
        if(isEmpty()) return null;
        size--;
        T ans = head.next.val;
        head.next.next.last = head;
        head.next = head.next.next;
        return ans;
    }

    @Override
    public T removeLast() {
        if(isEmpty()) return null;
        size--;
        T ans = head.last.val;
        head.last.last.next = head;
        head.last=head.last.last;
        return ans;
    }

    @Override
    public T get(int index) {
        if(!(index<size)) return null;
        Node ans = head.next;
        for(int i=1;i<index;i++) {
            ans = ans.next;
        }
        return ans.next.val;
    }

    private T getTo(Node now,int index) {
        if(index == 0) return now.val;
        return getTo(now.next, index-1);
    }

    public T getRecursive(int index) {
        return getTo(head.next, index);
    }
}
