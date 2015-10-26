package df.framework;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.LoggingPreferences;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.util.logging.Level;
import df.projectSettings.ProjectSettingsReader;

import java.util.concurrent.TimeUnit;
import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import java.io.File;
import java.io.IOException;

import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.commons.io.FileUtils;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.CloseableHttpResponse;

public class Framework {
		
	// field: driver
	public WebDriver driver = null;
	
	// Initiation of driver
	public void startDriver(String browser)
	{
		try {
			if (browser.equalsIgnoreCase("Firefox")) {
				// Settings for browser logs
				DesiredCapabilities caps = DesiredCapabilities.firefox();
		        LoggingPreferences logPrefs = new LoggingPreferences();
		        logPrefs.enable(LogType.BROWSER, Level.SEVERE);
		        caps.setCapability(CapabilityType.LOGGING_PREFS, logPrefs);
				
				this.driver = new FirefoxDriver(caps);
			} else if (browser.equalsIgnoreCase("chrome")) {
				// Set Path for the executable file
				System.setProperty("webdriver.chrome.driver", "C:\\DFAutomation\\DataFactory\\drivers\\chromedriver.exe");
				// Settings for browser logs
				DesiredCapabilities caps = DesiredCapabilities.chrome();
		        LoggingPreferences logPrefs = new LoggingPreferences();
		        logPrefs.enable(LogType.BROWSER, Level.SEVERE);
		        caps.setCapability(CapabilityType.LOGGING_PREFS, logPrefs);
		        
		        this.driver = new ChromeDriver(caps);
			} else if (browser.equalsIgnoreCase("ie")) {
				// Set Path for the executable file
	            System.setProperty("webdriver.ie.driver", "C:\\DFAutomation\\DataFactory\\drivers\\IEDriverServer.exe");
	            /* WebDriver issue - https://code.google.com/p/selenium/issues/detail?id=6565
	            // Settings for browser logs
				DesiredCapabilities caps = DesiredCapabilities.firefox();
		        LoggingPreferences logPrefs = new LoggingPreferences();
		        logPrefs.enable(LogType.BROWSER, Level.ALL);
		        caps.setCapability(CapabilityType.LOGGING_PREFS, logPrefs);*/
	            this.driver = new InternetExplorerDriver();
			} else {
				throw new IllegalArgumentException("The Browser Type is Undefined");
			}
			// Driver settings
			this.driver.manage().deleteAllCookies();
			this.driver.manage().window().maximize();
			this.driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
			this.driver.get(ProjectSettingsReader.properties.getProperty("URL"));
		} catch (Exception e){
			System.out.print("Error while Initializing driver: " + e.getMessage());
		}		
	}
	
	//All browser window will be closed. Browser and resources are stopped. 
	public void stopDriver()
	{
		try {
			this.driver.quit();
		} catch (Exception e){
			this.driver = null;
		}
		
	}
	
	//Check driver status
	public boolean driverOK()
	{
		if (this.driver == null)
		{
			System.out.println("Driver is not initialized");
			return false;
		}
		
		try {
			String temp = this.driver.getCurrentUrl();
		} catch (Exception e){
			System.out.println("Driver is not initialized " + e.getMessage());
			return false;
		}
		
		return true;
	}
		
	//Method sends HTTP requests
	public CloseableHttpResponse ingestData (String fileName){
		
		File file = new File ( ProjectSettingsReader.properties.getProperty("IngestFiles") + fileName); 
		String contentStr;
		String destHttp = "http://enqadf01:8080/ingest/claim";
        CloseableHttpClient client = HttpClients.createDefault();        
        HttpPost httpPost = new HttpPost(destHttp);
        httpPost.addHeader("Content-Type", "text/ppm-claim-header+tsv");        
        CloseableHttpResponse response;
        try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        try{
        	contentStr = FileUtils.readFileToString(file);
        	StringEntity se = new StringEntity(contentStr);
        	httpPost.setEntity(se);
        	response = client.execute(httpPost);
        	//System.out.println(response);
        	return response;
        } catch (IOException ex) {
        	System.out.println(ex.getMessage());
        	return null;
        } finally {
        	try {
        		client.close();
        	} catch (IOException ex){
        		System.out.println(ex.getMessage());
        	}
        }
        
	}
	
	//Method returns current time		
	public String getCurrentTime(){
		DateFormat dateFormat = new SimpleDateFormat("HH:mm");
        Date date = new Date();
        return dateFormat.format(date);
	}	
	
}