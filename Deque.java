import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item> {
    private Node<Item> first = null;
    private Node<Item> last = null;
    private  int size = 0;
    private class Node<Item> {
        Item item;
        private Node<Item> prev, next;
        public Node(Item data, Node<Item> prev, Node<Item> next) {
            this.item = data;
            this.prev = prev;
            this.next = next;
        }
    }

  private class DequeIterator implements Iterator<Item>
    {
        private Node<Item> current = first;
        public boolean hasNext() {  return current != null;  }

        public Item next() {
            if (hasNext()) {
                Item temp = current.item;
                current = current.next;
                return temp;
            }
            else {
                throw new NoSuchElementException();

            }
        }
        public void remove(Item item) {
            throw new UnsupportedOperationException();
        }
}
    // construct an empty deque
    public Deque() {
    }

    // is the deque empty?
    public boolean isEmpty() {
        return first == null;
    }

    // return the number of items on the deque
    public int size() {
        return size;
    }


    // // add the item to the front
    public void addFirst(Item item) {
        illegal(item);
        Node<Item> newFirst  =  new Node<>(item, null,null);
        if (isEmpty()) {
            first = newFirst;
            last = newFirst;
        }
        else {
             Node<Item> prevFirst = first;
             newFirst = new Node<>(item, null, prevFirst);
             prevFirst.prev = newFirst;
             first = newFirst;

        }
        size++;
    }

    // add the item to the back
    public void addLast(Item item) {
        illegal(item);
        if (isEmpty()) addFirst(item);
        else {
            last.next = new Node<>(item, last, null);
            size++;
        }

    }


    // remove and return the item from the front
    public Item removeFirst() {
        if (size == 1) {
           return remIfOne();
        }
        else if (size > 1) {
            Node<Item> oldFirst = first;
            first = first.next;
            first.prev = null;
            size--;
            return oldFirst.item;
        }
        else {
            throw new NoSuchElementException();
        }
    }

    // remove and return the item from the back
    public Item removeLast() {
        if (size == 1) {
          return remIfOne();
        }
       else if (size > 1) {
            Node<Item> oldLast = last;
            last = last.prev;
            last.next = null;
            size--;
            return  oldLast.item;
        }
        else {
            throw new NoSuchElementException();
        }
    }

    private Item remIfOne() {
        Item temp = first.item;
        first = null;
        last = null;
        size = 0;
        return temp;
    }
    // return an iterator over items in order from front to back
    public Iterator<Item> iterator() {
        DequeIterator d = new DequeIterator();
        return d;
    }

    private void illegal(Item item) {
        if (item == null) throw new IllegalArgumentException();
    }

    // unit testing (required)
    public  static void main(String[] args) {
        Deque<Integer> deque = new Deque<Integer>();
        deque.addFirst(1);
        System.out.println(deque.size());
        deque.addLast(3);
        deque.addLast(4);
        deque.addLast(5);
        deque.addLast(6);
        deque.addFirst(7);
        deque.addLast(8);
        deque.addLast(9);
        deque.addLast(10);
        System.out.println(deque.size());

        Iterator<Integer> d = deque.iterator();
        d.next();
        d.hasNext();
    }


}

