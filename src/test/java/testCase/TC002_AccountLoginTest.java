package testCase;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccount;
import testBase.BaseClass;

public class TC002_AccountLoginTest extends BaseClass {
	
	@Test(groups= {"Sanity","Master"})
	public void Test_Login()
	{
		try {
			
		logger.info("****  Starting TC002_AccountLoginTest  ****");
		
		HomePage hp = new HomePage(driver);
		hp.account();
		hp.login_page();
		
		LoginPage lp = new LoginPage(driver);
		lp.setEmail(p.getProperty("email"));
		lp.setPassword(p.getProperty("password"));
		lp.Login();
		
		MyAccount acc = new MyAccount(driver);
		boolean msg=acc.isMyAccountVisible();
		Assert.assertTrue(msg);	
		logger.info("****  Finished TC002_AccountLoginTest  ****");
			
		}
		catch(Exception e)
		{
			Assert.fail();
		}
	}

}
