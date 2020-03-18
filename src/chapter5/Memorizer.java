package chapter5;

import java.util.Map;
import java.util.concurrent.*;

public class Memorizer<A,V> implements Computable<A,V> {

    private final Computable<A,V> function;

    private final Map<A, Future<V>> cachedResults = new ConcurrentHashMap<>();

    public Memorizer(Computable<A, V> function) {
        this.function = function;
    }

    @Override
    public V compute(A args) {
        Future<V> f = cachedResults.get(args);
        if (f == null) {
            Callable<V> callable = () -> {return this.function.compute(args);};
            FutureTask<V> ft = new FutureTask<>(callable);
            f = cachedResults.putIfAbsent(args, ft);
            if (f == null) {
                f = ft;
                ft.run();
            }
        }

        V result = null;

        try {
            result = f.get();
        } catch (CancellationException e) {
            cachedResults.remove(args,f);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        return result;
    }
}
