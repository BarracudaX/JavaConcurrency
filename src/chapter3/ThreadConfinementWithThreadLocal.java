package chapter3;

public class ThreadConfinementWithThreadLocal {

    private static ThreadLocal<NotThreadSafeObject> container = ThreadLocal.withInitial(() -> new NotThreadSafeObject());

    public NotThreadSafeObject getObject(){
        return container.get();
    }

}
