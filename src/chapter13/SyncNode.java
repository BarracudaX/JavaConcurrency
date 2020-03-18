package chapter13;

import net.jcip.annotations.GuardedBy;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class SyncNode<E> {

    @GuardedBy("lock")
    private SyncNode<E> next;

    @GuardedBy("lock")
    private E element ;

    private final Lock elementLock = new ReentrantLock();

    private final Lock nodeLock = new ReentrantLock();

    public SyncNode(SyncNode<E> next, E element) {
        this.next = next;
        this.element = element;
    }

    Lock getNodeLock(){
        return nodeLock;
    }

    E getElement(){
        elementLock.lock();
        try{
            return element;
        }finally {
            elementLock.unlock();
        }
    }

    SyncNode<E> getNext(){
        nodeLock.lock();
        try{
            return next;
        }finally {
            nodeLock.unlock();
        }
    }

    void setElement(E element) {
        elementLock.lock();
        try{
            this.element = element;
        }finally {
            elementLock.unlock();
        }
    }

    void setNext(SyncNode<E> next) {
        nodeLock.lock();
        try{
            this.next = next;
        }finally {
            nodeLock.unlock();
        }
    }

}
