package com.inetbanking.testcases;

import java.time.Duration;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.inetbanking.pageobjects.Loginpage;

public class TC_LoginTest_001 extends BaseClass
{
	@Test
	public void LoginTest()
	{
		driver.get(Baseurl);
		driver.manage().window().maximize();
		logger.info("Url is opened");
		Loginpage lp = new Loginpage(driver);
		lp.setUserName(username);
		logger.info("Entered Username");

		lp.setPassword(password);
		logger.info("Entered Password");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

		lp.clickSubmit();
		if(driver.getTitle().equals("GTPL Bank Manager HomePage"))
		{
			Assert.assertTrue(true);
			logger.info("Test case Passed");

		}
		else
		{
			Assert.assertTrue(false);
			logger.info("Test case Failed");

		}
	}
	

}
