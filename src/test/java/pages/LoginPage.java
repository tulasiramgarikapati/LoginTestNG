package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

public class LoginPage 
{
	public WebDriver driver;
	
	@FindBy(name="username")
	public WebElement uid;
	
	@FindBy(name="password")
	public WebElement pwd;
	
	@FindBy(how=How.XPATH, using="(//*[@value='Submit'])")
	public WebElement next;
	
	@FindBy(how=How.XPATH, using="(//*[contains(text(),'Please enter Username:')])")
	public WebElement blankuid;
	
	@FindBy(how=How.XPATH, using="(//*[contains(text(),'Please enter Password:')])")
	public WebElement blankpwd;
	
	@FindBy(how=How.XPATH, using="(//*[contains(text(),'Sorry username or password error')])")
	public WebElement invalid;
	
	public LoginPage(WebDriver driver)
	{
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	public void uidfill(String x)
	{
		uid.sendKeys(x);
	}
	
	public void pwdfill(String x)
	{
		pwd.sendKeys(x);
	}
	
	public void nextClick()
	{
		next.click();
	}
}