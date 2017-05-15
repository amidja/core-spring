package au.amidja.persistence.dao;

import au.amidja.core.persistenc.dao.IGenericDao;
import au.amidja.persistence.model.UserRoles;

public interface IUserRolesReadDao extends IGenericDao<UserRoles, Integer>{
	
	public UserRoles findByUserId(Integer id);

	
}
