package au.amidja.persistence.dao.adapter;

import org.springframework.security.core.userdetails.UserDetails;

import au.amidja.core.IAdapter;
import au.amidja.persistence.model.SystemUser;

public interface ISystemUserAdapter extends IAdapter<UserDetails, SystemUser>{

}