package df.myLogger;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import df.framework.Framework;
import df.myLogger.CustomAppender;

import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.logging.LogEntries;
import org.openqa.selenium.logging.LogEntry;
import org.openqa.selenium.logging.LogType;
import org.testng.asserts.SoftAssert;

public class Logging {
	public Logger lg; 
	private DateFormat dateFormat;
	Date date;
	public CustomAppender appender;
	private String logName;
	
		
	public void startLogger (String browser, String testCaseName){
		logName = browser + "_" + testCaseName;
		//Get current Date and Time. Will be added to log file name
		dateFormat = new SimpleDateFormat("YYYYMMdd_HH-mm-ss");
		date = new Date(); 
		// Create log4j log with name 'logName'
		lg = Logger.getLogger(logName);
		DOMConfigurator.configure("log4j.xml");
		appender = new CustomAppender();
		lg.addAppender(appender);			
	}
	
	public void stopLogger() {
		lg.removeAllAppenders();
	}
	
	/*Analyzing of Browser logs*/
	public boolean analyzeBrowserLogAndSave (WebDriver driver) {
		// this line is for ie, cause ie doesn't provide logs
		if (driver.toString().contains("InternetExplorerDriver")) return true;
		LogEntries logEntries = driver.manage().logs().get(LogType.BROWSER);	       
        if (logEntries.getAll().size()!=0){ 
        	String browserLog = "Browser Log \n";
        	for (LogEntry entry : logEntries) {
        		String logString = new Date(entry.getTimestamp()) + " " + entry.getLevel() + " " + entry.getMessage();
	            System.out.println(logString);
	            browserLog = browserLog + logString + "\n";
        	}
        	saveBrowserLog(browserLog);
        	return false;
        }
         return true;	        	        
	}
	
	
	public void saveBrowserLog (String browserLog) {
		File browserLogFile = new File("testLogs" + File.separator + logName + "_BrowserLog_" + dateFormat.format(date) + ".txt");		
		try {
					if (!browserLogFile.exists()) {
						browserLogFile.createNewFile();
					}
					
					BufferedWriter bw = new BufferedWriter(new FileWriter(browserLogFile));
					bw.write(browserLog);
					bw.close();
				} catch (IOException e) {
					e.printStackTrace();		
				}		
	}
	
	public void saveTestLog(String testLog) {
		
		File browserLogFile = new File("testLogs" + File.separator + logName + dateFormat.format(date) + ".txt");		
		try {
					if (!browserLogFile.exists()) {
						browserLogFile.createNewFile();
					}
					
					BufferedWriter bw = new BufferedWriter(new FileWriter(browserLogFile));
					bw.write(testLog);
					bw.close();
				} catch (IOException e) {
					e.printStackTrace();		
				}		
	}
	
	public boolean analyzeTestResult(Framework work, SoftAssert softAssert){
		// Verify test execution result
		try {
			softAssert.assertAll();			
		} catch (AssertionError e) {
			saveTestLog(e.getMessage());
			analyzeBrowserLogAndSave(work.driver);
			return false;
		}
		// If test execution is PASS then verify Driver status
		if (!(work.driverOK())) {
			analyzeBrowserLogAndSave(work.driver);
			return false;
		}
		// If rest execution result is PASS and Driver status is OK then analyze browser log result
		return analyzeBrowserLogAndSave(work.driver);
	}	
}
