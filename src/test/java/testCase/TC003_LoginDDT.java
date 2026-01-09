package testCase;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccount;
import testBase.BaseClass;
import utilities.DataProviders;

public class TC003_LoginDDT extends BaseClass {
	
	@Test(dataProvider="LoginData", dataProviderClass=DataProviders.class, groups="DataDriven") //getting data provider from different class
	public void verify_loginDDT(String email, String pwd, String exp)
	{
		
		logger.info("****  Starting TC003_Login DDT  ****");
		try
		{
		HomePage hp = new HomePage(driver);
		hp.account();
		hp.login_page();
		
		LoginPage lp = new LoginPage(driver);
		lp.setEmail(email);
		lp.setPassword(pwd);
		lp.Login();
		
		MyAccount myacc= new MyAccount(driver);
		boolean target_page=myacc.isMyAccountVisible();
		
		/*Data is Valid - login success - test pass - logout
		                  login failed - test fail
		                  
		 Data is invalid - login success - test fail - logout
		                   login failed - test pass                  
		 */
		
		if(exp.equalsIgnoreCase("Valid"))
		{
			if(target_page==true)
			{
				myacc.Logout();
				Assert.assertTrue(true);
			}
			else
			{
				Assert.assertTrue(false);
			}
		}
		
		if(exp.equalsIgnoreCase("Invalid"))
		{
			if(target_page==true)
			{
				myacc.Logout();
				Assert.assertTrue(false);
			}
			else
			{
				Assert.assertTrue(true);
			}
		}
		logger.info("****  Finished TC003_Login DDT  ****");
		}
		
		catch(Exception e)
		{
			Assert.fail();
		}
	}
}
		
	
	


