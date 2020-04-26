import java.util.Iterator;

public class Deque<Item> implements Iterable<Item> {

    private class Node {
        private Item item;
        private Node nextNode;
        private Node lastNode;
    }

    private Node head, tail;
    private int size;

    // construct an empty deque
    public Deque() {
        head = null;
        tail = null;
        size = 0;
    }

    // is the deque empty?
    public boolean isEmpty() {
        return size == 0;
    }

    // return the number of items on the deque
    public int size() {
        return size;
    }

    // add the item to the front
    public void addFirst(Item item) {
        if (item == null)
            throw new IllegalArgumentException();

        Node nn = new Node();
        nn.item = item;
        nn.nextNode = head;
        nn.lastNode = null;

        if (size == 0) {
            head = nn;
            tail = head;
        }
        else {
            head.lastNode = nn;
            head = nn;
        }
        size++;
    }

    // add the item to the back
    public void addLast(Item item) {
        if (item == null)
            throw new IllegalArgumentException();

        Node nn = new Node();
        nn.item = item;
        nn.nextNode = null;
        nn.lastNode = tail;

        if (size == 0) {
            tail = nn;
            head = tail;
        }
        else {
            tail.nextNode = nn;
            tail = nn;
        }
        size++;
    }

    // remove and return the item from the front
    public Item removeFirst() {
        if (isEmpty())
            throw new java.util.NoSuchElementException();

        Item returnItem = head.item;
        head = head.nextNode;

        if (size == 1)
            tail = null;
        else
            head.lastNode = null;

        size--;
        return returnItem;
    }

    // remove and return the item from the back
    public Item removeLast() {
        if (isEmpty())
            throw new java.util.NoSuchElementException();

        Item returnItem = tail.item;
        tail = tail.lastNode;

        if (size == 1)
            head = null;
        else
            tail.nextNode = null;

        size--;
        return returnItem;
    }

    private class DeqIter implements Iterator<Item> {
        private Node current = head;

        public boolean hasNext() {
            return current != null;
        }

        public void remove() {
            throw new UnsupportedOperationException("Remove is not supported");
        }

        public Item next() {
            if (current == null)
                throw new java.util.NoSuchElementException();

            Item item = current.item;
            current = current.nextNode;
            return item;
        }
    }

    // return an iterator over items in order from front to back
    public Iterator<Item> iterator() {
        return new DeqIter();
    }
}
