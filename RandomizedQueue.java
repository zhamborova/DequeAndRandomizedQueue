import edu.princeton.cs.algs4.StdRandom;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class RandomizedQueue<Item> implements Iterable<Item> {
    private Item[] queue;
    private int front;
    private int back;
    private int size = 0;

    private class QueueIterator implements Iterator<Item>
    {
        private int index = 0;
        private Integer[] arr = new Integer[back];
        public QueueIterator() {
            for (int i = 0; i < back; i++) {
                arr[i] = i;
            }
        }

        public boolean hasNext() {  return index < back;  }




        public Item next() {
            if (hasNext()) {
                int ind = StdRandom.uniform(0, back);
                while (arr[ind] == null) {
                    ind = StdRandom.uniform(0, back);
                }
                arr[ind] = null;
                index++;
                return queue[ind];
            }
            else {
                throw new NoSuchElementException();

            }
        }
        public void remove(Item item) {
            throw new UnsupportedOperationException();
        }
    }
    public RandomizedQueue() {
        queue = (Item[]) new Object[10];
        front = 0;
        back = 0;
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
        if (item == null) {
            throw new IllegalArgumentException("item can't be null");
        }
        if (size == queue.length - 1) {
            resize(queue.length * 2);
        }
            queue[back] = item;
            back++;
            size++;

    }

    private void resize(int sz) {
        Item[] newQueue = (Item[]) new Object[sz];
        for (int i = 0; i <= sz; i++) {
            if (queue[front] != null) {
                newQueue[i] = queue[front];
                front++;
            }
            else {
                front = 0;
                back = i;
                break;
            }
        }
        queue = newQueue;

    }


    // remove and return a random item
    public Item dequeue() {
        if (size == 0) {
           throw new NoSuchElementException();
        }
        int index = StdRandom.uniform(front, back);
        Item temp = queue[index];
        queue[index] = queue[back - 1];
        queue[back - 1] = null;
        back--;
        size--;

        if (size <= queue.length/ 4) {
            resize(queue.length / 2);
        }


        return temp;
    }

    // return a random item (but do not remove it)
    public Item sample() {
        if (size == 0) {
            throw new NoSuchElementException();
        }
        int index = StdRandom.uniform(front, back);

        return queue[index];
    }

    // return an independent iterator over items in random order
    public Iterator<Item> iterator() {
        return new QueueIterator();
    }

    // unit testing (required)
    public static void main(String[] args) {
        RandomizedQueue q = new RandomizedQueue();
        q.enqueue(1);
        System.out.println(q.size());
        q.enqueue(2);
        q.enqueue(3);
        q.enqueue(4);
        System.out.println(q.size());
        q.dequeue();
        q.dequeue();
        Iterator<Integer> it = q.iterator();

    }
}
