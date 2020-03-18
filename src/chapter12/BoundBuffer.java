package chapter12;

import java.util.concurrent.Semaphore;

public class BoundBuffer<E> {

    private final Semaphore availableItems ;
    private final Semaphore availableSpaces ;

    private final E[] items ;

    private int putPosition = 0,takePosition = 0;

    public BoundBuffer(int capacity) {
        availableSpaces = new Semaphore(capacity);
        availableItems = new Semaphore(0);
        items = (E[]) new Object[capacity];
    }

    public boolean isEmpty(){
        return availableItems.availablePermits() == 0;
    }

    public boolean isFull(){
        return availableSpaces.availablePermits() == 0;
    }

    public void put(E element) throws InterruptedException {
        availableSpaces.acquire();
        doInsert(element);
        availableItems.release();
    }

    public E take() throws InterruptedException {
        availableItems.acquire();
        E item = doExtract();
        availableSpaces.release();
        return item;
    }

    private synchronized E doExtract() {
        int i = takePosition;

        E element = items[i];

        items[i] = null ;

        takePosition = (++i == items.length) ? 0 : i;

        return element;
    }

    private synchronized void doInsert(E element) {
        int i = putPosition;

        items[i] = element;

        putPosition = (++i == items.length ) ? 0 : i;

    }


}
