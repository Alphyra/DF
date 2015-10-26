package df.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;

import df.myLogger.*;

import java.util.List;


public class BasePage {
	/* This page's WebDriver */ 
	protected WebDriver driver;
	protected Logging testLog;
	
	/* Constructor */ 
	public BasePage(WebDriver driver, Logging testLog) {
		this.driver = driver;
		this.testLog = testLog;
	}
	
	/* Check is an element at page. */
	public boolean isElementPresent(By by) {
		try {
			driver.findElement(by);//if it does not find the element throw NoSuchElementException, thus returns false. 
			return true;
		} catch (NoSuchElementException e) {
			testLog.lg.debug("---ERROR Element " + by + " isn't found" + "\n" + e.getMessage()); 
			return false;
		}
	}
	
	/* Find an element */
	public WebElement getElement(By by) {
		testLog.lg.debug("---Start to find element: " + by);
		WebElement element;
		try {
			element = driver.findElement(by);
			return element;
		} catch (NoSuchElementException e) {
			testLog.lg.debug("---ERROR Element: " + by + " isn't found " + "\n" + e.getMessage());
			return null;
		}		
	}
	
	/* Find an elements */
	public List<WebElement> getElements(By by){
		testLog.lg.debug("---Start to find elements: " + by);
		List<WebElement> list;
		try {
			list = driver.findElements(by);
			return list;
		} catch (NoSuchElementException e) {
			testLog.lg.debug("---ERROR: Elements: " + by + " isn't found " + "\n" + e.getMessage());
			return null;
		}
	}
	
		
	/* Click on an element */
	public void clickOnElement(By by) {
		testLog.lg.debug("---Start to click on element: " + by);
		try {
			WebElement element = getElement(by);
			element.click();
		} catch (Exception e) {
			testLog.lg.debug ("---ERROR: Click on element " + by + " wasn't done" + "\n");
		}
	}
	
	/*Get text from an element*/
	public String getText(By by) {
		testLog.lg.debug("---Start getting text on element: " + by);
		try {
			WebElement element = getElement(by);
			return element.getText();
		} catch (Exception e) {
			testLog.lg.debug ("---ERROR getting text at element " + by + "\n" + e.getMessage());
			return null;
		}
	}
	
	/*Get number of elements*/
	public int countElements(By by){
		testLog.lg.debug("---Start getting count of element: " + by);
		try {
			List<WebElement> list = getElements(by);
			return list.size();
		} catch (Exception e) {
			testLog.lg.debug ("---ERROR getting count of element " + by + "\n" + e.getMessage());
			return -1;			
		}
	}
	
	/* Get attribute value of an element */
	public String getAttributeValue(By by, String attribute){
		testLog.lg.debug("---Start getting value for attribut: " + attribute + " of element: " + by);
		WebElement element;
		try {
			element = getElement(by);
			String av = element.getAttribute(attribute);
			return av;
		} catch (Exception e) {
			testLog.lg.debug ("---ERROR getting attribute of element " + by + "\n" + e.getMessage());
			return null;
		}
	}
	
	/* Enter text at some field */
	public void putTextToElement(By by,String text){
		testLog.lg.debug("---Start putting text: " + text + " to element: " + by);
		WebElement element;
		try {
			element = getElement(by);
			element.sendKeys(text);
		} catch (Exception e) {
			testLog.lg.debug ("---ERROR putting text to element " + by + "\n" + e.getMessage());
		}
	}
	
	/* Clear input field */
	public void clearElement(By by){
		testLog.lg.debug("---Start clearing element: " + by);
		WebElement element;
		try {
			element = getElement(by);
			element.clear();
		} catch (Exception e) {
			testLog.lg.debug ("---ERROR clearing element " + by + "\n" + e.getMessage());
		}		
	}	
}
