package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage extends BasePage {

	public HomePage(WebDriver driver) 
	{
		super(driver);
		
	}

	//Locators
	
	@FindBy(xpath="//ul[@class='list-inline']//li[@class='dropdown']")
	WebElement myaccount;
	
	@FindBy(xpath="//a[normalize-space()='Register']")
	WebElement register;
	
	@FindBy(xpath="//a[normalize-space()='Login']")
	WebElement login;
	
	//Action Methods
	
	public void account()
	{
	  myaccount.click();
	}
	
	public void reg_page()
	{
		register.click();
	}
	
	public void login_page()
	{
		login.click();;
	}
}
