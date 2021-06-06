package test;

import java.io.IOException;
import java.time.Duration;
import java.util.Properties;
import java.util.Random;
import java.util.logging.Logger;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import pages.ClientActivationPage;
import pages.CreateClientPage;
import pages.HomeAndClientsListPage;
import pages.LoginPage;

public class testMethods extends baseClass{
	WebDriver driver;
	ExtentTest report;
	Properties prop;
	LoginPage loginpg;
	HomeAndClientsListPage HomeandClientlistpg;
	CreateClientPage CreateClientPg;
	ClientActivationPage ClientActivationPg;
	WebElement wait;
	String ClientID, randomMobilenuum;
	

	public testMethods(WebDriver driver,ExtentTest report) throws IOException {
		this.driver=driver;
		this.report=report;
		this.loginpg = new LoginPage(this.driver);
		this.HomeandClientlistpg = new HomeAndClientsListPage(this.driver);
		this.CreateClientPg = new CreateClientPage(this.driver);
		this.ClientActivationPg = new ClientActivationPage(this.driver);
		prop=testDataSetup();
	}
	
	public void Login() throws InterruptedException {
		Thread.sleep(5000);
		wait= new WebDriverWait(driver, Duration.ofSeconds(50)).until(ExpectedConditions.visibilityOf(loginpg.LandingPageValidation1));		
		Assert.assertEquals(loginpg.LandingPageValidation1.getText(), "A ONE STOP LENDING SOLUTION");
		Assert.assertTrue(loginpg.LandingPageValidation2.getText().contains("Our signature product designed exclusively for the inclusive financing sector!"));
		report.log(LogStatus.PASS,("URL is launched successfully"));
		loginpg.userName.sendKeys(prop.getProperty("userName"));
		report.log(LogStatus.INFO,(prop.getProperty("userName")+" userName is entered")); 
		loginpg.pswd.sendKeys(prop.getProperty("pswd"));
		report.log(LogStatus.INFO,(prop.getProperty("pswd")+" pswd is entered"));
		loginpg.login.click();
		wait= new WebDriverWait(driver, Duration.ofSeconds(50)).until(ExpectedConditions.visibilityOf(HomeandClientlistpg.WelcomeLabel));
		Assert.assertEquals(HomeandClientlistpg.WelcomeLabel.getText(), "Welcome, "+prop.getProperty("userName"));
		report.log(LogStatus.PASS,("User is logged in successfully!"));
	}
		
	public void clientDropdown(String option) throws InterruptedException {
		Actions action = new Actions(driver);
		action.moveToElement(HomeandClientlistpg.clientDrpdownLabel).build().perform();
		for(int i = 0; i<=HomeandClientlistpg.clientdrpdownList().size()-1; i++) {
	       if(HomeandClientlistpg.clientdrpdownList().get(i).getText().contains(option)) 
	            HomeandClientlistpg.clientdrpdownList().get(i).click();
	     }
		report.log(LogStatus.INFO,(option+" is clicked from the Client Dropdown"));
		wait= new WebDriverWait(driver, Duration.ofSeconds(50)).until(ExpectedConditions.visibilityOf(HomeandClientlistpg.clientPage));
		Assert.assertEquals(HomeandClientlistpg.clientPage.getText(), "List of Clients");
		report.log(LogStatus.PASS,("List of Clients page is displayed"));
	}
	
	public String clientcreation() throws InterruptedException {
		HomeandClientlistpg.createClient.click();
		report.log(LogStatus.INFO,("Create client button is clicked"));
		wait= new WebDriverWait(driver, Duration.ofSeconds(50)).until(ExpectedConditions.visibilityOf(CreateClientPg.createClientLabel));
		Assert.assertEquals(CreateClientPg.createClientLabel.getText(),"Create Client");
		report.log(LogStatus.PASS,("Create client Page is displayed"));
		CreateClientPg.firstname.sendKeys(prop.getProperty("firstname"));
		CreateClientPg.lastname.sendKeys(prop.getProperty("lastname"));
		int random =100000000 + new Random().nextInt(900000000);
		randomMobilenuum="9"+Integer.toString(random);
		CreateClientPg.mobileNo.sendKeys(randomMobilenuum);
		CreateClientPg.dateofbirth.sendKeys(prop.getProperty("dateofbirth"));
		CreateClientPg.dobDate("02").click();
		CreateClientPg.clienttypeId.click();
		CreateClientPg.clientOption(prop.getProperty("clienttypeId")).click();
		CreateClientPg.genderId.click();
		CreateClientPg.clientOption(prop.getProperty("genderId")).click();
		CreateClientPg.clientClassificationId.click();
		CreateClientPg.clientOption(prop.getProperty("clientClassificationId")).click();
		report.log(LogStatus.INFO,("All basic information is entered in create client Page"));
		CreateClientPg.save.click();
		report.log(LogStatus.INFO,("Submit button is clicked on create client Page"));
		Thread.sleep(3000);
		wait= new WebDriverWait(driver, Duration.ofSeconds(50)).until(ExpectedConditions.visibilityOf(ClientActivationPg.FirstNameLabel));
		Assert.assertEquals(ClientActivationPg.FirstNameLabel.getText(),prop.getProperty("firstname")+" "+prop.getProperty("lastname"));
		report.log(LogStatus.PASS,("Company Activation Page is displayed"));
		return randomMobilenuum;
	}


	public String clientActivationAndValidation() throws InterruptedException {
		String[] urlSplit=driver.getCurrentUrl().split("/");
		Assert.assertEquals(ClientActivationPg.ClientIDLabel.getText(),"Client #: 00000"+urlSplit[5]+" | External id: | Staff:");
		report.log(LogStatus.PASS,("Client ID is Validated with URL and Client Activation Page label"));
		ClientActivationPg.activateButton.click();
		report.log(LogStatus.INFO,("Activation Button is clicked"));
		Assert.assertEquals(ClientActivationPg.activatePageLabel.getText(), "Activate");
		report.log(LogStatus.PASS,("Activation Page is displayed"));
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript("arguments[0].click();",ClientActivationPg.clientactionform);
		jse.executeScript("arguments[0].click();",ClientActivationPg.activationDateButton);
		jse.executeScript("arguments[0].click();",ClientActivationPg.activationDate);
		report.log(LogStatus.PASS,("Activation Date is Entered as today"));
		CreateClientPg.save.click();
		Thread.sleep(2000);
		wait= new WebDriverWait(driver, Duration.ofSeconds(50)).until(ExpectedConditions.visibilityOf(ClientActivationPg.ClientIDLabel));
		Assert.assertEquals(ClientActivationPg.ClientIDLabel.getText(),"Client #: 00000"+urlSplit[5]+" | External id: | Staff:");
		Assert.assertTrue(ClientActivationPg.clientIDactive.isDisplayed());
		ClientID = "00000"+urlSplit[5];
		report.log(LogStatus.PASS,(ClientID+" Client ID is Activated!!"));
		return ClientID;
	}


	public void clientVerficationthouSearchbox() throws InterruptedException {
		clientDropdown("Client");
		String[] searchOptions= {prop.getProperty("firstname"),prop.getProperty("lastname"),randomMobilenuum,ClientID};
		for(int i=0; i<searchOptions.length; i++) {
			HomeandClientlistpg.searchTextBox.clear();
			HomeandClientlistpg.searchTextBox.sendKeys(searchOptions[i]);
			HomeandClientlistpg.searchButton.click();
			Thread.sleep(2000);
			wait= new WebDriverWait(driver, Duration.ofSeconds(50)).until(ExpectedConditions.visibilityOf(HomeandClientlistpg.clientID(ClientID)));
			Assert.assertTrue(HomeandClientlistpg.clientID(ClientID).isDisplayed());
			Assert.assertTrue(HomeandClientlistpg.clientName(ClientID,prop.getProperty("firstname")+" "+prop.getProperty("lastname")).isDisplayed());
			Assert.assertTrue(HomeandClientlistpg.ClientStatus(ClientID, "Active").isDisplayed());
			report.log(LogStatus.PASS,("Verified ClientID, Name and Status as Active when "+searchOptions[i]+" is entered in the search box"));
		}
	}

	public void Logout() {
		Actions action = new Actions(driver);
		action.moveToElement(HomeandClientlistpg.userDrpdownLabel).build().perform();
        HomeandClientlistpg.Logout.click();
	}
	
}
