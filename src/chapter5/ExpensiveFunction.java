package chapter5;

import java.math.BigInteger;

public class ExpensiveFunction implements Computable<String, BigInteger> {
    @Override
    public BigInteger compute(String args) {
        //do some expensive things here.
        return new BigInteger(args);
    }
}
