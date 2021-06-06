package pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomeAndClientsListPage {

WebDriver driver;
	
	public HomeAndClientsListPage(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(this.driver,this);
	}

	@FindBy(css="[id='expertsearch'] h3 strong")
	public WebElement WelcomeLabel;
			
	@FindBy(css=".toolbar h4")
	public WebElement clientPage;
	
	@FindBy(id="client_createbutton")
	public WebElement createClient;
	
	@FindBy(id="client-dropdown")
	public WebElement clientDrpdownLabel;
		
	public List<WebElement> clientdrpdownList() {
		return driver.findElements(By.xpath("//a[@id='client-dropdown']/..//li/a"));
	}
	
	@FindBy(css="[data-ng-model='searchText']")
	public WebElement searchTextBox;
	
	@FindBy(xpath="//input[@data-ng-model='searchText']/../span/button")
	public WebElement searchButton;

	public WebElement clientID(String id) {
		return driver.findElement(By.xpath("//td[text()='"+id+"']"));
	}
	
	public WebElement clientName(String id, String name) {
		return driver.findElement(By.xpath("//td[text()='"+id+"']/../td[text()='"+name+"']"));
	}
	
	public WebElement ClientStatus(String id, String status) {
		return driver.findElement(By.xpath("//td[text()='"+id+"']/../td[contains(text(),'"+status+"')]"));
	}
	
	@FindBy(id="user-dropdown")
	public WebElement userDrpdownLabel;
			
	@FindBy(xpath="(//a[@id='user-dropdown']/..//li/a)[4]")
	public WebElement Logout;
}


