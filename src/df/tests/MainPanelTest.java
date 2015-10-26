package df.tests;

import org.testng.annotations.Test;
import org.testng.annotations.Parameters;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;
import org.testng.asserts.SoftAssert;

import df.pages.*;
import df.testLinkIntegration.TLink;
import df.framework.Framework;
import df.myLogger.*;

public class MainPanelTest {
	private SoftAssert sa = new SoftAssert();
	public Framework work = new Framework();
	public Logging testLog = new Logging();
	public MainPanel mainPanel; 
	
	@Parameters ("browser")
	@BeforeMethod
    public void before(String browser) {
    		 testLog.startLogger(browser,"MainPanelTest_");
    		 work.startDriver(browser);
    		 mainPanel = new MainPanel (work.driver, testLog);
    }
	
	@Test
	public void verifyMainPanelControls() {
		sa.assertTrue(mainPanel.verifyDatasetDefinitionLabelPresence(), "ERROR: Dataset Definition label isn't present" + testLog.appender.getLogAsString());
		sa.assertEquals(mainPanel.clickSettingsTab(), "Settings", "ERROR while openning Settings tab" + testLog.appender.getLogAsString());
		sa.assertEquals(mainPanel.clickEventsTab(), "No Events recorded", "ERROR while openning Events tab" + testLog.appender.getLogAsString());
		
		sa.assertAll();		
	}	
		
	@Parameters ("browser")
	@AfterMethod
    public void after(String browser) {
		// report status to TestLink, Save logs  	
		TLink tl = new TLink();  
		tl.setResult(this.getClass().getSimpleName().toString(), testLog.analyzeTestResult(work, sa), browser);
		   	   
		work.stopDriver();
		testLog.stopLogger();
	}
}
