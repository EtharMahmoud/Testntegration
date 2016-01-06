package config;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.xml.DOMConfigurator;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;


import utility.Log;

public class ActionKeywords {
	public WebDriver driver;
	Properties OR;
	public ActionKeywords() throws IOException
	{
		DOMConfigurator.configure("log4j.xml");
		String Path_OR = Constants.Path_OR;
		FileInputStream fs = new FileInputStream(Path_OR);
		OR= new Properties(System.getProperties());
		OR.load(fs);

	}
	public  void openBrowser(String data){		
		Log.info("Opening Browser");
		try{				
			if(data.equals("Mozilla")){
				driver=new FirefoxDriver();
				Log.info("Mozilla browser started");				
			}
			else if(data.equals("IE")){
				//Dummy Code, Implement you own code
				driver=new InternetExplorerDriver();
				Log.info("IE browser started");
			}
			else if(data.equals("Chrome")){
				//Dummy Code, Implement you own code
				System.setProperty("webdriver.chrome.driver", "C:\\Users\\Ethar Mahmoud\\Downloads\\chromedriver_win32\\chromedriver.exe");
				driver=new ChromeDriver();
				Log.info("Chrome browser started");
			}

			int implicitWaitTime=(10);
			driver.manage().timeouts().implicitlyWait(implicitWaitTime, TimeUnit.SECONDS);
		}catch (Exception e){
			Log.info("Not able to open the Browser --- " + e.getMessage());

		}
	}

	public  void navigate(String data){
		try{
			Log.info("Navigating to URL");
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			driver.get(data);
		}catch(Exception e){
			Log.info("Not able to navigate --- " + e.getMessage());

		}
	}

	public  void click(String object){
		try{
			Log.info("Clicking on Webelement "+ object);
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			driver.findElement(By.xpath(OR.getProperty(object))).click();
		}catch(Exception e){
			Log.error("Not able to click --- " + e.getMessage());

		}
	}
	
	public  void click(String object, String objectProperty){
		try{
			Log.info("Clicking on Webelement "+ object);
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			driver.findElement(By.xpath(OR.getProperty(object).replace("$$$", objectProperty))).click();
		}catch(Exception e){
			Log.error("Not able to click --- " + e.getMessage());

		}
	}

	public  void input(String object, String data){
		try{
			Log.info("Entering the text in " + object);
			driver.findElement(By.xpath(OR.getProperty(object))).sendKeys(data);
		}catch(Exception e){
			Log.error("Not able to Enter UserName --- " + e.getMessage());

		}
	}

	public  void setCheckbox(String object){
		try{
			Log.info("Setting " + object);
			driver.findElement(By.xpath(OR.getProperty(object))).sendKeys(" ");
		}catch(Exception e){
			Log.error("Not able to check " + object + " --- " + e.getMessage());

		}
	}

	public Boolean checkElementExists(String object){
		try{
			Log.info("Checking "+object+" exists");
			Boolean exists = driver.findElements(By.xpath(OR.getProperty(object))).size() > 0;
			if(exists)
			{
				Log.info("Object Exists");
				return true;
			}
			else
			{
				Log.info("Object Doesn't Exist");
				return false;
			}
		}catch(Exception e){
			Log.error("Not able to Close the Browser --- " + e.getMessage());
			return false;
		}
	}
	
	public Boolean checkElementExists(String object, String objectProperty){
		try{
			Log.info("Checking "+object+" exists");
			Boolean exists = driver.findElements(By.xpath(OR.getProperty(object).replace("$$$", objectProperty))).size() > 0;
			if(exists)
			{
				Log.info("Object Exists");
				return true;
			}
			else
			{
				Log.info("Object Doesn't Exist");
				return false;
			}
		}catch(Exception e){
			Log.error("Not able to Close the Browser --- " + e.getMessage());
			return false;
		}
	}
	
	public  void waitFor( String data) throws Exception{
		try{
			Log.info("Wait for 5 seconds");
			Thread.sleep(5000);
		}catch(Exception e){
			Log.error("Not able to Wait --- " + e.getMessage());

		}
	}

	public void closeBrowser(){
		try{
			Log.info("Closing the browser");
			driver.quit();
		}catch(Exception e){
			Log.error("Not able to Close the Browser --- " + e.getMessage());

		}
	}

	public void uploadFile(String btnUpload, String filePath)
	{
		try{
			driver.findElement(By.xpath(OR.getProperty(btnUpload))).click();
			driver.switchTo()
			.activeElement()
			.sendKeys(filePath);
			driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
		}catch(Exception e)
		{

		}
	}

}