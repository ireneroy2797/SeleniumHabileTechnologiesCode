package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {

WebDriver driver;
	
public LoginPage(WebDriver driver) {
	this.driver=driver;
	PageFactory.initElements(this.driver,this);
}
	

@FindBy(css="h3[class*=\"fontfamily\"]")
public WebElement LandingPageValidation1;
		
@FindBy(css="h5[style*=\"white\"]")
public WebElement LandingPageValidation2;

@FindBy(id="uid")
public WebElement userName;

@FindBy(id="pwd")
public WebElement pswd;

@FindBy(id="login-button")
public WebElement login;

}
