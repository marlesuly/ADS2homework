public class MyQueue import java.util.EmptyStackException;

public class MyQueue<T> {
    private MyLinkedList<T> list;

    public MyQueue() {
        list = new MyLinkedList<>();
    }

    public boolean empty() {
        return list.size() == 0;
    }

    public int size() {
        return list.size();
    }

    public T peek() {
        if (empty()) {
            throw new EmptyStackException();
        }
        return list.getFirst();
    }

    public T enqueue(T item) {
        list.addLast(item);
        return item;
    }

    public T dequeue() {
        if (empty()) {
            throw new EmptyStackException();
        }
        T item = list.getFirst(); // Get the item to return
        list.removeFirst(); // Remove the item
        return item;
    }
}{
}
