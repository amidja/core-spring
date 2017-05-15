package au.amidja.persistence.dao.impl;

import javax.persistence.NoResultException;
import javax.persistence.Query;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import au.amidja.persistence.dao.GenericAuhSecurityJpaDao;
import au.amidja.persistence.dao.ISystemUserReadDao;
import au.amidja.persistence.model.SystemUser;

@Repository("systemUserReadDao")
public class SystemUserReadJpaDao extends GenericAuhSecurityJpaDao<SystemUser, Integer> implements ISystemUserReadDao {

	private static final Logger LOG = LoggerFactory.getLogger(SystemUserReadJpaDao.class);

	@Override	
	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	public SystemUser findByUserId(Integer id) {
		
		if (id == null){
			throw new IllegalArgumentException("User Id cannot be null");
		}
		
		try {
			Query q = getEntityManager().createQuery("select a from SystemUser a " + "where a.id = ?1");
			q.setParameter(1, id.intValue());
			return (SystemUser) q.getSingleResult();
		} catch (NoResultException nre) {
			return null;
		}
	}

	@Override	
	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)	
	public SystemUser findByUserName(String userName) {
		
		if (userName == null || userName.length() <=0 ){
			throw new IllegalArgumentException("UserName cannot be empy or null");
		}
		
		try {
			Query q = getEntityManager().createQuery("select a from SystemUser a " + "where a.userName = ?1");
			q.setParameter(1, userName);
			return (SystemUser) q.getSingleResult();
		} catch (NoResultException nre) {
			return null;
		}
	}

	
	
	
	
}
