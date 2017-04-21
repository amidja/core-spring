package au.amidja.core;

/**
 *  Transforms on object of type V to an object of type T
 *  @param T - target
 *  @param V - source  
 *     
 */
public interface Adapter<T, V> {
	T adapt(V source);
}
