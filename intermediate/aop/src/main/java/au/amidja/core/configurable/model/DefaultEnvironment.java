package au.amidja.core.configurable.model;

import java.util.Date;

public class DefaultEnvironment implements Environment{
	
	@Override
	public Date getCurrentDate() {
		return new Date();
	}

}
