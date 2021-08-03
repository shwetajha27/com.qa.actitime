package com.acti.base;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
/*
 * Title: DriverScript
 * Description:
 * Developed By: Shweta Jha
 * Developed Date: 08/02/21
 * Approved By: Shantosh Kumar
 * Approved Date: 08/02/21
 */

public class DriverScript {
	
	public static WebDriver driver;
	public static Properties prop;
	
	public DriverScript()
	{
		try
		{
		 File src = new File("./atConfig/config.properties");
		 FileInputStream fis = new FileInputStream(src);
		  prop = new Properties();
		 prop.load(fis);
		}
		catch(Exception e)
		{
			System.out.println("Unable to read properties file please check"+e.getMessage());
		}
	}
	
	public void initApplication()
	{
		String browser = prop.getProperty("browser");
		if(browser.equalsIgnoreCase("chrome"))
		{
			System.setProperty("webdriver.chrome.driver", "./atbrowser/chromedriver.exe");
			driver = new ChromeDriver();
		}
		
		else if(browser.equalsIgnoreCase("Firefox"))
		{
			System.setProperty("webdriver.gecko.driver", "./atbrowser/geckodriver.exe");
			driver = new FirefoxDriver();
		}
		
		else if(browser.equalsIgnoreCase("Edge"))
		{
			System.setProperty("webdriver.edge.driver", "./atbrowser/msedgedriver.exe");
			driver = new EdgeDriver();
		}
		
		driver.manage().window().maximize();
		driver.manage().timeouts().pageLoadTimeout(50, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		
		String url = prop.getProperty("url");
		driver.get(url);
	}

}
