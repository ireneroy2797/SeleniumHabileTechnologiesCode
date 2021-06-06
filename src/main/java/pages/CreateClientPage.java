package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CreateClientPage {

WebDriver driver;
	
	public CreateClientPage(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(this.driver,this);
	}
		
	@FindBy(css=".row div h3[class='ng-binding']")
	public WebElement createClientLabel;

	@FindBy(id="firstname")
	public WebElement firstname;
	
	@FindBy(id="lastname")
	public WebElement lastname;
	
	@FindBy(id="mobileNo")
	public WebElement mobileNo;
	
	@FindBy(id="dateofbirth")
	public WebElement dateofbirth;
	
	@FindBy(id="clienttypeId_chosen")
	public WebElement clienttypeId;
	
	@FindBy(id="genderId_chosen")
	public WebElement genderId;
	
	@FindBy(id="clientClassificationId_chosen")
	public WebElement clientClassificationId;
	
	@FindBy(id="save")
	public WebElement save;
	
	public WebElement clientOption(String Option) {
		return driver.findElement(By.xpath("//li[text()='"+Option+"']"));
	}
	
	public WebElement dobDate(String date) {
		return driver.findElement(By.xpath("//button/span[text()='"+date+"']"));
	}
}
