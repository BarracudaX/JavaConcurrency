package chapter3;

import net.jcip.annotations.NotThreadSafe;

@NotThreadSafe
public class MutableInteger {

    private int field;

    public int getField() {
        return field;
    }

    public void setField(int field) {
        this.field = field;
    }
}
