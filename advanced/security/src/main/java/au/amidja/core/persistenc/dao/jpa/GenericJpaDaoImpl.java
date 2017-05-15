package au.amidja.core.persistenc.dao.jpa;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Query;

import au.amidja.core.persistenc.dao.DaoUtil;
import au.amidja.core.persistenc.dao.IGenericDao;

public class GenericJpaDaoImpl<T extends Serializable, ID extends Serializable> extends BaseJpaDao<T> implements IGenericDao<T, ID> {

	protected Class<T> persistentClass = (Class<T>) DaoUtil.getTypeArguments(GenericJpaDaoImpl.class, this.getClass()).get(0);

	@Override
	public T find(ID id) {
		return _find(persistentClass, id);
	}

	@Override
	public void persist(T entity) {		 
		_persist(entity);
		
	}

	@Override
	public T merge(T entity) {
		return _merge(entity);
	}

	@Override
	public T forceMerge(T entity) {
		return _forceMerge(entity);
	}

	@Override
	public boolean remove(T entity) {
		return _remove(entity);
	}

	@Override
	public void flush() {
		_flush();
	}

	@Override
	public boolean isAttached(T entity) {
		return _contains(entity);
	}

	@Override
	public void detach(T entity) {
		_detach(entity);		
	}

    // _________________________________________________________________________
	/**
     * Find by named query.
     *
     * @param namedQuery the named query
     * @return the list< t>
     */
    @SuppressWarnings("unchecked")
    protected List<T> findByNamedQuery(final String namedQuery) {
        return em().createNamedQuery(namedQuery).getResultList();
    }

    /**
     * Find by named query and params.
     *
     * @param namedQuery the named query
     * @param params the params
     * @return the list< t>
     */
    @SuppressWarnings("unchecked")
    protected List<T> findByNamedQueryAndParams(final String namedQuery, final Object... params) {
        final Query query = em().createNamedQuery(namedQuery);
        for (int count = 0; count < params.length; count++) {
            query.setParameter(count + 1, params[count]);
        }
        return query.getResultList();
    }

    /**
     * Find first by named query and params.
     *
     * @param namedQuery the named query
     * @param params the params
     * @return the t
     */
    protected T findFirstByNamedQueryAndParams(final String namedQuery, final Object... params) {
        final List<T> resultList = findByNamedQueryAndParams(namedQuery, params);
        return resultList.isEmpty() ? null : resultList.get(0);
    }

    // _________________________________________________________________________	
}