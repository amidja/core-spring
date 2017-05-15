package au.amidja.persistence.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.PostLoad;
import javax.persistence.Table;

import org.jboss.aerogear.security.otp.api.Base32;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@SuppressWarnings("serial")
@Entity
@Table(name="SYSTEM_USER")
public class SystemUser implements java.io.Serializable{

	private static final Logger LOG = LoggerFactory.getLogger(SystemUser.class);
		
	private int id;	
	private String userName;
	
	@Column(length = 50)
	private String password;
	
	private String firstName;
	private String lastName;
	private Boolean isUsing2FA;

	private String secret;

	private String code;
	private List<SystemRole> systemRoles;
	
	public SystemUser() {
		super();		
		LOG.debug("Creating new systemUser wiht id [{}], username [{}] ", getId(), getUserName());
	}

	public SystemUser(int id, String firstName, String lastName) {
		this();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
	}

	@Id   
    @Column(name="USER_ID", unique=true, nullable=false)
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	
	@Column(name="USER_NAME", unique=true, nullable=false)
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	@Column(name="PASSWORD", unique=false, nullable=false )
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Column(name="FIRST_NM", unique=false,  nullable=false, length=50)
	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	@Column(name="LAST_NM", unique=false,  nullable=false, length=50)
	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	@Column(name="USING_2FA")
    public Boolean isUsing2FA() {		
		return isUsing2FA;
	}

	public void setUsing2FA(Boolean isUsing2FA) {
		this.isUsing2FA = isUsing2FA;
		if (isUsing2FA ){			
			getSecret();
		}else{
			setSecret(null);
		}
	}

	@Column(name="OTP_CODE", unique=false,  nullable=true, length=50)
	public String getCode() {
		return code;
	}


	public void setCode(String code) {
		this.code = code;
	}

	@Column(name="SECRET", unique=false,  nullable=true, length=50)
	public String getSecret() {		
		LOG.debug("retrieved secret [{}] for systemUser [{}]", this.secret,  getUserName());
		return this.secret;
	}
	
	public void setSecret(String secret) {
		this.secret = secret;
		LOG.debug("stored secret [{}] for systemUser [{}]", secret, getUserName());
	}

	@OneToMany(fetch=FetchType.EAGER, cascade=CascadeType.ALL)
    @JoinTable(name = "USER_ROLES", 
    	joinColumns={@JoinColumn(name="USER_ID_FK", referencedColumnName="USER_ID")},
    			inverseJoinColumns={@JoinColumn(name="ROLE_ID_FK", referencedColumnName="ROLE_ID")}
    	)
	public List<SystemRole> getSystemRoles() {
		return systemRoles;
	}

	public void setSystemRoles(List<SystemRole> userRoles) {
		this.systemRoles = userRoles;
	}		

	@PostLoad
	public void postLoad(){
		LOG.debug("Loaded systemUser with id [{}]", getId());
	}
	
}