package df.tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;
import org.testng.annotations.Parameters;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;


import df.myLogger.*;
import df.framework.Framework;

public class testA {
	
	public Framework work = new Framework();
	public Logging testLog = new Logging();
	
	@Parameters ("browser")
	@BeforeMethod
    public void before(String browser) {
    		 testLog.startLogger(browser,"DefinitionTab_");
    		 work.startDriver(browser);    		
    }
	
	@Test
	public void test() {
		
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		WebElement element1 = work.driver.findElement(By.xpath(".//*[@id='tsf']/div[2]/div[3]/center/input[2]"));
		element1.click();
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Parameters ("browser")
	@AfterMethod
	 public void after(String browser) {
		work.stopDriver();
		testLog.stopLogger();
	 }

}

