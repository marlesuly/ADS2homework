public class MyHeap import java.util.EmptyStackException;

public class MyHeap<T extends Comparable<T>> {
    private MyArrayList<T> heap;

    public MyHeap() {
        heap = new MyArrayList<>();
    }

    public boolean empty() {
        return heap.size() == 0;
    }

    public int size() {
        return heap.size();
    }

    public T getMax() {
        if (empty()) {
            throw new EmptyStackException();
        }
        return heap.get(0);
    }

    public T extractMax() {
        if (empty()) {
            throw new EmptyStackException();
        }
        T max = getMax();
        swap(0, size() - 1);
        heap.remove(size() - 1);
        heapify(0);
        return max;
    }

    public void insert(T item) {
        heap.add(item);
        traverseUp(size() - 1);
    }

    private void heapify(int index) {
        int largest = index;
        int left = leftChildOf(index);
        int right = rightChildOf(index);

        if (left < size() && heap.get(left).compareTo(heap.get(largest)) > 0) {
            largest = left;
        }
        if (right < size() && heap.get(right).compareTo(heap.get(largest)) > 0) {
            largest = right;
        }

        if (largest != index) {
            swap(index, largest);
            heapify(largest);
        }
    }

    private void traverseUp(int index) {
        while (index > 0 && heap.get(parentOf(index)).compareTo(heap.get(index)) < 0) {
            swap(index, parentOf(index));
            index = parentOf(index);
        }
    }

    private int leftChildOf(int index) {
        return 2 * index + 1;
    }

    private int rightChildOf(int index) {
        return 2 * index + 2;
    }

    private int parentOf(int index) {
        return (index - 1) / 2;
    }

    private void swap(int index1, int index2) {
        T temp = heap.get(index1);
        heap.set(index1, heap.get(index2));
        heap.set(index2, temp);
    }
}{
}
