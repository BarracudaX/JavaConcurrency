package chapter5;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class Memorizer2<A,V> implements Computable<A,V> {

    private final Computable<A,V> function;
    private final Map<A,V> cachedResults = new ConcurrentHashMap<>();

    public Memorizer2(Computable<A, V> function) {
        this.function = function;
    }

    @Override
    public V compute(A args) {
        V result = cachedResults.get(args);
        if (result == null) {
            result = function.compute(args);
            cachedResults.put(args, result);
        }
        return result;
    }

}
