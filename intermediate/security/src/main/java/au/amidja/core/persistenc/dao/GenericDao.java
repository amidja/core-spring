package au.amidja.core.persistenc.dao;

import java.io.Serializable;

/**
* Interface for a Data Access Object that can be used for a single specified type domain object. 
* A single instance implementing this interface can be used only for the type of domain object specified in the type parameters.
* 
* @param <T>
*            The type of the domain object for which this instance is to be used.
* @param <ID>
*            The type of the id of the domain object for which this instance is to be used.
*/
public interface GenericDao<T extends Serializable, ID extends Serializable> {

	/**
	 * <p>
	 * Get the entity with the specified type and id from the datastore.
	 * 
	 * <p>
	 * If none is found, return null.
	 */
	public T find(ID id);
        
	/**     
     * Persist takes an entity instance, adds it to the context and makes that instance
     * managed (ie future updates to the entity will be tracked).
     *
     * @param obj the obj
     */
    void persist(T obj);

    /**
	 * <p>
	 * Copy the state of the given object onto the persistent object with the
	 * same identifier. If there is no persistent instance currently associated
	 * with the session, it will be loaded. Return the persistent instance. If
	 * the given instance is unsaved, save a copy and return it as a newly
	 * persistent instance.
	 * 
	 * <p>
	 * The instance that is passed in does not become associated with the
	 * session. 
	 */
    T merge(T entity);
    
    /**
     * Force Merge.
     *
     */
    T forceMerge(T entity);
      
    /**
	 * Remove the specified entity from the datastore.
	 * 
	 * @return <code>true</code> if the entity is found in the datastore and
	 *         removed, <code>false</code> if it is not found.
	 */
	public boolean remove(T entity);
    		
    
	/**
	 * Flushes changes in the session to the datastore.
	 */
	public void flush();
	
	/**
	 * Returns <code>true</code> if the object is connected to the current session.
	 */
	public boolean isAttached(T entity);
	
    /**
     * Detach.
     *
     * @param obj the obj
     */
    void detach(T obj);       
}