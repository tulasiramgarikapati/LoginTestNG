package tests;
import java.io.IOException;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.LogStatus;

import pages.HomePage;
import pages.LoginPage;
import utility.TestUtility;

public class LoginTestwithSuiteParams 
{
	@DataProvider(name="x")
	public Object[][] y()
	{
		Object[][] data = {
				{"http://localhost:8080/LoginDev/","","","bothblank"},
				{"http://localhost:8080/LoginDev/","","tulasi123","uidblank"},
				{"http://localhost:8080/LoginDev/","tulasi123","","pwdblank"},
				{"http://localhost:8080/LoginDev/","tulasi123","tulasi123","invalid"},
				{"http://localhost:8080/LoginDev/","tulasi","tulasi","valid"}
		};
		return data;
	}
	
	@Test(dataProvider="x")
	public void runMethod(String url,String un,String pwd,String c) throws IOException, InterruptedException
	{
		TestUtility tu = new TestUtility();
		WebDriver driver = tu.openBrowser();
		tu.launchSite(driver, url);
		HomePage hp = new HomePage(driver);
		LoginPage lp = new LoginPage(driver);
		
		lp.uidfill(un);
		lp.pwdfill(pwd);
		lp.nextClick();
		
		try
		{
			if(c.equalsIgnoreCase("bothblank") && lp.blankuid.isDisplayed()) 
			{
				Reporter.log("Blank username and password test passed");
			}
			else if(c.equalsIgnoreCase("uidblank") && lp.blankuid.isDisplayed())
			{
				Reporter.log("Blank username test passed");
			}
			else if(c.equalsIgnoreCase("pwdblank") && lp.blankpwd.isDisplayed())
			{
				Reporter.log("Blank password test passed");
			}
			else if(c.equalsIgnoreCase("invalid") && lp.invalid.isDisplayed())
			{
				Reporter.log("Invalid credentials test passed");
			}
			else if(c.equalsIgnoreCase("valid") && hp.successtext.isDisplayed())
			{
				Reporter.log("Valid credentials test passed");
			}
			else
			{
				String fname = tu.pageScreenShot(driver);
				Reporter.log("Test Failed,see"+fname);
				
				Assert.fail();
			}
		}
		catch(Exception e)
		{
			String fname = tu.pageScreenShot(driver);
			Reporter.log(e.getMessage()+fname);
			
			byte[] b = ((TakesScreenshot)driver).getScreenshotAs(OutputType.BYTES);
			Assert.fail();
		}
		
		tu.closeSite(driver);

	}
}
