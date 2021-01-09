
import edu.princeton.cs.algs4.StdIn;

import java.util.Iterator;

public class Permutation {
    public static void main(String[] args) {
        RandomizedQueue q =  new RandomizedQueue();
        int k = Integer.parseInt(args[0]);
        while (!StdIn.isEmpty()) {
            String value = StdIn.readString();
            q.enqueue(value);
        }

        Iterator<String> it = q.iterator();

        while (k > 0) {
            System.out.println(it.next());
            k--;
        }
    }
}
