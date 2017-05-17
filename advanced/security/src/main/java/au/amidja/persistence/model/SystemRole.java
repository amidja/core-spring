package au.amidja.persistence.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.PostLoad;
import javax.persistence.Table;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Entity
@Table(name="SYSTEM_ROLE")
@SuppressWarnings("serial")
public class SystemRole implements java.io.Serializable{
	
	private static final Logger LOG = LoggerFactory.getLogger(SystemRole.class);
		
	private int id;
	private String description;
	private String role;	
	
	public SystemRole() {
		super();
	}

	public SystemRole(int id, String description, String role) {
		this();
		this.id = id;
		this.description = description;
		this.role = role;
	}

	@Id   
    @Column(name="ROLE_ID", unique=true, nullable=false)
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Column(name="ROLE_DESC", unique=false,  nullable=false, length=50)
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Column(name="ROLE_VAL", unique=false,  nullable=false, length=50)
	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}
 	
	@PostLoad
	public void postLoad(){
		//LOG.debug("Loaded systemRole with id [{}]", getId());
	}
}
