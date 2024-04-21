import java.util.EmptyStackException;

public class MyStack<T> {
    private MyArrayList<T> list;

    public MyStack() {
        list = new MyArrayList<>();
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
        return list.get(size() - 1);
    }

    public T push(T item) {
        list.add(item);
        return item;
    }

    public T pop() {
        if (empty()) {
            throw new EmptyStackException();
        }
        int lastIndex = size() - 1;
        T item = list.get(lastIndex); // Get the item to return
        list.remove(lastIndex); // Remove the item from the list
        return item;
    }
}
