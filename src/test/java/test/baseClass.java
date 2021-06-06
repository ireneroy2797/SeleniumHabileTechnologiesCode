package test;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import com.relevantcodes.extentreports.ExtentReports;

public class baseClass {
	WebDriver driver;
	Properties prop;
	
	public WebDriver BrowserIntialization() throws IOException {
		System.setProperty("webdriver.chrome.driver",System.getProperty("user.dir")+"\\resources\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.get("https://salesdemo.habiletechnologies.com/");
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		return driver;
	}
	
	public Properties testDataSetup() throws IOException {
		prop = new Properties();
		FileInputStream fis = new FileInputStream(System.getProperty("user.dir")+"\\resources\\testdata.properties");
		prop.load(fis);
		return prop;
	}
	public ExtentReports reportcreation() {
		ExtentReports extent = new ExtentReports(System.getProperty("user.dir")+"\\ExtentReportResults.html");
		return extent;
	}
}
