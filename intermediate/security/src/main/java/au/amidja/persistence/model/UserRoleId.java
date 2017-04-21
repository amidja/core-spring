package au.amidja.persistence.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Embeddable 
@SuppressWarnings("serial")
public class UserRoleId implements java.io.Serializable{
	
	private static final Logger LOG = LoggerFactory.getLogger(UserRoleId.class);
	
	private int userId;	
	private int roleId;
	
	public UserRoleId() {
		super();		
	}
	
	public UserRoleId(int userId, int roleId) {
		this();
		this.userId = userId;
		this.roleId = roleId;
	}

	@Column(name="USER_ID_FK", unique=false, nullable=false)
	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	@Column(name="ROLE_ID_FK", unique=false, nullable=false)
	public int getRoleId() {
		return roleId;
	}

	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + roleId;
		result = prime * result + userId;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		UserRoleId other = (UserRoleId) obj;
		if (roleId != other.roleId)
			return false;
		if (userId != other.userId)
			return false;
		return true;
	}
}