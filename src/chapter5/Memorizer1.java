package chapter5;

import java.util.HashMap;
import java.util.Map;

public class Memorizer1<A, V> implements Computable<A, V> {

    private final Computable<A,V> function;
    private final Map<A,V> cachedResults = new HashMap<>();

    public Memorizer1(Computable<A, V> function) {
        this.function = function;
    }

    @Override
    public synchronized V compute(A args) {
        V result = cachedResults.get(args);
        if (result == null) {
            result = function.compute(args);
            cachedResults.put(args, result);
        }
        return result;
    }
}
