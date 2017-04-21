package au.amidja.persistence.dao;

import au.amidja.core.persistenc.dao.GenericDao;
import au.amidja.persistence.model.SystemUser;

public interface SystemUserReadDao extends GenericDao<SystemUser, Integer>{
	
	public SystemUser findByUserId(Integer id);
	
	public SystemUser findByUserName(String userName);	
}
