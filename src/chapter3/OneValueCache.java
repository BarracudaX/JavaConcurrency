package chapter3;

import jdk.nashorn.internal.ir.annotations.Immutable;

import java.math.BigInteger;
import java.util.Arrays;

@Immutable
public class OneValueCache {

    private final BigInteger lastNumber;

    private final BigInteger[] lastFactors;

    public OneValueCache(BigInteger number, BigInteger[] factors) {
        this.lastNumber = number;
        this.lastFactors = Arrays.copyOf(factors,factors.length);
    }

    public BigInteger[] getFactors(BigInteger number) {
        if (lastNumber == null || !number.equals(lastNumber)) {
            return null;
        } else {
            return Arrays.copyOf(lastFactors, lastFactors.length);
        }
    }
}
