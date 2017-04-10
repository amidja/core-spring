package au.amidja.core.configurable.model;

import java.util.Calendar;
import java.util.Date;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import junit.framework.Assert;

@Ignore
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/testAppContext.xml")
public class ProductTest {

	final Date time = Calendar.getInstance().getTime();
	
	@Autowired
	Environment environment;

	@Before
	public void before() {
		((MockEnvironment) this.environment).setCurrentDate(time);
	}

	@Test
	public void created_product_should_have_current_environment_date() {
		final Product product = new Product("", "");
		Assert.assertEquals(time, product.getCreateDate());

	}

	@Test
	public void sell_should_set_createDate_to_now() {
		final Product product = new Product("", "");
		product.sell();
		Assert.assertEquals(time, product.getSaleDate());

	}

}
