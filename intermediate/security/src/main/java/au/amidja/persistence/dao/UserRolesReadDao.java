package au.amidja.persistence.dao;

import au.amidja.core.persistenc.dao.GenericDao;
import au.amidja.persistence.model.UserRoles;

public interface UserRolesReadDao extends GenericDao<UserRoles, Integer>{
	
	public UserRoles findByUserId(Integer id);

	
}
