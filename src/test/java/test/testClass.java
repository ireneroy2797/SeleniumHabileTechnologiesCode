package test;

import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;


public class testClass extends baseClass{
	WebDriver driver;
	ExtentReports extent;
	ExtentTest report;
	
	@BeforeClass
	public void setUp() throws IOException {
		driver=BrowserIntialization();
		extent=reportcreation();
		report = extent.startTest("ClientCreationAndValidation");
	} 
	
	@Test
	public void ClientCreationAndValidation() throws InterruptedException, IOException {
		testMethods testmethods= new testMethods(driver,report);
		testmethods.Login();
		testmethods.clientDropdown("Client");
		testmethods.clientcreation();
		testmethods.clientActivationAndValidation();
		testmethods.clientVerficationthouSearchbox();
		testmethods.Logout();
	}
	
	@AfterClass
	public void endTest() {
		extent.endTest(report);
		extent.flush();
		driver.close();
	}

	
}
