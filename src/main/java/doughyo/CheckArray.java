package doughyo;

/**
 * Fluent interface with a method test that should check a one-dimensional array (often a primitive array) and return
 * a boolean array with the same length as the given array.  T can be another type that can be treated as a collection
 * of boolean-test-able values, such as a BitSet or any Collection of things that could be compared or queried.
 * Created by Tommy Ettinger on 2/25/2016.
 */
public interface CheckArray<T> {
    boolean[] test(T t);
}
