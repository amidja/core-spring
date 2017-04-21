package au.amidja.persistence.dao.adapter;

import org.springframework.security.core.userdetails.UserDetails;

import au.amidja.core.Adapter;
import au.amidja.persistence.model.SystemUser;

public interface SystemUserAdapter extends Adapter<UserDetails, SystemUser>{

}