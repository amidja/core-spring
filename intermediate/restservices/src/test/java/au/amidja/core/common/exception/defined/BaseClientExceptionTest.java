package au.amidja.core.common.exception.defined;

import static org.junit.Assert.*;

import org.junit.Test;

public class BaseClientExceptionTest {

	@Test(expected=BaseClientException.class)	
	public void testBase() {
		
		throw new BaseClientException("");
	}

	
	@Test(expected=BaseClientException.class)	
	public void testNotAuthorised() {		
		throw new NotAuthorisedException("");
	}
}
