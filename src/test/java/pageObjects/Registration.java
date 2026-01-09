package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class Registration extends BasePage{

	public Registration(WebDriver driver) 
	{
		super(driver);
		
	}
	
	@FindBy(xpath="//input[@id='input-firstname']")
	WebElement fname;
	
	@FindBy(xpath="//input[@id='input-lastname']")
	WebElement lname;
	
	@FindBy(xpath="//input[@id='input-email']")
	WebElement email;
	
	@FindBy(xpath="//input[@id='input-telephone']")
	WebElement phn;
	
	@FindBy(xpath="//input[@id='input-password']")
	WebElement pwd;
	
	@FindBy(xpath="//input[@id='input-confirm']")
	WebElement cnfrm_pwd;
	
	@FindBy(xpath="//input[@name='agree']")
	WebElement policy;
	
	@FindBy(xpath="//input[@value='Continue']")
	WebElement contin;
	
	@FindBy(xpath="//h1[normalize-space()='Your Account Has Been Created!']")
	WebElement msg_confirmation;
	
	//Action Methods
	
	public void setFirstName(String firstname)
	{
		fname.sendKeys(firstname);
	}
	public void setlastName(String lastname)
	{
		lname.sendKeys(lastname);
	}
	public void setEmail(String mail)
	{
		email.sendKeys(mail);
	}
	public void setphn(String phone)
	{
		phn.sendKeys(phone);
	}
	public void setpwd(String password)
	{
		pwd.sendKeys(password);
	}
	public void setconfirmpwd(String confirm_password)
	{
		cnfrm_pwd.sendKeys(confirm_password);
	}
	public void checkprivacypolicy()
	{
		policy.click();
	}
	public void clickbtn()
	{
		contin.click();
	}
	
	public String message_confirmation()
	{
		try
		{
		return msg_confirmation.getText();
		}
		catch(Exception e)
		{
			return e.getMessage();
		}
	
	}

}
