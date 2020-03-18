package chapter13;

import java.util.concurrent.atomic.AtomicInteger;

public class MyConcurrentLinkedList<E> {

    private volatile SyncNode<E> head;

    private final int capacity;

    private final AtomicInteger size = new AtomicInteger(0);

    public MyConcurrentLinkedList(int capacity) {
        this.capacity = capacity;
    }
    

    public void add(E element) {
        if (head == null) {
            head = new SyncNode<>(null, element);
            return;
        }

        for (SyncNode<E> current = head; current != null; current = current.getNext()) {
            if (current.getNext() == null) {
                current.setNext(new SyncNode<>(null, element));
            }
        }

    }

    public void remove(E element) {
        for (SyncNode<E> current = head; current != null; current = current.getNext()) {
            if (current.getNext().getElement().equals(element)) {
                current.setNext(current.getNext().getNext());
            }
        }

    }

    public boolean contains(E element) {
        for (SyncNode<E> current = head; current != null; current = current.getNext()) {
            if (current.getElement().equals(element)) {
                return true;
            }
        }
        return false;
    }

}
