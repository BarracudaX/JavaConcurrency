package chapter13;

public interface Action<T,R> {

    R perform(T t);

}
