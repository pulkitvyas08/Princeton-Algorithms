import edu.princeton.cs.algs4.StdRandom;

import java.util.Iterator;

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
        
    }

    // return a random item (but do not remove it)
    public Item sample() {

    }

    // return an independent iterator over items in random order
    public Iterator<Item> iterator() {

    }

    // unit testing (required)
    public static void main(String[] args) {

    }

}
