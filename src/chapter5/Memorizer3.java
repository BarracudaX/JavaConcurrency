package chapter5;

import java.util.Map;
import java.util.concurrent.*;

public class Memorizer3<A,V> implements Computable<A,V> {

    private final Computable<A,V> function;
    private final Map<A, Future<V>> cachedResults = new ConcurrentHashMap<>();

    public Memorizer3(Computable<A, V> function) {
        this.function = function;
    }

    @Override
    public V compute(A args) {
        FutureTask<V> task = (FutureTask<V>) cachedResults.get(args);
        if (task == null) {
            Callable<V> callable = () -> {return this.function.compute(args);};
            task = new FutureTask<>(callable);
            cachedResults.put(args, task);
            task.run();
        }

        V result = null;

        try {
            result = task.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        return result;
    }

}
