package au.amidja.persistence.model;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


@Entity
@Table(name="USER_ROLES")
@SuppressWarnings("serial")
public class UserRoles implements java.io.Serializable {
	
	private static final Logger LOG = LoggerFactory.getLogger(UserRoles.class);
	
	@EmbeddedId
	private UserRoleId userRoleId;

	public UserRoles() {
		super();		
	}

	public UserRoles(UserRoleId userRoleId) {
		super();
		this.userRoleId = userRoleId;
	}

	public UserRoleId getUserRoleId() {
		return userRoleId;
	}

	public void setUserRoleId(UserRoleId userRoleId) {
		this.userRoleId = userRoleId;
	}
}