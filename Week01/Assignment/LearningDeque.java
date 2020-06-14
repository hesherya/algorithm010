import java.util.Deque;
import java.util.LinkedList;

/**
 * @author hesher
 */
public class LearningDeque {
    public static void main(String[] args) {
        Deque<String> deque = new LinkedList<>();

        deque.offerFirst("a");
        deque.offerFirst("b");
        deque.offerFirst("c");
        System.out.println(deque);

        String str = deque.peekFirst();
        System.out.println(str);
        System.out.println(deque);

        while (deque.size() > 0) {
            System.out.println(deque.pollFirst());
        }
        System.out.println(deque);
    }
}
