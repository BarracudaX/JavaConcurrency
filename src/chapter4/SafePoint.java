package chapter4;

import net.jcip.annotations.ThreadSafe;

@ThreadSafe
public class SafePoint {

    private int x,y;

    private SafePoint(int[] values) {
        this(values[0], values[1]);
    }

    public SafePoint(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public synchronized int[] get(){
        return new int[]{x, y};
    }

    public synchronized void set(int x, int y) {
        this.x = x;
        this.y = y;
    }
}
