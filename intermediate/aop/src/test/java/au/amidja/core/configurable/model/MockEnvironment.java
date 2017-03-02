package au.amidja.core.configurable.model;

import java.util.Date;

public class MockEnvironment implements Environment{

	private Date date;

	@Override
	public Date getCurrentDate() {
		return this.date;
	}

	public void setCurrentDate(Date date) {
		this.date = date;
	}

}
