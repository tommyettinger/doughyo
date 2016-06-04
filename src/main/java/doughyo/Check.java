package doughyo;

/**
 * Fluent interface with a method test that should check some type (no particular requirements) and return a boolean
 * based on some evaluation of the parameter.
 * Created by Tommy Ettinger on 2/25/2016.
 */
public interface Check<T> {
    boolean test(T t);
}
