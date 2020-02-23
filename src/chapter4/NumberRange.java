package chapter4;

import net.jcip.annotations.NotThreadSafe;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

@NotThreadSafe
public class NumberRange {

    private final AtomicInteger upperBound = new AtomicInteger();

    private final AtomicInteger lowerBound = new AtomicInteger();

    public void setLowerBound(int lowerBound) {
        if (lowerBound > this.lowerBound.get()) {
            throw new IllegalArgumentException("Lower bound is invalid.Current lower bound : "+this.lowerBound.get()+",lower bound provided : "
                                            +lowerBound);
        }
        this.lowerBound.set(lowerBound);
    }

    public void setUpperBound(int upperBound) {
        if (upperBound > this.upperBound.get()) {
            throw new IllegalArgumentException("Upper bound is invalid.Current lower bound : "+this.upperBound.get()+",upper bound provided : "
                    +upperBound);
        }
        this.upperBound.set(upperBound);
    }

    public boolean isInRange(int i) {
        return (i >= this.lowerBound.get() && i <= upperBound.get());
    }

}
