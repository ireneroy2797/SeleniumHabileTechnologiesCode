package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ClientActivationPage {

WebDriver driver;
	
	public ClientActivationPage(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(this.driver,this);
	}
		

	@FindBy(css="[class='client-title'] strong")
	public WebElement FirstNameLabel;
	
	@FindBy(css="[class='client-title'] small")
	public WebElement ClientIDLabel;
	
	@FindBy(css="[href*='activate']")
	public WebElement activateButton;
	
	@FindBy(css="[ng-hide='getEnabledSteps().length > 1']")
	public WebElement activatePageLabel;
	
	@FindBy(name="clientactionform")
	public WebElement clientactionform;
	
	@FindBy(id="activationDate")
	public WebElement activationDateButton;
	
	@FindBy(xpath="(//a[@ng-click='select(todayDate.date)'])[2]")
	public WebElement activationDate;
	
	@FindBy(css="i[uib-tooltip='Active']")
	public WebElement clientIDactive;
	
	
}
