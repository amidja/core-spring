package au.amidja.core.common.exception.defined;

import java.io.Serializable;
import java.util.Collections;
import java.util.Map;

/**
 * It is used to store information about an error/exception 
 *  
 * @author qja266
 *
 */
public class DefinedError implements Serializable {

	private static final long serialVersionUID = -7238365464962737786L;

	private final String id;
	private final String parentId;
	private final String description;
	private final String userMessage;
	private final Map<String, String> data;

	public DefinedError(String id, String parentId, String description, String userMessage) {
		this.id = id;
		this.parentId = parentId;
		this.description = description;
		this.userMessage = userMessage;
		this.data = Collections.emptyMap();
	}

	public DefinedError(String id, String parentId, String description, String userMessage, Map<String, String> data) {
		this.id = id;
		this.parentId = parentId;
		this.description = description;
		this.userMessage = userMessage;
		this.data = data;
	}

	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * @return the parentId
	 */
	public String getParentId() {
		return parentId;
	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @return the userMessage
	 */
	public String getUserMessage() {
		return userMessage;
	}

	public String getCode() {
		return parentId + "." + id;
	}

	public Map<String, String> getData() {
		return data;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "DefinedError [id=" + id + ", parentId=" + parentId + ", description=" + description + ", userMessage="
				+ userMessage + "]";
	}

}
