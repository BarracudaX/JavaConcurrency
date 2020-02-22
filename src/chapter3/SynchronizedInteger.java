package chapter3;

import net.jcip.annotations.ThreadSafe;

@ThreadSafe
public class SynchronizedInteger {

    private int field;

    public synchronized int getField() {
        return field;
    }

    public synchronized void setField(int field) {
        this.field = field;
    }
}
