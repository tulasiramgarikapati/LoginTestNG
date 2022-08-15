package utility;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.io.FileHandler;

import io.github.bonigarcia.wdm.WebDriverManager;

public class TestUtility 
{
	public WebDriver openBrowser()
	{
		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		return driver;
	}
	
	public void launchSite(WebDriver driver, String url) throws InterruptedException
	{
		driver.get(url);
		Thread.sleep(5000);
	}
	
	public void closeSite(WebDriver driver)
	{
		driver.quit();
	}
	
	public String pageScreenShot(WebDriver driver) throws IOException
	{
		SimpleDateFormat sf = new SimpleDateFormat("dd-MM-yyyy-hh-mm-ss");
		Date date = new Date();
		String fname = sf.format(date)+".png";
		
		File src = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		File dest = new File(fname);
		FileHandler.copy(src, dest);
		return(dest.getAbsolutePath());
	}
	
}