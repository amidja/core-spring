package au.amidja.persistence.dao;

import java.io.Serializable;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import au.amidja.core.persistenc.dao.jpa.GenericJpaDaoImpl;

public class GenericAuhSecurityJpaDao<T extends Serializable, ID extends Serializable> extends GenericJpaDaoImpl<T, ID> {

	@Override
	@PersistenceContext(unitName = "auhSecurityPersistenceUnit")
	public void setEntityManager(final EntityManager entityManager) {
		super.setEntityManager(entityManager);
	}	
}