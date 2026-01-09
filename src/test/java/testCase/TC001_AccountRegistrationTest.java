package testCase;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.Registration;
import testBase.BaseClass;

public class TC001_AccountRegistrationTest extends BaseClass {
	
	//WebDriver driver;
	
	@Test(groups= {"Regression","Master"})
	public void test_registration()
	{
		try
		{
		logger.info("****  Starting TC001_AccountRegistrationTest  ****");
		HomePage hp = new HomePage(driver);
		hp.account();
		logger.info("Clicked on my account");
		hp.reg_page();
		logger.info("Clicked on Registration page");
		Registration reg = new Registration(driver);
		
		reg.setFirstName(randomString().toUpperCase());
		reg.setlastName(randomString().toUpperCase());
		reg.setEmail(randomString()+"@gmail.com");
		reg.setphn(randomNumber());
		
		String password=randomAlphaNumber();
		reg.setpwd(password);
		reg.setconfirmpwd(password);
		reg.checkprivacypolicy();
		reg.clickbtn();
		String msg=reg.message_confirmation();
		logger.info("Validating confirmation message");
		if(msg.equals("Your Account Has Been Created!"))
		{
		Assert.assertTrue(true);
		}
		else
		{
		logger.error("Test Failed");
		logger.debug("Debug logs");
		Assert.assertTrue(false);
		}
		logger.info("****  Finished TC001_AccountRegistrationTest  ****");
		}
		catch(Exception e)
		{
			logger.error("Exception occured ="+e.getMessage());
			Assert.fail();
		}
		
		
		
		
	}
	
	
}
