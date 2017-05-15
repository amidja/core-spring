package au.amidja.core.persistenc.dao.jpa;

import java.io.Serializable;

import javax.persistence.EntityManager;

/**
 * Base class for DAOs that uses JPA EnityManagers.
 */
public class BaseJpaDao <T> {
	
	private EntityManager entityManager;

	/**
	 * Set the current EntityManager
	 * @param entityManager
	 */
	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	/**
	 * Get the current EntityManager
	 */
	protected EntityManager getEntityManager() {
		return entityManager;
	}
	
	/**
	 * Get the current EntityManager
	 */
	protected EntityManager em() {
		return entityManager;
	}
		
	/**
	 * Return the persistent instance of the given entity class with the given
	 * identifier, or null if there is no such persistent instance.
	 */
	protected <T> T _find(Class<T> type, Serializable id) {
		return em().find(type, id);
	}
	
	/**
	 * <p>
	 * Make a transient instance persistent and add it to the datastore. This
	 * operation cascades to associated instances if the association is mapped
	 * with cascade="persist". Throws an error if the entity already exists.
	 * 
	 * <p>
	 * Does not guarantee that the object will be assigned an identifier
	 * immediately. With <code>persist</code> a datastore-generated id may not
	 * be pulled until flush time.
	 */
	protected void _persist(Object... entities) {
		for (Object entity : entities) {
			if (entity != null)
				em().persist(entity);
		}
	}
	
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
	 * session. This operation cascades to associated instances if the
	 * association is mapped with cascade="merge".
	 */
	protected <T> T _merge(T entity) {
		return em().merge(entity);
	}
	
	/**
	 * <p>
	 *  Same as merge but also synchronize the persistence context (entity) to the underlying database. 
	 */
	protected <T> T _forceMerge(T entity){
		 T newEntity = em().merge(entity);
		 em().flush();
		 return newEntity;		
	}
	
	/**
	 * Remove the specified entity from the datastore.
	 * 
	 * @return <code>true</code> if the object is found in the datastore and
	 *         removed, <code>false</code> if the item is not found.
	 */
	protected boolean _remove(T entity){
		if (entity != null) {
			if (em().contains(entity)) {
				em().remove(entity);
				return true;
			} 
		}
		return false;
	}	
		
	/**
	 * Flushes changes in the session(cache) to the datastore.
	 */
	protected void _flush() {
		em().flush();
	}
	
	/**
	 * Returns true if the object is connected to the current hibernate session.
	 */
	protected boolean _contains(Object o) {
		return em().contains(o);
	}
	
	
	public void _detach(T entity) {
		entityManager.detach(entity);		
	}
}
	

