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

public class HeaderTest {
	private SoftAssert sa = new SoftAssert();
	public Framework work = new Framework();
	public Logging testLog = new Logging();
	Header header;
	
	@Parameters ("browser")
	@BeforeMethod
	private void before(String browser) {
		testLog.startLogger(browser,"HeaderTest_");
		work.startDriver(browser);
		header = new Header(work.driver, testLog);
	}
	
	@Test 
	public void verifyHeaderControls(){
		sa.assertTrue(header.verifyLogoPresence(), "ERROR: Logo isn't present\n" + testLog.appender.getLogAsString());
		sa.assertTrue(header.verifyDashboardLinkPresence(), "ERROR: Dashboard Link isn't present\n" + testLog.appender.getLogAsString());
		sa.assertTrue(header.verifySettingsLinkPresence(), "ERROR: Settings Link isn't present\n" + testLog.appender.getLogAsString());
		sa.assertTrue(header.verifyProfileLinkPresence(), "ERROR: Profile Link isn't present\n" + testLog.appender.getLogAsString());
		sa.assertTrue(header.verifyHelpLinkPresence(), "ERROR: Help Link isn't present\n" + testLog.appender.getLogAsString());
				
		sa.assertAll();
				
	}
	
	@Parameters ("browser")
	@AfterMethod
	private void after(String browser) {	  
	   // report status to TestLink, Save logs  	
	   TLink tl = new TLink();  
	   tl.setResult(this.getClass().getSimpleName().toString(), testLog.analyzeTestResult(work, sa), browser);
	   	   
	   work.stopDriver();
	   testLog.stopLogger();
	}

}
