package au.amidja.persistence.dao;

import au.amidja.core.persistenc.dao.IGenericDao;
import au.amidja.persistence.model.SystemUser;

public interface ISystemUserReadDao extends IGenericDao<SystemUser, Integer>{
	
	public SystemUser findByUserId(Integer id);
	
	public SystemUser findByUserName(String userName);	
}
