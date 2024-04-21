import java.util.Iterator;

public class MyLinkedList<T> implements MyList<T> {
    private Node<T> head;
    private Node<T> tail;
    private int size;

    private static class Node<T> {
        T data;
        Node<T> next;

        Node(T data) {
            this.data = data;
        }
    }

    @Override
    public void add(T item) {
        addLast(item);
    }

    @Override
    public void set(int index, T item) {
        Node<T> node = getNode(index);
        node.data = item;
    }

    @Override
    public void add(int index, T item) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException();
        }
        if (index == 0) {
            addFirst(item);
        } else if (index == size) {
            addLast(item);
        } else {
            Node<T> prev = getNode(index - 1);
            Node<T> newNode = new Node<>(item);
            newNode.next = prev.next;
            prev.next = newNode;
            size++;
        }
    }

    @Override
    public void addFirst(T item) {
        Node<T> newNode = new Node<>(item);
        newNode.next = head;
        head = newNode;
        if (size == 0) {
            tail = head;
        }
        size++;
    }

    @Override
    public void addLast(T item) {
        if (size == 0) {
            addFirst(item);
        } else {
            Node<T> newNode = new Node<>(item);
            tail.next = newNode;
            tail = newNode;
            size++;
        }
    }

    @Override
    public T get(int index) {
        Node<T> node = getNode(index);
        return node.data;
    }

    @Override
    public T getFirst() {
        if (head == null) {
            throw new IndexOutOfBoundsException();
        }
        return head.data;
    }

    @Override
    public T getLast() {
        if (tail == null) {
            throw new IndexOutOfBoundsException();
        }
        return tail.data;
    }

    @Override
    public void remove(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }
        if (index == 0) {
            removeFirst();
        } else if (index == size - 1) {
            removeLast();
        } else {
            Node<T> prev = getNode(index - 1);
            prev.next = prev.next.next;
            size--;
        }
    }

    @Override
    public void removeFirst() {
        if (head == null) {
            throw new IndexOutOfBoundsException();
        }
        head = head.next;
        size--;
        if (size == 0) {
            tail = null;
        }
    }

    @Override
    public void removeLast() {
        if (size <= 1) {
            removeFirst();
        } else {
            Node<T> prev = getNode(size - 2);
            prev.next = null;
            tail = prev;
            size--;
        }
    }
    private void swapNodes(Node<T> prev, Node<T> node1, Node<T> node2) {
        if (prev != null) {
            prev.next = node2;
        } else {
            head = node2;
        }

        Node<T> temp = node2.next;
        node2.next = node1;
        node1.next = temp;

        if (node1 == tail) {
            tail = node2;
        }
    }
    @Override
    public void sort() {
        boolean swapped;
        do {
            swapped = false;
            Node<T> current = head;
            Node<T> prev = null;
            while (current != null && current.next != null) {
                Comparable<T> currentData = (Comparable<T>) current.data;
                Comparable<T> nextData = (Comparable<T>) current.next.data;
                if (currentData.compareTo((T) nextData) > 0) {
                    swapNodes(prev, current, current.next);
                    swapped = true;
                }
                prev = current;
                current = current.next;
            }
        } while (swapped);
    }

    @Override
    public int indexOf(Object object) {
        int index = 0;
        for (Node<T> current = head; current != null; current = current.next) {
            if (current.data.equals(object)) {
                return index;
            }
            index++;
        }
        return -1;
    }

    @Override
    public int lastIndexOf(Object object) {
        int index = size - 1;
        for (Node<T> current = tail; current != null; current = getNode(index--)) {
            if (current.data.equals(object)) {
                return index + 1;
            }
        }
        return -1;
    }

    @Override
    public boolean exists(Object object) {
        return indexOf(object) != -1;
    }

    @Override
    public Object[] toArray() {
        Object[] array = new Object[size];
        int index = 0;
        for (Node<T> current = head; current != null; current = current.next) {
            array[index++] = current.data;
        }
        return array;
    }

    @Override
    public void clear() {
        head = null;
        tail = null;
        size = 0;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            private Node<T> current = head;

            @Override
            public boolean hasNext() {
                return current != null;
            }

            @Override
            public T next() {
                T data = current.data;
                current = current.next;
                return data;
            }
        };
    }

    private Node<T> getNode(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }
        Node<T> current = head;
        for (int i = 0; i < index; i++) {
            current = current.next;
        }
        return current;
    }
}