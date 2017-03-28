package au.amidja.core.configurable.model;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;

public class Product {

	private final String name;
	private final String description;
	private final Date createDate;
	private Status status;
	private Date saleDate;
	
	@Autowired
	private Environment environment;

	public Product(String name, String description) {
		this.name = name;
		this.description = description;
		this.status = Status.PENDING;
		this.createDate = environment.getCurrentDate();
	}

	public void sell() {
		this.status = Status.SALE;
		this.saleDate = environment.getCurrentDate();
	}

	public Date getCreateDate() {
		return createDate;
	}

	public Date getSaleDate() {
		return saleDate;
	}

	public static enum Status {
		PENDING, SALE;
	}
}
