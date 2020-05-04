import edu.princeton.cs.algs4.StdRandom;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class RandomizedQueue<Item> implements Iterable<Item> {

    private int size = 0;
    private Item[] queue;

    private void resize(int nsize) {
        Item[] newQueue = (Item[]) new Object[nsize];
        for (int i = 0; i < size; i++)
            newQueue[i] = queue[i];
        queue = newQueue;
    }

    private void checkResize() {
        if (size == queue.length)
            resize(queue.length * 2);
        else if (size <= queue.length / 4)
            resize(queue.length / 2);
    }

    private class RanQueIter implements Iterator<Item> {

        private final int[] values = new int[size];
        private int currentValue = 0;

        public RanQueIter() {
            for (int i = 0; i < size; i++)
                values[i] = i;

            StdRandom.shuffle(values);
        }

        public boolean hasNext() {
            return currentValue < values.length;
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }

        public Item next() {
            if (!hasNext())
                throw new NoSuchElementException();

            return queue[values[currentValue++]];
        }
    }

    // construct an empty randomized queue
    public RandomizedQueue() {
        queue = (Item[]) new Object[1];
    }

    // is the randomized queue empty?
    public boolean isEmpty() {
        return size == 0;
    }

    // return the number of items on the randomized queue
    public int size() {
        return size;
    }

    // add the item
    public void enqueue(Item item) {
        if (item == null)
            throw new IllegalArgumentException();

        queue[size] = item;
        size++;
        checkResize();
    }

    // remove and return a random item
    public Item dequeue() {
        if (size == 0)
            throw new IllegalArgumentException();

        int x = StdRandom.uniform(size);
        Item item = queue[x];
        queue[x] = queue[--size];

        checkResize();
        return item;
    }

    // return a random item (but do not remove it)
    public Item sample() {
        if (size == 0)
            throw new NoSuchElementException();

        return queue[StdRandom.uniform(size)];
    }

    // return an independent iterator over items in random order
    public Iterator<Item> iterator() {
        return new RanQueIter();
    }

    private void checkState() {
        System.out
                .format("Length: %d | n: %d | first: %d | last: %d\n", queue.length, size, queue, );
    }

    public static void main(String[] args) {

        RandomizedQueue<Integer> rQue = new RandomizedQueue<Integer>();
        int a;

        rQue.checkState();
        rQue.enqueue(4);
        rQue.checkState();
        rQue.enqueue(30);
        rQue.checkState();
        rQue.enqueue(31);
        rQue.checkState();
        rQue.isEmpty();
        rQue.checkState();
        rQue.enqueue(9);
        rQue.checkState();
        rQue.dequeue();
        rQue.checkState();
        rQue.enqueue(23);
        rQue.checkState();
    }
}
